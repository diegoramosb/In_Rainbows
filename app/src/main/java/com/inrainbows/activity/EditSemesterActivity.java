package com.inrainbows.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.EditText;
import android.widget.TextView;

import com.inrainbows.InRainbowsApp;
import com.inrainbows.R;
import com.inrainbows.mvp.model.Semester;
import com.inrainbows.mvp.presenter.BasePresenter;
import com.inrainbows.mvp.presenter.EditSemesterPresenter;
import com.inrainbows.mvp.view.EditSemesterContract;
import com.inrainbows.persistence.AppDatabase;

import org.joda.time.DateTime;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * @author diego on 15/07/2018.
 */
public class EditSemesterActivity extends BaseActivity implements EditSemesterContract.View {

    EditSemesterContract.Presenter presenter;

    @BindView(R.id.tvSemesterName)
    TextView tvSemesterName;

    @BindView(R.id.tvStartDate)
    TextView tvStartDate;

    @BindView(R.id.tvEndDate)
    TextView tvEndDate;

    @BindView(R.id.etSemesterName)
    EditText etSemesterName;

    @BindView(R.id.etStartDate)
    EditText etStartDate;

    @BindView(R.id.etEndDate)
    EditText etEndDate;

    @Override
    public  void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.edit_semester_act);
        ButterKnife.bind(this);

        this.presenter = new EditSemesterPresenter(this);

        System.out.println(presenter.getAllSemesters());

        etSemesterName.setText(R.string.sample_semester_name);
        etStartDate.setText(R.string.sample_start_date);
        etEndDate.setText(R.string.sample_end_date);
    }

    @OnClick(R.id.fab_check_semester)
    public  void insertSemester(){
        Semester semester = new Semester.SemesterBuilder(0L, etSemesterName.getText().toString(),
                new DateTime(etStartDate.getText().toString()),
                new DateTime(etEndDate.getText().toString()),
                presenter.getCurrentSemester() == null).build();
        showMainView();
    }

    @Override
    public void setPresenter(EditSemesterContract.Presenter presenter) {
        this.presenter = presenter;
    }

    @Override
    public void showMainView() {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }

    @Override
    public void showError() {

    }

    @Override
    public void showLoading() {

    }

    @Override
    public void showEmpty() {

    }
}
