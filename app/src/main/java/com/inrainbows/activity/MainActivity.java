package com.inrainbows.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;

import com.inrainbows.R;
import com.inrainbows.mvp.presenter.MainPresenter;
import com.inrainbows.mvp.presenter.SemesterPresenter;
import com.inrainbows.mvp.view.MainContract;
import com.inrainbows.persistence.AppDatabase;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * @author diego on 15/07/2018.
 */
public class MainActivity extends AppCompatActivity implements MainContract.View {

    @BindView(R.id.fab_add)
    FloatingActionButton fab;

    private MainContract.Presenter presenter;

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
}
