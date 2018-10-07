package com.inrainbows.mvp.view;

import com.inrainbows.InRainbowsApp;
import com.inrainbows.mvp.model.Semester;
import com.inrainbows.mvp.presenter.BasePresenter;

import java.util.List;

/**
 * Contract ForEditSemester to implement
 * @author diego on 19/07/2018.
 */
public interface EditSemesterContract {

    /**
     * Intefce for activities to implement
     */
    interface View extends BaseView<EditSemesterContract.Presenter> {

        /**
         * Returns to the main view
         */
        void showMainView();

    }

    /**
     * Interface for presenters to implement
     */
    interface Presenter extends BasePresenter<EditSemesterContract.View> {

        /**
         * Inserts a new semester in the database
         * @param semester new semester
         */
        void insertSemester(Semester semester);

        /**
         * Updates a semester in the database
         * @param semester semester to be updated
         */
        void updateSemester(Semester semester);

        /**
         * Deletes a semester from the database
         * @param semesterId id of the semester to be deleted
         */
        void deleteSemester(long semesterId);

        /**
         * Returns the semester with the given id from the database
         * @param semesterId semester id
         * @return semester with the given id from the database
         */
        Semester getSemester(long semesterId);

        /**
         * Returns all semesters from the database
         * @return all semesters from the database
         */
        List<Semester> getAllSemesters();

        /**
         * Returns the current semester from the database
         * @return current semester
         */
        Semester getCurrentSemester();

        /**
         * Sets the given semester as the current semester
         * @param semester semester to be set as current
         */
        void setCurrentSemester(Semester semester);

    }

}
