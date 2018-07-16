package com.inrainbows.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.PopupMenu;

import com.inrainbows.R;
import com.inrainbows.mvp.model.Semester;
import com.inrainbows.mvp.presenter.MainPresenter;
import com.inrainbows.mvp.view.MainContract;
import com.inrainbows.persistence.AppDatabase;
import com.inrainbows.persistence.daos.SemesterDao;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * @author diego on 15/07/2018.
 */
public class MainActivity extends AppCompatActivity implements MainContract.View {

    @BindView(R.id.fab_add)
    FloatingActionButton fab;

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

        fab.setOnClickListener(new android.view.View.OnClickListener() {
            @Override
            public void onClick(android.view.View v) {
                presenter.addSemester();
            }
        });

        AppDatabase db = AppDatabase.getDatabase(getApplication());

        presenter = new MainPresenter(this, db.semesterDao());
        
        drawerLayout.setStatusBarBackgroundColor(getColor(R.color.colorPrimaryDark));
        
        if(navigationView != null){
            setupDrawerContent(navigationView);
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
        navigationView.setNavigationItemSelectedListener(
                new NavigationView.OnNavigationItemSelectedListener() {
                    @Override
                    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                        switch (item.getItemId()){
                            case R.id.semesters_drawer_item:
                                Intent intent = new Intent(MainActivity.this, EditSemesterActivity.class);
                                startActivity(intent);
                                break;
                            case R.id.main_activity_drawer_item:
                                break;
                            default:
                                break;
                        }
                        item.setChecked(true);
                        drawerLayout.closeDrawers();
                        return true;
                    }
                }
        );
    }

    private void showSemestersPopupMenu(){
        PopupMenu popup = new PopupMenu(MainActivity.this, new Button(getApplication()) /*Cambiar botón por otro anchor*/);

        for(Semester semester : presenter.getAllSemesters()){
            popup.getMenu().add(semester.getSemesterName());
        }

        popup.show();
    }
}
