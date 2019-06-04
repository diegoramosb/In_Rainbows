package com.inrainbows.mvp.presenter;

import com.inrainbows.mvp.model.Grade;
import com.inrainbows.mvp.model.Subject;
import com.inrainbows.mvp.view.EditGradeContract;
import com.inrainbows.persistence.daos.GradeDao;
import com.inrainbows.persistence.daos.SubjectDao;
import com.inrainbows.persistence.entities.SubjectEntity;

import java.util.ArrayList;
import java.util.List;

/**
 * Presenter for editing grades
 * @author diego on 31/07/2018.
 */
public class EditGradePresenter implements EditGradeContract.Presenter {

    /**
     * Grade DAO
     */
    private GradeDao gradeDao;

    /**
     * Subject DAO
     */
    private SubjectDao subjectDao;

    /**
     * Binded view
     */
    private EditGradeContract.View view;

    /**
     * Creates a new presenter binded to a view
     * @param view binded view
     */
    public EditGradePresenter(EditGradeContract.View view){
        this.view = view;
        this.gradeDao = view.getDb().gradeDao();
        this.subjectDao = view.getDb().subjectDao();
    }

    @Override
    public void onCreate() {

    }

    /**
     * Inserts a new grade in the DB
     * @param grade grade to be persisted
     */
    @Override
    public void insertGrade(Grade grade) {
        gradeDao.insert(grade.toEntity());
    }

    /**
     * Returns the list of all {@link Subject} in the database
     * @return list of all subjects in the database
     */
    @Override
    public List<Subject> getAllSubjects() {
        return subjectEntityToSubjectList(subjectDao.getAllList());
    }

    /**
     * Converts a {@link SubjectEntity} list to a {@link Subject} list
     * @param entities
     * @return
     */
    private List<Subject> subjectEntityToSubjectList(List<SubjectEntity> entities){
        List<Subject> ans = new ArrayList<>();
        for(SubjectEntity subject : entities){
            ans.add(new Subject(subject));
        }
        return ans;
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
