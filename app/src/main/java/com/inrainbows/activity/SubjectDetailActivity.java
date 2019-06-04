package com.inrainbows.activity;

import android.arch.lifecycle.Observer;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.inrainbows.R;
import com.inrainbows.activity.fragments.NumberPickerDialogFragment;
import com.inrainbows.mvp.model.Subject;
import com.inrainbows.mvp.view.SubjectDetailContract;


import org.parceler.Parcels;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Activity that shows the details of a subject
 * @author diego on 4/08/2018.
 */
public class SubjectDetailActivity extends BaseActivity implements SubjectDetailContract.View, NumberPickerDialogFragment.NumberPickerDialogListener {

    /**
     * Subject to be displayed
     */
    Subject subject;

    /**
     * Text view for the subject name
     */
    @BindView(R.id.tv_subject_detail_name)
    TextView tvSubjectName;

    /**
     * Text view for the subject grade
     */
    @BindView(R.id.tv_subject_detail_grade)
    TextView tvSubjectGrade;

    /**
     * Text view for the subject study progress today
     */
    @BindView(R.id.tv_subject_detail_progress_today)
    TextView tvProgressToday;

    /**
     * Text view for the subject weekly study progress
     */
    @BindView(R.id.tv_subject_detail_progress_week)
    TextView tvProgressWeek;

    /**
     * Text view for the subject progress in the semester
     */
    @BindView(R.id.tv_subject_detail_progress_semester)
    TextView tvProgressSemester;

    /**
     * Progress bar for the subject study progress today
     */
    @BindView(R.id.pb_subject_detail_today)
    ProgressBar pbDay;

    /**
     * Progress bar for the subject weekly study progress
     */
    @BindView(R.id.pb_subject_detail_week)
    ProgressBar pbWeek;

    /**
     * Progress bar for the subject progress in the semester
     */
    @BindView(R.id.pb_subject_detail_semester)
    ProgressBar pbSemester;

    /**
     * FAB to modify the subject study time
     */
    @BindView(R.id.fab_modify_time)
    FloatingActionButton fabModifyTime;

    /**
     * Layout that contains the add time FAB and label
     */
    @BindView(R.id.layout_add_time)
    LinearLayout layoutAddTime;

    /**
     * Layout that contains the remove time FAB and label
     */
    @BindView(R.id.layout_remove_time)
    LinearLayout layoutRemoveTime;

    /**
     * Presenter that the activity will use
     */
    SubjectDetailContract.Presenter presenter;

    /**
     * Boolean to indicate if the FAB menu is open
     */
    private boolean isFABOpen;

    /**
     * Initializes the activity
     * @param savedInstanceState state parameters
     */
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        subject = Parcels.unwrap(getIntent().getParcelableExtra("subject"));

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

        Double value = subject.studiedMinutesToHours(subject.studiedMinutesToday());
        pbDay.setProgress(Double.valueOf((value/subject.getWeeklyExtraHours()) * 100).intValue(), true);
        tvProgressToday.setText(value+"");

        value = subject.studiedMinutesToHours(subject.studiedMinutesThisWeek());
        pbWeek.setProgress(value.intValue(), true);
        tvProgressWeek.setText(value+"");

        value = subject.studiedMinutesToHours(subject.studiedMinutesThisSemester());
        pbSemester.setProgress(value.intValue(), true);
        tvProgressSemester.setText(value+"");
    }

    /**
     * Binds the presenter to the view
     * @param presenter presenter that the view will use
     */
    @Override
    public void setPresenter(SubjectDetailContract.Presenter presenter) {
        this.presenter = presenter;
    }


    private void subscribeToSubject() {
        final Observer<Subject> subjectObserver = (subject) -> {
            updateUI();
        };

        subjectObserver.onChanged(subject);

        presenter.getSubject().observe(this, subjectObserver);
    }

    /**
     * Updates the UI elements
     */
    private void updateUI() {
        //TODO: use the observer here?
        tvSubjectName.setText(subject.getName());
        tvProgressToday.setText(subject.studiedMinutesToHours(subject.studiedMinutesToday()) + " h/" + subject.dailyHoursString() + " h");
        tvProgressWeek.setText(subject.studiedMinutesToHours(subject.studiedMinutesThisWeek()) + "h/" + subject.getWeeklyExtraHours() + " h");
        tvProgressSemester.setText(subject.studiedMinutesToHours(subject.studiedMinutesThisSemester()) + " h/" + subject.getSemesterExtraHours() + " h");

        pbDay.setProgress(subject.dailyStudiedPercentage());
        pbWeek.setProgress(subject.weeklyStudiedPercentage());
        pbSemester.setProgress(subject.semesterStudiedPercentage());
    }

    /**
     * Starts a timer to log study time to the subject
     * @param durationMillis
     */
    private void startTimer(long durationMillis) {
        CountDownTimer timer = new CountDownTimer(durationMillis, 1000) {
            @Override
            public void onTick(long millisLeft) {
                System.out.println(millisLeft);
            }

            @Override
            public void onFinish() {
                System.out.println("done!");
            }
        }.start();
    }

    /**
     * Opens the FAB menu to modify the study time
     */
    @OnClick(R.id.fab_modify_time)
    public void onClickFab() {
        if(!isFABOpen){
            showFABMenu();
        }
        else {
            closeFABMenu();
        }
    }

    /**
     * Shows the FAB menu
     */
    private void showFABMenu(){
        isFABOpen=true;

        layoutAddTime.setVisibility(View.VISIBLE);
        layoutRemoveTime.setVisibility(View.VISIBLE);

        fabModifyTime.animate().rotationBy(90);

        layoutAddTime.animate().translationY(-getResources().getDimension(R.dimen.standard_55));
        layoutRemoveTime.animate().translationY(-getResources().getDimension(R.dimen.standard_105));
    }

    /**
     * Closes the FAB menu
     */
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
        //TODO: add time or start timer
//        showAddTimeDialog();
        startTimer(10000);
    }

    /**
     * Method called when the remove time FAB is tapped
     */
    @OnClick(R.id.fab_remove_time)
    public void fabRemoveTimeOnClick() {
        showRemoveTimeDialog();
    }

    /**
     * Shows the remove time dialog
     */
    private void showRemoveTimeDialog() {
        NumberPickerDialogFragment dialog = new NumberPickerDialogFragment();

        Bundle bundle = new Bundle();
        bundle.putInt("title", NumberPickerDialogFragment.REMOVE);
        dialog.setArguments(bundle);

        closeFABMenu();
        dialog.show(getSupportFragmentManager(), "numberPickerDialog");
    }

    /**
     * Shows the add time dialog
     */
    private void showAddTimeDialog() {
        NumberPickerDialogFragment dialog = new NumberPickerDialogFragment();

        Bundle bundle = new Bundle();
        bundle.putInt("title", NumberPickerDialogFragment.ADD);
        dialog.setArguments(bundle);

        closeFABMenu();
        dialog.show(getSupportFragmentManager(), "numberPickerDialog");
    }

    /**
     * Modifies the studied time of the subject
     * @param hours hours to add or remove
     * @param minutes minutes to add or remove
     * @param mode either NumberPickerDialogFragment.ADD or NumberPickerDialogFragment.REMOVE to select the mode
     */
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
