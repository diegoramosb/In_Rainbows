package com.inrainbows.activity;

import android.app.DialogFragment;
import android.app.Fragment;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.TimePicker;

import com.inrainbows.R;
import com.inrainbows.mvp.model.Subject;
import com.inrainbows.mvp.view.SubjectDetailContract;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * @author diego on 4/08/2018.
 */
public class SubjectDetailActivity extends BaseActivity implements NumberPickerDialogFragment.NumberPickerDialogListener {

    Subject subject;

    @BindView(R.id.tv_subject_detail_name)
    TextView tvSubjectName;

    @BindView(R.id.tv_subject_detail_progress_today)
    TextView tvProgressToday;

    @BindView(R.id.pb_subject_detail_today)
    ProgressBar pbDay;

    SubjectDetailContract.Presenter presenter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Bundle bundle = getIntent().getExtras();
        subject = bundle.getParcelable("subject");

        setContentView(R.layout.subject_detail_act);
        ButterKnife.bind(this);

        tvSubjectName.setText(subject.getName());
        tvProgressToday.setText("LOL");
    }

    @OnClick(R.id.fab_add_time)
    public void onClickFab() {
        showTimePickerDialog();
    }

    private void showTimePickerDialog() {
        NumberPickerDialogFragment dialog = new NumberPickerDialogFragment();
        dialog.show(getSupportFragmentManager(), "numberPickerDialog");
    }

    @Override
    public void saveInput(int hours, int minutes) {
        pbDay.setProgress(pbDay.getProgress() + hours);
        tvProgressToday.setText(hours+"");
    }
}
