package com.inrainbows.mvp.view;

import com.inrainbows.mvp.model.Grade;
import com.inrainbows.mvp.model.Subject;
import com.inrainbows.mvp.presenter.BasePresenter;

import java.util.List;

/**
 * @author diego on 31/07/2018.
 */
public interface EditGradeContract {

    interface View extends BaseView<EditGradeContract.Presenter> {

        void showMainView();

    }

    interface Presenter extends BasePresenter<EditGradeContract.View> {

        List<Subject> getAllSubjects();

        void insertGrade(Grade grade);

    }

}
