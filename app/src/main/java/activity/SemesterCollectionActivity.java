package activity;

import android.support.v7.app.AppCompatActivity;

import java.util.List;

import javax.inject.Inject;

import model.Semester;
import presenter.SemesterPresenter;
import view.SemesterView;

/**
 * @author diego on 28/06/2018.
 */
public class SemesterCollectionActivity extends AppCompatActivity implements SemesterView {

    @Inject
    private SemesterPresenter SemesterPresenter;

    /**
     * Shows loading screen.
     */
    @Override
    public void showLoading() {

    }

    /**
     * Shows error message.
     */
    @Override
    public void showError() {

    }

    /**
     * Shows list of semesters.
     *
     * @param semesters List of semesters to be displayed.
     */
    @Override
    public void showSemesters(List<Semester> semesters) {

    }

    /**
     * Shows an empty list.
     */
    @Override
    public void showEmpty() {

    }
}
