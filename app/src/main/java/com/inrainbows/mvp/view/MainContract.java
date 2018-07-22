package com.inrainbows.mvp.view;

import android.arch.lifecycle.MutableLiveData;

import com.inrainbows.mvp.model.Semester;
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

    interface Presenter extends BasePresenter<MainContract.View>{

        void currentSemester();

        MutableLiveData<Semester> getCurrentSemester();

        void setCurrentSemester(Semester semester);

        List<Semester> getAllSemesters();

        void setDb(AppDatabase db);

    }

    interface View extends BaseView<MainContract.Presenter>{

        void showAddSemesterActivity();

        void setCurrentSemesterName(String name);


    }

    interface OnItemClickListener {

//        void onClickItem(Semester semester);
//
//        void onClickLongItem(Semester semester);

    }

    interface DeleteListener {

//        void setConfirm(boolean confirm, Semester semester);

    }

}
