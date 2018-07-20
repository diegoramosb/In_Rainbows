package com.inrainbows.mvp.view;

import com.inrainbows.mvp.model.Semester;
import com.inrainbows.mvp.presenter.BasePresenter;

import java.util.List;

/**
 * @author diego on 15/07/2018.
 *
 * Contract for the main view and presenter, which are in charge of showing the most important
 * data as well as accessing other views.
 */
public interface MainContract {

    interface Presenter extends BasePresenter<MainContract.View>{

        void showAddSemesterView();

        void updateCurrentSemesterName();

        void setCurrentSemester(Semester semester);

        List<String> getAllSemesterNames();

        List<Semester> getAllSemesters();

    }

    interface View extends BaseView<MainContract.Presenter>{

        void showAddSemester();

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
