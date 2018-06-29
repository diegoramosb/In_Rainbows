package presenter;

import model.Semester;
import view.SemesterView;

/**
 * @author diego on 28/06/2018.
 */
public class SemesterPresenter implements Presenter<SemesterView> {

    private Semester semester;

    private SemesterView view;

    @Override
    public void onCreate() {

    }

    @Override
    public void onStart() {

    }

    @Override
    public void onStop() {

    }

    @Override
    public void onPause() {

    }

    @Override
    public void attachView(SemesterView view) {
        this.view = view;
    }
}
