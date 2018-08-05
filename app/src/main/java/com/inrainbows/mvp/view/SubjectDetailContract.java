package com.inrainbows.mvp.view;

import com.inrainbows.mvp.presenter.BasePresenter;

/**
 * @author diego on 4/08/2018.
 */
public interface SubjectDetailContract {

    interface View extends BaseView<SubjectDetailContract.Presenter> {

    }

    interface Presenter extends BasePresenter<SubjectDetailContract.View> {

    }

}
