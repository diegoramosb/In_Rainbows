package com.inrainbows.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.BindViews;

import com.inrainbows.mvp.model.Semester;
import com.inrainbows.mvp.view.SemesterView;
import com.inrainbows.mvp.presenter.SemesterPresenter;
import com.inrainbows.R;

import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * @author diego on 28/06/2018.
 */
public class SemesterCollectionActivity extends AppCompatActivity implements SemesterView {

    @Inject
    SemesterPresenter SemesterPresenter;

    @BindView(R.id.lvSemesters)
    ListView lvSemesters;

    @BindView(R.id.btnNewSemester)
    Button btnNewSemester;

    @BindViews({R.id.etName, R.id.etStartDate, R.id.etEndDate})
    List<EditText> editTexts;

    ArrayList<Semester> semesters;

    ArrayAdapter<Semester> semesterArrayAdapter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_semesters_collection);
        ButterKnife.bind(this);

        semesters = new ArrayList<>();

        editTexts.get(0).setText(R.string.sample_semester_name);
        editTexts.get(1).setText(R.string.sample_start_date);
        editTexts.get(2).setText(R.string.sample_end_date);

        semesterArrayAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, semesters);
    }

    @Override
    protected void onStop() {
        super.onStop();
    }

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

    @OnClick(R.id.btnNewSemester)
    public void addSemester(){
        //Por ahora formato de fechas YYYY-MM-DD
        String[] startDate = editTexts.get(1).getText().toString().split("-");
        String[] endDate = editTexts.get(2).getText().toString().split("-");

        Semester semester = new Semester.SemesterBuilder(editTexts.get(0).getText().toString(), Integer.parseInt(startDate[0]), Integer.parseInt(startDate[1]), Integer.parseInt(startDate[2]), Integer.parseInt(endDate[0]), Integer.parseInt(endDate[1]), Integer.parseInt(endDate[2])).build();
        semesterArrayAdapter.add(semester);


        lvSemesters.setAdapter(semesterArrayAdapter);
    }
}
