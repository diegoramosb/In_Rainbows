package com.inrainbows.mvp.view;

import android.arch.lifecycle.MutableLiveData;

import com.inrainbows.mvp.model.Subject;
import com.inrainbows.mvp.presenter.BasePresenter;
import com.inrainbows.persistence.AppDatabase;
import com.inrainbows.persistence.daos.SubjectDao;

/**
 * @author diego on 4/08/2018.
 */
public interface SubjectDetailContract {

    interface View extends BaseView<SubjectDetailContract.Presenter> {

    }

    interface Presenter extends BasePresenter<SubjectDetailContract.View> {

        void setDb(AppDatabase db);

        MutableLiveData<Subject> getSubject();

        void updateSubject(Subject subejct);

    }

}
