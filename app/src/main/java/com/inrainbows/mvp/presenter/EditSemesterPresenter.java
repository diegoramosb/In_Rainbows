package com.inrainbows.mvp.presenter;

import com.inrainbows.activity.MainActivity;
import com.inrainbows.mvp.model.Semester;
import com.inrainbows.mvp.view.EditSemesterContract;
import com.inrainbows.persistence.daos.SemesterDao;
import com.inrainbows.persistence.entities.SemesterEntity;

import java.util.ArrayList;
import java.util.List;

/**
 * Presenter for editing semesters
 * @author diego on 19/07/2018.
 */
public class EditSemesterPresenter implements EditSemesterContract.Presenter {

    /**
     * Semester DAO
     */
    private SemesterDao dao;

    /**
     * Binded view
     */
    private EditSemesterContract.View view;

    /**
     * Creates a new presenter binded to a view
     * @param view binded view
     */
    public EditSemesterPresenter(EditSemesterContract.View view) {
        this.view = view;
        this.view.setPresenter(this);
        this.dao = view.getDb().semesterDao();
    }

    /**
     * Inserts a new semester in the database
     * @param semester new semester
     */
    @Override
    public void insertSemester(Semester semester) {
        dao.insert(semester.toEntity());
    }

    /**
     * Updates a semester in the database
     * @param semester semester to be updated
     */
    @Override
    public void updateSemester(Semester semester) {
        dao.update(semester.toEntity());
    }

    /**
     * Deletes a semester from the database
     * @param semesterId id of the semester to be deleted
     */
    @Override
    public void deleteSemester(long semesterId) {
        dao.delete(dao.getById(semesterId));
    }

    /**
     * Returns the semester with the given id from the database
     * @param semesterId semester id
     * @return semester with the given id from the database
     */
    @Override
    public Semester getSemester(long semesterId) {
        return  new Semester(dao.getById(semesterId));
    }

    /**
     * Returns all semesters from the database
     * @return all semesters from the database
     */
    @Override
    public List<Semester> getAllSemesters() {
        return semesterEntityListToSemester(dao.getAllList());
    }

    /**
     * Returns the current semester from the database
     * @return current semester
     */
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

    /**
     * Sets the given semester as the current semester
     * @param semester semester to be set as current
     */
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

    /**
     * Converts a {@link SemesterEntity} list to a {@link Semester} list
     * @param semesterEntityList list to convert
     * @return a {@link Semester} list with the elements of the given list
     */
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
