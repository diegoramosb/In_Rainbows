package com.inrainbows.mvp.presenter;

import android.os.AsyncTask;

import com.inrainbows.mvp.model.Semester;
import com.inrainbows.mvp.model.Subject;
import com.inrainbows.mvp.view.SemesterView;
import com.inrainbows.persistence.daos.SemesterDao;
import com.inrainbows.persistence.entities.SemesterEntity;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @author diego on 28/06/2018.
 */
public class SemesterPresenter implements Presenter<SemesterView> {

    private SemesterView view;

    private SemesterDao dao;

    public SemesterPresenter(SemesterView view, SemesterDao dao) {
        this.view = view;
        this.dao = dao;
    }

    public void insertSemester(Semester semester){
        dao.insert(semester.toEntity());
    }

    public Semester getSemester(long id){
        return new Semester(dao.getById(id));
    }

    public void updateSemester(Semester semester){
        dao.update(semester.toEntity());
    }

    public List<Semester> getAllSemesters(){
        List<Semester> ans = new ArrayList<>();
        for(SemesterEntity entity : dao.getAllList()){
            ans.add(new Semester(entity));
        }
        return ans;
    }

//    private void addSubjectsToSemester(Semester semester){
//        List<Subject> subjects = dao;
//    }

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

    @Override
    public void attachView(SemesterView view) {
        this.view = view;
    }
}
