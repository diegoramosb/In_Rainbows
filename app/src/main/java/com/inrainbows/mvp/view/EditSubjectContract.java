package com.inrainbows.mvp.view;

import com.inrainbows.mvp.model.Subject;
import com.inrainbows.mvp.presenter.BasePresenter;

/**
 * @author diego on 22/07/2018.
 */
public interface EditSubjectContract {

    interface View extends BaseView<Presenter> {

        void showMainView();

    }

    interface Presenter extends BasePresenter<View> {

        void insertSubject(Subject subject);

    }

}
