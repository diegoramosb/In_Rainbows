package com.inrainbows.mvp.view;

import com.inrainbows.mvp.model.Subject;
import com.inrainbows.mvp.presenter.BasePresenter;

/**
 * Contract for EditSubject classes to implement
 * @author diego on 22/07/2018.
 */
public interface EditSubjectContract {

    /**
     * Interface for activities to implement
     */
    interface View extends BaseView<Presenter> {

        /**
         * Returns to the main view
         */
        void showMainView();

    }

    /**
     * Interface for presenters to implement
     */
    interface Presenter extends BasePresenter<View> {

        /**
         * Inserts the given subject in the database
         * @param subject subject to insert
         */
        void insertSubject(Subject subject);

    }

}
