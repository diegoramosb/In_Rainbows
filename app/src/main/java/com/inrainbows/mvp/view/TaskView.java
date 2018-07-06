package com.inrainbows.mvp.view;

import android.view.View;

import java.util.List;
import com.inrainbows.mvp.model.SubjectTask;

/**
 * @author diego on 2/06/2018.
 */
public interface TaskView extends View {

    void showTasks(List<SubjectTask> tasks);

    void showLoading();

    void showError();
}
