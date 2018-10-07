package com.inrainbows.mvp.view;

import android.arch.lifecycle.MutableLiveData;

import com.inrainbows.mvp.model.Semester;
import com.inrainbows.mvp.model.Subject;
import com.inrainbows.mvp.presenter.BasePresenter;
import com.inrainbows.persistence.AppDatabase;

import java.util.List;

/**
 * @author diego on 15/07/2018.
 *
 * Contract for the main view and presenter, which are in charge of showing the most important
 * data as well as accessing other views.
 */
public interface MainContract {

    /**
     * Interface for the main activity of the application to implement
     */
    interface View extends BaseView<MainContract.Presenter>{

        /**
         * Shows the addSemesterActivity activity
         */
        void showAddSemesterActivity();

        /**
         * Shows the AddSubjectActivity activity
         */
        void showAddSubjectActivity();

        /**
         * Shows the AddGradeActivity activity
         */
        void showAddGradeActivity();

        /**
         * Sets the given semester as the activity's current semester
         * @param semester new current semester
         */
        void setCurrentSemester(Semester semester);

        /**
         * Sets the current semester label to the given string
         * @param semesterName semester name or other string
         */
        void setCurrentSemesterName(String semesterName);

    }

    /**
     * Interface to be extended by the main presenter
     */
    interface Presenter extends BasePresenter<MainContract.View>{

        /**
         * Returns a MutableLiveData with the current semster
         * @return current semester
         */
        MutableLiveData<Semester> getCurrentSemester();

        /**
         *
         * @param semester
         */
        void setCurrentSemester(Semester semester);

        List<Semester> getAllSemesters();

        MutableLiveData<List<Subject>> getSubjects();

        void setDb(AppDatabase db);

    }
}
