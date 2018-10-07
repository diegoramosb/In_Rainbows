package com.inrainbows.mvp.view;

import com.inrainbows.mvp.model.Grade;
import com.inrainbows.mvp.model.Subject;
import com.inrainbows.mvp.presenter.BasePresenter;

import java.util.List;

/**
 * Base contract for EditGrade feature
 * @author diego on 31/07/2018.
 */
public interface EditGradeContract {

    /**
     * Interface for activities to implement
     */
    interface View extends BaseView<EditGradeContract.Presenter> {

        /**
         * Return to main view
         */
        void showMainView();

    }

    /**
     * Presenter for presenters to implement
     */
    interface Presenter extends BasePresenter<EditGradeContract.View> {

        /**
         * Returns the list of all subjects in the database
         * @return list of all subjects in the database
         */
        List<Subject> getAllSubjects();

        /**
         * Inserts a grade in the database
         * @param grade grade to be persisted
         */
        void insertGrade(Grade grade);

    }

}
