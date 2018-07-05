package com.diegoramos.mvp.view;

import java.util.List;
import com.diegoramos.mvp.model.SubjectTask;

/**
 * @author diego on 2/06/2018.
 */
public interface TaskView extends View {

    void showTasks(List<SubjectTask> tasks);

    void showLoading();

    void showError();
}
