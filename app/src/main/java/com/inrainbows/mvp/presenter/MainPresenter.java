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
 * Presenter of the {@link com.inrainbows.activity.MainActivity}
 * @author diego on 15/07/2018.
 */
public class MainPresenter extends ViewModel implements MainContract.Presenter {

//    MainContract.View view;

    /**
     * App database
     */
    private AppDatabase db;

    /**
     * MutableLiveData for the current semester
     */
    private MutableLiveData<Semester> currentSemester = new MutableLiveData<>();

    /**
     * MutableLiveDate for the subjects of the semester
     */
    private MutableLiveData<List<Subject>> subjects = new MutableLiveData<>();

    /**
     * Constructor
     */
    public MainPresenter() {
        //This is an empty constructor
    }

    /**
     * Sets the app database to the one given. It also gets the current semester and subjects
     * @param db given app database
     */
    @Override
    public void setDb(AppDatabase db) {
        this.db = db;
        getCurrentSemesterFromDb();
        getSubjectsFromDb();
    }

    /**
     * Gets the current semester from the DB and sets it as such
     */
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

    /**
     * Gets the subjects of the current semester and sets it as such
     */
    private void getSubjectsFromDb(){
        if(currentSemester.getValue() != null) {
            List<Subject> semesterSubjects = subjectEntityListToSubject(db.subjectDao().getAllSubjectsWithSemesterId(currentSemester.getValue().getId()));
            for (Subject subject : semesterSubjects) {
                subject.setGrades(gradeEntityListToGrade(db.gradeDao().getAllWithSubjectId(subject.getId())));
            }
            subjects.setValue(semesterSubjects);
        }
    }

    /**
     * Sets the given semester as the current semester in the DB
     * @param semester semester to be set as current
     */
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

    /**
     * Returns the current semester
     * @return the current semester
     */
    @Override
    public MutableLiveData<Semester> getCurrentSemester() {
        return currentSemester;
    }

    /**
     * Returns the list of semesters from the DB
     * @return
     */
    @Override
    public List<Semester> getAllSemesters() {
        return semesterEntityListToSemester(db.semesterDao().getAllList());
    }

    /**
     * Converts a list of {@link SemesterEntity} to a list of {@link Semester}
     * @param semesterEntityList entity list
     * @return a list of Semesters with the elements of the SemesterEntity list
     */
    private List<Semester> semesterEntityListToSemester(List<SemesterEntity> semesterEntityList){
        List<Semester> ans = new ArrayList<>();
        for(SemesterEntity semesterEntity : semesterEntityList){
            ans.add(new Semester(semesterEntity));
        }
        return ans;
    }

    /**
     * Converts a list of {@link SubjectEntity} to a list of {@link Subject}
     * @param subjects entity list
     * @return a list of Subjects with the elements of the SubjectEntity list
     */
    private List<Subject> subjectEntityListToSubject(List<SubjectEntity> subjects){
        List<Subject> ans = new ArrayList<>();
        for(SubjectEntity entity : subjects){
            ans.add(new Subject(entity));
        }
        return ans;
    }

    /**
     * Converts a list of {@link GradeEntity} to a list of {@link Grade}
     * @param entities entity list
     * @return a list of Grades with the elements of the GradeEntity list
     */
    private List<Grade> gradeEntityListToGrade(List<GradeEntity> entities){
        List<Grade> ans = new ArrayList<>();
        for(GradeEntity entity : entities){
            ans.add(new Grade(entity));
        }
        return ans;
    }

    /**
     * Returns the subject list of the current semester
     * @return
     */
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
