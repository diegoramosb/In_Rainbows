package com.inrainbows.mvp.presenter;

import com.inrainbows.mvp.model.Semester;
import com.inrainbows.mvp.view.BaseView;
import com.inrainbows.mvp.view.MainContract;
import com.inrainbows.persistence.AppDatabase;
import com.inrainbows.persistence.daos.SemesterDao;
import com.inrainbows.persistence.entities.SemesterEntity;

import java.util.ArrayList;
import java.util.List;

/**
 * @author diego on 15/07/2018.
 */
public class MainPresenter implements MainContract.Presenter {

    MainContract.View view;

    AppDatabase db;

    public MainPresenter(MainContract.View view) {
        this.view = view;
        this.view.setPresenter(this);
        db = view.getDb();
    }

    @Override
    public void showAddSemesterView() {
        view.showAddSemester();
    }

    @Override
    public void setCurrentSemesterName() {
        System.out.println(db.semesterDao().getAllList());
        SemesterEntity entity = db.semesterDao().getCurrentSemester();
        if(entity != null) {
            Semester currentSemester = new Semester(entity);
            view.setCurrentSemesterName(currentSemester.getSemesterName());
        }
        else {
            view.setCurrentSemesterName("No semester");
        }
    }

    @Override
    public void onCreate() {
    }

    @Override
    public void onStart() {

    }

    @Override
    public void onStop() {

    }

    @Override
    public void onPause() {

    }
}
