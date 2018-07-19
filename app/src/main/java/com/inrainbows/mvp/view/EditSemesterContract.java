package com.inrainbows.mvp.view;

import com.inrainbows.mvp.model.Semester;
import com.inrainbows.mvp.presenter.BasePresenter;

import java.util.List;

/**
 * @author diego on 19/07/2018.
 */
public interface EditSemesterContract {

    interface View extends BaseView<EditSemesterContract.Presenter> {

        void showMainView();

    }

    interface Presenter extends BasePresenter<EditSemesterContract.View> {

        void insertSemester(Semester semester);

        void updateSemester(Semester semester);

        void deleteSemester(long semesterId);

        Semester getSemester(long semesterId);

        List<Semester> getAllSemesters();

        Semester getCurrentSemester();

        void setCurrentSemester(Semester semester);

    }

}
