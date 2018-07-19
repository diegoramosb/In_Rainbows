package com.inrainbows.mvp.view;

import java.util.List;

import com.inrainbows.mvp.model.Semester;

/**
 * @author diego on 28/06/2018.
 */
public interface SemesterView extends BaseView {
    /**
     * Shows loading screen.
     */
    void showLoading();

    /**
     * Shows error message.
     */
    void showError();

    /**
     * Shows list of semesters.
     * @param semesters List of semesters to be displayed.
     */
    void showSemesters(List<Semester> semesters);

    /**
     * Shows an empty list.
     */
//    void showEmpty();
}
