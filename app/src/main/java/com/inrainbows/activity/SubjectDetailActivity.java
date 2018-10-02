package com.inrainbows.activity;

import android.arch.lifecycle.Observer;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.inrainbows.R;
import com.inrainbows.mvp.model.Subject;
import com.inrainbows.mvp.view.SubjectDetailContract;


import org.parceler.Parcels;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * @author diego on 4/08/2018.
 */
public class SubjectDetailActivity extends BaseActivity implements SubjectDetailContract.View, NumberPickerDialogFragment.NumberPickerDialogListener {

    Subject subject;

    @BindView(R.id.tv_subject_detail_name)
    TextView tvSubjectName;

    @BindView(R.id.tv_subject_detail_grade)
    TextView tvSubjectGrade;

    @BindView(R.id.tv_subject_detail_progress_today)
    TextView tvProgressToday;

    @BindView(R.id.tv_subject_detail_progress_week)
    TextView tvProgressWeek;

    @BindView(R.id.tv_subject_detail_progress_semester)
    TextView tvProgressSemester;

    @BindView(R.id.pb_subject_detail_today)
    ProgressBar pbDay;

    @BindView(R.id.pb_subject_detail_week)
    ProgressBar pbWeek;

    @BindView(R.id.pb_subject_detail_semester)
    ProgressBar pbSemester;

    @BindView(R.id.fab_modify_time)
    FloatingActionButton fabModifyTime;

    @BindView(R.id.layout_add_time)
    LinearLayout layoutAddTime;

    @BindView(R.id.layout_remove_time)
    LinearLayout layoutRemoveTime;

    SubjectDetailContract.Presenter presenter;

    private boolean isFABOpen;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Bundle bundle = getIntent().getExtras();
        subject = Parcels.unwrap(getIntent().getParcelableExtra("subject"));

        System.out.println(subject);

        setUIComponents();
    }

    /**
     * Initializes the UI components
     */
    @Override
    public void setUIComponents() {
        setContentView(R.layout.subject_detail_act);
        ButterKnife.bind(this);

        isFABOpen = false;
        tvSubjectName.setText(subject.getName());


        tvSubjectGrade.setText(subject.currentGrade()+"");

        Double value = subject.getStudiedHoursDay();
        pbDay.setProgress(Double.valueOf((value/subject.getWeeklyExtraHours()) * 100).intValue(), true);
        tvProgressToday.setText(value+"");

        value = subject.getStudiedHoursWeek();
        pbWeek.setProgress(value.intValue(), true);
        tvProgressWeek.setText(value+"");

        value = subject.getStudiedHoursSemester();
        pbSemester.setProgress(value.intValue(), true);
        tvProgressSemester.setText(value+"");
    }

    @Override
    public void setPresenter(SubjectDetailContract.Presenter presenter) {
        this.presenter = presenter;
    }

    private void subscribeToSubject() {
        final Observer<Subject> subjectObserver = new Observer<Subject>() {
            @Override
            public void onChanged(@Nullable Subject subject) {
                updateUI();
            }
        };

        subjectObserver.onChanged(subject);

        presenter.getSubject().observe(this, subjectObserver);
    }

    private void updateUI() {
        tvSubjectName.setText(subject.getName());
        tvProgressToday.setText(subject.getStudiedHoursDay() + " h/" + subject.dailyHoursString() + " h");
        tvProgressWeek.setText(subject.getStudiedHoursWeek() + "h/" + subject.getWeeklyExtraHours() + " h");
        tvProgressSemester.setText(subject.getStudiedHoursSemester() + " h/" + subject.getSemesterExtraHours() + " h");

        pbDay.setProgress(subject.dailyStudiedPercentage());
        pbWeek.setProgress(subject.weeklyStudiedPercentage());
        pbSemester.setProgress(subject.semesterStudiedPercentage());
    }

    @OnClick(R.id.fab_modify_time)
    public void onClickFab() {
        if(!isFABOpen){
            showFABMenu();
        }
        else {
            closeFABMenu();
        }
    }

    private void showFABMenu(){
        isFABOpen=true;

        layoutAddTime.setVisibility(View.VISIBLE);
        layoutRemoveTime.setVisibility(View.VISIBLE);

        fabModifyTime.animate().rotationBy(90);

        layoutAddTime.animate().translationY(-getResources().getDimension(R.dimen.standard_55));
        layoutRemoveTime.animate().translationY(-getResources().getDimension(R.dimen.standard_105));
    }

    private void closeFABMenu(){
        isFABOpen=false;

        fabModifyTime.animate().rotationBy(-90);

        layoutAddTime.animate().translationY(0);
        layoutRemoveTime.animate().translationY(0);

        layoutAddTime.setVisibility(View.GONE);
        layoutRemoveTime.setVisibility(View.GONE);
    }

    @OnClick(R.id.fab_add_time)
    public void fabAddTimeOnClick() {
        showAddTimeDialog();
    }

    @OnClick(R.id.fab_remove_time)
    public void fabRemoveTimeOnClick() {
        showRemoveTimeDialog();
    }

    private void showRemoveTimeDialog() {
        NumberPickerDialogFragment dialog = new NumberPickerDialogFragment();

        Bundle bundle = new Bundle();
        bundle.putInt("title", NumberPickerDialogFragment.REMOVE);
        dialog.setArguments(bundle);

        closeFABMenu();
        dialog.show(getSupportFragmentManager(), "numberPickerDialog");
    }

    private void showAddTimeDialog() {
        NumberPickerDialogFragment dialog = new NumberPickerDialogFragment();

        Bundle bundle = new Bundle();
        bundle.putInt("title", NumberPickerDialogFragment.ADD);
        dialog.setArguments(bundle);

        closeFABMenu();
        dialog.show(getSupportFragmentManager(), "numberPickerDialog");
    }

    @Override
    public void modifyTime(int hours, int minutes, int mode) {
        if(mode == NumberPickerDialogFragment.ADD) {
            pbDay.setProgress(pbDay.getProgress() + hours);
            tvProgressToday.setText(hours + "");
        }
        else if(mode == NumberPickerDialogFragment.REMOVE){

        }
    }
}
