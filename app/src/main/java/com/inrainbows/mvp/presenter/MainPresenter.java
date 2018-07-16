package com.inrainbows.mvp.presenter;

import com.inrainbows.mvp.view.BaseView;
import com.inrainbows.mvp.view.MainContract;
import com.inrainbows.persistence.daos.SemesterDao;

/**
 * @author diego on 15/07/2018.
 */
public class MainPresenter implements MainContract.Presenter {

    MainContract.View view;

    SemesterDao dao;

    public MainPresenter(MainContract.View view, SemesterDao dao) {
        this.view = view;
        this.view.setPresenter(this);
        this.dao = dao;
    }

    @Override
    public void addSemester() {
        view.showAddSemester();
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
