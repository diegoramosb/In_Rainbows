package com.inrainbows.activity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AlertDialog;
import android.view.Menu;
import android.view.MenuItem;

import com.inrainbows.R;
import com.inrainbows.mvp.model.Semester;
import com.inrainbows.mvp.presenter.MainPresenter;
import com.inrainbows.mvp.view.MainContract;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * @author diego on 15/07/2018.
 */
public class MainActivity extends BaseActivity implements MainContract.View {

    private MainContract.Presenter presenter;

    @BindView(R.id.drawer_layout)
    DrawerLayout drawerLayout;

    @BindView(R.id.nav_view)
    NavigationView navigationView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.main_act);
        ButterKnife.bind(this);

        presenter = new MainPresenter(this);

        drawerLayout.setStatusBarBackgroundColor(getColor(R.color.colorPrimaryDark));

        update();

    }

    private void update(){
        if(navigationView != null){
            setupDrawerContent(navigationView);
            presenter.updateCurrentSemesterName();
        }
    }

    public void showAddSemester(){
        Intent intent = new Intent(this, EditSemesterActivity.class);
        startActivity(intent);
    }

    @Override
    public void setPresenter(MainContract.Presenter presenter) {
        this.presenter = presenter;
    }

    @Override
    public void showError() {

    }

    @Override
    public void showLoading() {

    }

    @Override
    public void showEmpty() {

    }

    private void setupDrawerContent(NavigationView navigationView) {

        presenter.updateCurrentSemesterName();

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
//                        item.setChecked(true);
                        drawerLayout.closeDrawers();
                        return true;
                    }
                }
        );
    }



    @OnClick(R.id.fab_add)
    public void fabOnClick(){
        presenter.showAddSemesterView();
    }

    @Override
    public void setCurrentSemesterName(String name){
        Menu menu = navigationView.getMenu();
        MenuItem miSemesterName = menu.findItem(R.id.semesters_drawer_item);
        miSemesterName.setTitle(name);
    }

    private void showChooseCurrentSemesterDialog(){

        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(this);
        alertDialogBuilder.setTitle("Select a Group Name");

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
                dialog.dismiss();// dismiss the alertbox after chose option
            }
        });
        AlertDialog alert = alertDialogBuilder.create();
        alert.show();

    }


}
