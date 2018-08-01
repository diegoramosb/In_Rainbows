package com.inrainbows.mvp.presenter;

import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;

import com.inrainbows.mvp.model.Grade;
import com.inrainbows.mvp.model.Semester;
import com.inrainbows.mvp.model.Subject;
import com.inrainbows.mvp.view.MainContract;
import com.inrainbows.persistence.AppDatabase;
import com.inrainbows.persistence.entities.GradeEntity;
import com.inrainbows.persistence.entities.SemesterEntity;
import com.inrainbows.persistence.entities.SubjectEntity;

import java.util.ArrayList;
import java.util.List;

/**
 * @author diego on 15/07/2018.
 */
public class MainPresenter extends ViewModel implements MainContract.Presenter {

//    MainContract.View view;

    private AppDatabase db;

    private MutableLiveData<Semester> currentSemester = new MutableLiveData<>();

    private MutableLiveData<List<Subject>> subjects = new MutableLiveData<>();

    public MainPresenter() {
    }

    @Override
    public void setDb(AppDatabase db) {
        this.db = db;
        getCurrentSemesterFromDb();
        getSubjectsFromDb();
    }

    private void getCurrentSemesterFromDb() {
        SemesterEntity entity = db.semesterDao().getCurrentSemester();
        if(entity != null){
            Semester semester = new Semester(entity);
            List<SubjectEntity> subjectEntities = db.subjectDao().getAllSubjectsWithSemesterId(entity.getId());
            ArrayList<Subject> subjects = new ArrayList<>();
            for(SubjectEntity subject : subjectEntities){
                subjects.add(new Subject(subject));
            }
            semester.setSubjects(subjects);
            currentSemester.setValue(semester);
        }
    }

    private void getSubjectsFromDb(){
        List<Subject> semesterSubjects = subjectEntityListToSubject(db.subjectDao().getAllSubjectsWithSemesterId(currentSemester.getValue().getId()));
        for(Subject subject : semesterSubjects){
            subject.setGrades(gradeEntityListToGrade(db.gradeDao().getAllWithSubjectId(subject.getId())));
        }
        subjects.setValue(semesterSubjects);
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

        getCurrentSemesterFromDb();
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


    private List<Subject> subjectEntityListToSubject(List<SubjectEntity> subjects){
        List<Subject> ans = new ArrayList<>();
        for(SubjectEntity entity : subjects){
            ans.add(new Subject(entity));
        }
        return ans;
    }

    private List<Grade> gradeEntityListToGrade(List<GradeEntity> entities){
        List<Grade> ans = new ArrayList<>();
        for(GradeEntity entity : entities){
            ans.add(new Grade(entity));
        }
        return ans;
    }

    @Override
    public MutableLiveData<List<Subject>> getSubjects() {
        return subjects;
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
