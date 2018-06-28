package view;

import java.util.List;
import model.Task;

/**
 * @author diego on 2/06/2018.
 */
public interface TaskView extends View {

    void showTasks(List<Task> tasks);

    void showLoading();

    void showError();
}
