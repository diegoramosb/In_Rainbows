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
 * @author diego on 31/07/2018.
 */
public class EditGradePresenter implements EditGradeContract.Presenter {

    private GradeDao gradeDao;

    private SubjectDao subjectDao;

    private EditGradeContract.View view;

    public EditGradePresenter(EditGradeContract.View view){
        this.view = view;
        this.gradeDao = view.getDb().gradeDao();
        this.subjectDao = view.getDb().subjectDao();
    }

    @Override
    public void onCreate() {

    }

    @Override
    public void insertGrade(Grade grade) {
        gradeDao.insert(grade.toEntity());
    }

    @Override
    public List<Subject> getAllSubjects() {
        return subjectEntityToSubjectList(subjectDao.getAllList());
    }

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
