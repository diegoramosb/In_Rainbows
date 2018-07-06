package com.inrainbows.mvp.view;

import java.util.List;

import com.inrainbows.mvp.model.Subject;

/**
 * @author diego on 2/06/2018.
 */
public interface SubjectView extends View {
    void showSubjects(List<Subject> subjects);
    void showLoading();
    void showError();
}
