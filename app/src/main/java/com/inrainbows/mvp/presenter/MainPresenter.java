package com.inrainbows.mvp.presenter;

import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;
import android.os.SystemClock;

import com.inrainbows.mvp.model.Semester;
import com.inrainbows.mvp.model.Subject;
import com.inrainbows.mvp.view.BaseView;
import com.inrainbows.mvp.view.MainContract;
import com.inrainbows.persistence.AppDatabase;
import com.inrainbows.persistence.daos.SemesterDao;
import com.inrainbows.persistence.entities.SemesterEntity;
import com.inrainbows.persistence.entities.SubjectEntity;

import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

/**
 * @author diego on 15/07/2018.
 */
public class MainPresenter extends ViewModel implements MainContract.Presenter {

//    MainContract.View view;

    AppDatabase db;

    private MutableLiveData<Semester> currentSemester = new MutableLiveData<>();

    private MutableLiveData<Long> time = new MutableLiveData<>();

//    public MainPresenter(MainContract.View view) {
//        this.view = view;
//        this.view.setPresenter(this);
//        db = view.getDb();
//    }


    public MainPresenter() {
    }

    @Override
    public void setDb(AppDatabase db) {
        this.db = db;
        currentSemester();
    }

    @Override
    public void currentSemester() {
        SemesterEntity entity = db.semesterDao().getCurrentSemester();
        if(entity != null){
            Semester semester = new Semester(entity);
            List<SubjectEntity> subjectEntities = db.subjectDao().getAllSubjectsWithSemesterId(entity.getId());
            ArrayList<Subject> subjects = new ArrayList<>();
            for(SubjectEntity subject : subjectEntities){
                subjects.add(new Subject(subject));
            }
            semester.setSubjects(subjects);
            currentSemester.postValue(semester);
        }
    }

    @Override
    public void setCurrentSemester(Semester semester) {
        Semester current = currentSemester.getValue();
        if(current != null) {
            current.setCurrentSemester(false);
            db.semesterDao().update(currentSemester.getValue().toEntity());
        }

        semester.setCurrentSemester(true);
        db.semesterDao().update(semester.toEntity());

        currentSemester();
    }


    @Override
    public MutableLiveData<Semester> getCurrentSemester() {
        return currentSemester;
    }

    @Override
    public List<Semester> getAllSemesters() {
        return semesterEntityListToSemester(db.semesterDao().getAllList());
    }

    private List<Semester> semesterEntityListToSemester(List<SemesterEntity> semesterEntityList){
        List<Semester> ans = new ArrayList<>();
        for(SemesterEntity semesterEntity : semesterEntityList){
            ans.add(new Semester(semesterEntity));
        }
        return ans;
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
