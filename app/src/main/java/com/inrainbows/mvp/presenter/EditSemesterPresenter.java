package com.inrainbows.mvp.presenter;

import com.inrainbows.activity.MainActivity;
import com.inrainbows.mvp.model.Semester;
import com.inrainbows.mvp.view.EditSemesterContract;
import com.inrainbows.persistence.daos.SemesterDao;
import com.inrainbows.persistence.entities.SemesterEntity;

import java.util.ArrayList;
import java.util.List;

/**
 * @author diego on 19/07/2018.
 */
public class EditSemesterPresenter implements EditSemesterContract.Presenter {

    private SemesterDao dao;

    private EditSemesterContract.View view;

    public EditSemesterPresenter(EditSemesterContract.View view) {
        this.view = view;
        this.view.setPresenter(this);
        this.dao = view.getDb().semesterDao();
    }

    @Override
    public void insertSemester(Semester semester) {
        dao.insert(semester.toEntity());
    }

    @Override
    public void updateSemester(Semester semester) {
        dao.update(semester.toEntity());
    }

    @Override
    public void deleteSemester(long semesterId) {
        dao.delete(dao.getById(semesterId));
    }

    @Override
    public Semester getSemester(long semesterId) {
        return  new Semester(dao.getById(semesterId));
    }

    @Override
    public List<Semester> getAllSemesters() {
        return semesterEntityListToSemester(dao.getAllList());
    }

    @Override
    public Semester getCurrentSemester() {
        SemesterEntity entity = dao.getCurrentSemester();
        if(entity != null){
            return new Semester(dao.getCurrentSemester());
        }
        else {
            return null;
        }
    }

    @Override
    public void setCurrentSemester(Semester semester) {
        Semester oldSemester = getCurrentSemester();
        if(oldSemester != null) {
            oldSemester.setCurrentSemester(false);
            updateSemester(oldSemester);
        }

        semester.setCurrentSemester(true);
        updateSemester(semester);
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
