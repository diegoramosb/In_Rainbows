package com.inrainbows.mvp.presenter;

import com.inrainbows.mvp.model.Semester;
import com.inrainbows.mvp.view.BaseView;
import com.inrainbows.mvp.view.MainContract;
import com.inrainbows.persistence.daos.SemesterDao;
import com.inrainbows.persistence.entities.SemesterEntity;

import java.util.ArrayList;
import java.util.List;

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
    public List<Semester> getAllSemesters() {
        return semesterEntityListToSemester(dao.getAllList());
    }

    private List<Semester> semesterEntityListToSemester(List<SemesterEntity> semesterEntityList){
        List<Semester> ans = new ArrayList<>();
        for(SemesterEntity semesterEntity : semesterEntityList){
            ans.add(new Semester(semesterEntity));
        }
        return ans;
    }

    @Override
    public void deleteSemester() {

    }

    @Override
    public Semester getSemester(long semesterId) {
        return null;
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
