package com.inrainbows.mvp.presenter;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;

import com.inrainbows.mvp.model.Grade;
import com.inrainbows.mvp.model.Semester;
import com.inrainbows.mvp.model.Subject;
import com.inrainbows.mvp.view.SubjectDetailContract;
import com.inrainbows.persistence.AppDatabase;
import com.inrainbows.persistence.daos.SubjectDao;
import com.inrainbows.persistence.entities.GradeEntity;
import com.inrainbows.persistence.entities.SubjectEntity;

import java.util.ArrayList;
import java.util.List;

/**
 * @author diego on 6/08/2018.
 */
public class SubjectDetailPresenter implements SubjectDetailContract.Presenter {

    MutableLiveData<Subject> subject;

    AppDatabase db;

    @Override
    public void onCreate() {
    }

    @Override
    public void setDb(AppDatabase db) {
        this.db = db;
    }

    @Override
    public MutableLiveData<Subject> getSubject() {
        return subject;
    }

    private void getSubjectFromDb() {
        if(subject != null){
            subject.setValue(new Subject(db.subjectDao().get(subject.getValue().getId())));
        }
        List<Grade> grades = gradeEntityListToGrade(db.gradeDao().getAllWithSubjectId(subject.getValue().getId()));
        subject.getValue().setGrades(grades);
    }

    private List<Grade> gradeEntityListToGrade(List<GradeEntity> entities) {
        List<Grade> ans = new ArrayList<>();
        for(GradeEntity entity : entities){
            ans.add(new Grade(entity));
        }
        return ans;
    }

    @Override
    public void updateSubject(Subject subject) {
        db.subjectDao().update(subject.toEntity());
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
