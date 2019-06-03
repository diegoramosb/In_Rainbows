package com.inrainbows.activity;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.inrainbows.R;
import com.inrainbows.mvp.model.Semester;
import com.inrainbows.mvp.model.Subject;
import com.inrainbows.mvp.presenter.MainPresenter;
import com.inrainbows.mvp.view.MainContract;

import org.parceler.Parcels;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Main activity of the application
 * @author diego on 15/07/2018.
 */
public class MainActivity extends BaseActivity implements MainContract.View, SubjectsRecyclerViewAdapter.ItemClickListener {

    /**
     * Presenter that the activity will use
     */
    private MainContract.Presenter presenter;

    /**
     * Current semester that the application is displaying
     */
    private Semester currentSemester;

    /**
     * Main page drawer menu
     */
    @BindView(R.id.drawer_layout)
    DrawerLayout drawerLayout;

    /**
     * View for the drawer menu content
     */
    @BindView(R.id.nav_view)
    NavigationView navigationView;

    /**
     * TextView for displaying the current semester grade
     */
    @BindView(R.id.tvSemesterGrade)
    TextView tvGrade;

    /**
     * TextView for displaying the current semester credits
     */
    @BindView(R.id.tvSemesterCredits)
    TextView tvCredits;

    /**
     * Button for selecting an "add" acction
     */
    @BindView(R.id.fab_add)
    FloatingActionButton fabAdd;

    /**
     * Button to add a new grade
     */
    @BindView(R.id.fab_add_grade)
    FloatingActionButton fabAddGrade;

    /**
     * Button to add a new subject
     */
    @BindView(R.id.fab_add_subject)
    FloatingActionButton fabAddSubject;

    /**
     * Button to add a new semester
     */
    @BindView(R.id.fab_add_semester)
    FloatingActionButton fabAddSemester;

    /**
     * Add grade label
     */
    @BindView(R.id.layout_add_grade)
    LinearLayout layoutAddGrade;

    /**
     * Add subject label
     */
    @BindView(R.id.layout_add_subject)
    LinearLayout layoutAddSubject;

    /**
     * Add semester label
     */
    @BindView(R.id.layout_add_semester)
    LinearLayout layoutAddSemester;

    /**
     * RecyclerView for displaying the subjects
     */
    @BindView(R.id.rv_subject_list)
    RecyclerView rvSubjects;

    /**
     * Adapter for the subjects recycler view
     */
    private SubjectsRecyclerViewAdapter subjectsRvAdapter;

    /**
     * Variable to indicate if the FAB menu is open or not
     */
    boolean isFABOpen;

    /**
     * Initializes the activity
     * @param savedInstanceState state parameters
     */
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setUIComponents();

        // Subscribe to the semester and subjects, so that the UI is updated if they change
        subscribeToCurrentSemester();
        subscribeToSubjects();
    }

    /**
     * Initializes the UI components
     */
    @Override
    public void setUIComponents() {
        setContentView(R.layout.main_act);
        ButterKnife.bind(this);

        presenter = ViewModelProviders.of(this).get(MainPresenter.class);
        presenter.setDb(db);

        rvSubjects.setHasFixedSize(true);
        rvSubjects.addItemDecoration(new DividerItemDecoration(this,
                DividerItemDecoration.VERTICAL));
        rvSubjects.setLayoutManager(new LinearLayoutManager(this));
        subjectsRvAdapter = new SubjectsRecyclerViewAdapter(this, new ArrayList<Subject>());
        subjectsRvAdapter.setClickListener(this);
        rvSubjects.setAdapter(subjectsRvAdapter);

        drawerLayout.setStatusBarBackgroundColor(getColor(R.color.colorPrimaryDark));

        isFABOpen = false;

        if(navigationView != null){
            setupDrawerContent(navigationView);
        }
    }

    /**
     * Method used to subscribe to the subjects
     */
    private void subscribeToSubjects(){
        final Observer<List<Subject>> subjectsObserver = (subjects) -> {
            if(subjects != null) {
                subjectsRvAdapter.setSubjects(subjects);
                updateUI(); //The ui is updated every time that there is a change in the subject list
            }
            else {
                //TODO: Hide recycler view and show empty message instead.
            }
        };

        //The observer is executed with any change in the subjects provided by the presenter

        subjectsObserver.onChanged(presenter.getSubjects().getValue());

        presenter.getSubjects().observe(this, subjectsObserver);
    }

    /**
     * Method used to subscribe to the subjects. See subscribeToSubjects() for details
     */
    private void subscribeToCurrentSemester() {
        final Observer<Semester> currentSemesterObserver = (semester)-> {
            if (semester != null) {
                setCurrentSemester(semester);
                updateUI();
            }
            else {
                setCurrentSemesterName("Tap here to add semester");
            }
        };
        if(currentSemester == null){
            currentSemesterObserver.onChanged(presenter.getCurrentSemester().getValue());
        }
        presenter.getCurrentSemester().observe(this, currentSemesterObserver);
    }

    /**
     * Updates the UI
     */
    private void updateUI(){
        if(currentSemester != null) {
            setCurrentSemesterName(currentSemester.getSemesterName());
            tvGrade.setText(new StringBuilder().append(currentSemester.currentGrade()).toString());
            tvCredits.setText(new StringBuilder().append(currentSemester.credits()).toString());
        }
        else {
            setCurrentSemesterName("No semesters");
            tvGrade.setText("0.0");
            tvCredits.setText("0.0");
        }
    }

    /**
     * Shows the {@link EditSemesterActivity} in add mode
     */
    @Override
    public void showAddSemesterActivity(){
        Intent intent = new Intent(this, EditSemesterActivity.class);
        startActivity(intent);
        closeFABMenu();
    }

    /**
     * Shows the {@link EditSubjectActivity} in add mode
     */
    @Override
    public void showAddSubjectActivity() {
        Intent intent = new Intent(this, EditSubjectActivity.class);
        intent.putExtra("currentSemester", Parcels.wrap(currentSemester));
        startActivity(intent);
        closeFABMenu();
    }

    /**
     * Shows the {@link EditGradeActivity} in add mode
     */
    @Override
    public void showAddGradeActivity() {
        Intent intent = new Intent(this, EditGradeActivity.class);
        startActivity(intent);
        closeFABMenu();
    }

    /**
     * Shows the {@link SubjectDetailActivity} of the selected subject
     * @param subject selected subject
     */
    public void showSubjectDetailActivity(Subject subject) {
        Intent intent = new Intent(this, SubjectDetailActivity.class);
        intent.putExtra("subject", Parcels.wrap(subject));
        startActivity(intent);
    }

    /**
     * Binds the presenter to the view
     * @param presenter presenter that the view will use
     */
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

    /**
     * Sets ups the drawer menu content
     * @param navigationView NavigationView with the content
     */
    private void setupDrawerContent(NavigationView navigationView) {

        navigationView.setNavigationItemSelectedListener(
                (item) -> {
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
        );
    }

    /**
     * Sets the given semester as the activity's current semester
     * @param semester new current semester
     */
    @Override
    public void setCurrentSemester(Semester semester){
        this.currentSemester = semester;
    }

    /**
     * Sets the current semester label to the given string
     * @param semesterName semester name or other string
     */
    @Override
    public void setCurrentSemesterName(String semesterName) {
        Menu menu = navigationView.getMenu();
        MenuItem miSemesterName = menu.findItem(R.id.semesters_drawer_item);
        miSemesterName.setTitle(semesterName);
    }

    /**
     * Shows a dialog for the user to choose the current semester
     */
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

    /**
     * Method called when the add FAB is tapped
     */
    @OnClick(R.id.fab_add)
    public void fabAddOnClick(){
        if(!isFABOpen){
            showFABMenu();
        }
        else {
            closeFABMenu();
        }
    }

    /**
     * Method called when the add semester FAB is tapped
     */
    @OnClick(R.id.fab_add_semester)
    public void fabAddSemesterOnClick() {
        showAddSemesterActivity();
    }

    /**
     * Method called when the add subject FAB is tapped
     */
    @OnClick(R.id.fab_add_subject)
    public void fabAddSubjectOnClick() {
        showAddSubjectActivity();
    }

    /**
     * Method called when the add grade FAB is tapped
     */
    @OnClick(R.id.fab_add_grade)
    public void fabAddGradeOnClick(){
        showAddGradeActivity();
    }

    /**
     * Method called when a subject is tapped
     * @param view
     * @param position position of the item in the {@link SubjectsRecyclerViewAdapter}
     */
    @Override
    public void onItemClick(View view, int position) {
//        Toast.makeText(this, "You clicked " + subjectsRvAdapter.getItem(position) + " on row number " + position, Toast.LENGTH_SHORT).show();
        showSubjectDetailActivity(subjectsRvAdapter.getItem(position));
    }

    /**
     * Shows the FAB menu
     */
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

    /**
     * Closes the FAB menu
     */
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

    /**
     * Configures the behaviour of the back button in the activity
     */
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
