package com.inrainbows.activity;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.inrainbows.R;
import com.inrainbows.mvp.model.Semester;
import com.inrainbows.mvp.model.Subject;
import com.inrainbows.mvp.presenter.MainPresenter;
import com.inrainbows.mvp.view.MainContract;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * @author diego on 15/07/2018.
 */
public class MainActivity extends BaseActivity implements MainContract.View, SubjectsRecyclerViewAdapter.ItemClickListener {

    private MainContract.Presenter presenter;

    private Semester currentSemester;

    @BindView(R.id.drawer_layout)
    DrawerLayout drawerLayout;

    @BindView(R.id.nav_view)
    NavigationView navigationView;

    @BindView(R.id.tvSemesterGrade)
    TextView tvGrade;

    @BindView(R.id.tvSemesterCredits)
    TextView tvCredits;

    @BindView(R.id.fab_add)
    FloatingActionButton fabAdd;

    @BindView(R.id.fab_add_grade)
    FloatingActionButton fabAddGrade;

    @BindView(R.id.fab_add_subject)
    FloatingActionButton fabAddSubject;

    @BindView(R.id.fab_add_semester)
    FloatingActionButton fabAddSemester;

    @BindView(R.id.layout_add_grade)
    LinearLayout layoutAddGrade;

    @BindView(R.id.layout_add_subject)
    LinearLayout layoutAddSubject;

    @BindView(R.id.layout_add_semester)
    LinearLayout layoutAddSemester;

    @BindView(R.id.rv_subject_list)
    RecyclerView rvSubjects;

    private SubjectsRecyclerViewAdapter subjectsRvAdapter;

    boolean isFABOpen;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.main_act);
        ButterKnife.bind(this);

        presenter = ViewModelProviders.of(this).get(MainPresenter.class);
        presenter.setDb(db);

        rvSubjects.setHasFixedSize(true);
        rvSubjects.setLayoutManager(new LinearLayoutManager(this));
        subjectsRvAdapter = new SubjectsRecyclerViewAdapter(this, new ArrayList<Subject>());
        subjectsRvAdapter.setClickListener(this);
        rvSubjects.setAdapter(subjectsRvAdapter);


        drawerLayout.setStatusBarBackgroundColor(getColor(R.color.colorPrimaryDark));

        isFABOpen = false;

        if(navigationView != null){
            setupDrawerContent(navigationView);
        }

        subscribeToCurrentSemester();
        subscribeToSubjects();
    }

    private void subscribeToSubjects(){
        final Observer<List<Subject>> subjectsObserver = new Observer<List<Subject>>(){
            @Override
            public void onChanged(@Nullable List<Subject> subjects) {
                if(subjects != null) {
                    subjectsRvAdapter.setSubjects(subjects);
                    updateUI();
                }
                else {
                    //TODO: Hide recycler view and show empty message instead.
                }
            }
        };

        subjectsObserver.onChanged(presenter.getSubjects().getValue());
        
        presenter.getSubjects().observe(this, subjectsObserver);
    }

    private void subscribeToCurrentSemester() {
        final Observer<Semester> currentSemesterObserver = new Observer<Semester>() {
            @Override
            public void onChanged(@Nullable Semester semester) {
                if (semester != null) {
                    setCurrentSemester(semester);
                    updateUI();
                }
                else {
                    setCurrentSemesterName("Tap here to add semester");
                }
            }
        };
        if(currentSemester == null){
            currentSemesterObserver.onChanged(presenter.getCurrentSemester().getValue());
        }
        presenter.getCurrentSemester().observe(this, currentSemesterObserver);
    }



    private void updateUI(){
        if(currentSemester != null) {
            setCurrentSemesterName(currentSemester.getSemesterName());
            tvGrade.setText(new StringBuilder().append(currentSemester.currentGrade()).append("").toString());
            tvCredits.setText(new StringBuilder().append(currentSemester.credits()).append("").toString());
        }
        else {
            setCurrentSemesterName("No semesters");
            tvGrade.setText("0.0");
            tvCredits.setText("0.0");
        }
    }

    @Override
    public void showAddSemesterActivity(){
        Intent intent = new Intent(this, EditSemesterActivity.class);
        startActivity(intent);
        closeFABMenu();
    }

    @Override
    public void showAddSubjectActivity() {
        Intent intent = new Intent(this, EditSubjectActivity.class);
        intent.putExtra("currentSemester", currentSemester);
        startActivity(intent);
        closeFABMenu();
    }

    @Override
    public void showAddGradeActivity() {
        Intent intent = new Intent(this, EditGradeActivity.class);
        startActivity(intent);
        closeFABMenu();
    }

    @Override
    public void setPresenter(MainContract.Presenter presenter) {
        this.presenter = presenter;
    }


    @Override
    public void showLoading() {

    }

    @Override
    public void showEmpty() {

    }

    private void setupDrawerContent(NavigationView navigationView) {

        navigationView.setNavigationItemSelectedListener(
                new NavigationView.OnNavigationItemSelectedListener() {
                    @Override
                    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                        switch (item.getItemId()){
                            case R.id.semesters_drawer_item:
                                showChooseCurrentSemesterDialog();
                                break;
                            case R.id.main_activity_drawer_item:
//                                Intent intent = new Intent(MainActivity.this, EditSemesterActivity.class);
//                                startActivity(intent);
                                break;
                            default:
                                break;
                        }
                        drawerLayout.closeDrawers();
                        return true;
                    }
                }
        );
    }


    @Override
    public void setCurrentSemester(Semester semester){
        this.currentSemester = semester;
    }


    @Override
    public void setCurrentSemesterName(String semesterName) {
        Menu menu = navigationView.getMenu();
        MenuItem miSemesterName = menu.findItem(R.id.semesters_drawer_item);
        miSemesterName.setTitle(semesterName);
    }

    private void showChooseCurrentSemesterDialog(){

        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(this);
        alertDialogBuilder.setTitle("Select a semester to set as current");

        final List<Semester> semesters = presenter.getAllSemesters();
        final String[] semesterNames = new String[semesters.size()];
        for(Semester semester : semesters){
            semesterNames[semesters.indexOf(semester)] = semester.getSemesterName();
        }

        alertDialogBuilder.setSingleChoiceItems( semesterNames, -1, new DialogInterface
                .OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int item) {
                presenter.setCurrentSemester(semesters.get(item));
                subscribeToCurrentSemester();
                dialog.dismiss();// dismiss the alertbox after chose option
            }
        });
        AlertDialog alert = alertDialogBuilder.create();
        alert.show();
    }

    @OnClick(R.id.fab_add)
    public void fabAddOnClick(){
        if(!isFABOpen){
            showFABMenu();
        }
        else {
            closeFABMenu();
        }
    }

    @OnClick(R.id.fab_add_semester)
    public void fabAddSemesterOnClick() {
        showAddSemesterActivity();
    }

    @OnClick(R.id.fab_add_subject)
    public void fabAddSubjectOnClick() {
        showAddSubjectActivity();
    }

    @OnClick(R.id.fab_add_grade)
    public void fabAddGradeOnClick(){
        showAddGradeActivity();
    }

    @Override
    public void onItemClick(View view, int position) {
        Toast.makeText(this, "You clicked " + subjectsRvAdapter.getItem(position) + " on row number " + position, Toast.LENGTH_SHORT).show();
    }

    private void showFABMenu(){
        isFABOpen=true;

        layoutAddGrade.setVisibility(View.VISIBLE);
        layoutAddSubject.setVisibility(View.VISIBLE);
        layoutAddSemester.setVisibility(View.VISIBLE);

        fabAdd.animate().rotationBy(90);

        layoutAddGrade.animate().translationY(-getResources().getDimension(R.dimen.standard_55));
        layoutAddSubject.animate().translationY(-getResources().getDimension(R.dimen.standard_105));
        layoutAddSemester.animate().translationY(-getResources().getDimension(R.dimen.standard_155));
    }

    private void closeFABMenu(){
        isFABOpen=false;

        fabAdd.animate().rotationBy(-90);

        layoutAddGrade.animate().translationY(0);
        layoutAddSubject.animate().translationY(0);
        layoutAddSemester.animate().translationY(0);

        layoutAddGrade.setVisibility(View.GONE);
        layoutAddSubject.setVisibility(View.GONE);
        layoutAddSemester.setVisibility(View.GONE);
    }


    @Override
    public void onBackPressed() {
        if(isFABOpen){
            closeFABMenu();
        }
        else {
            super.onBackPressed();
        }
    }

}
