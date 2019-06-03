package com.inrainbows.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.InputType;
import android.widget.EditText;

import com.inrainbows.R;
import com.inrainbows.mvp.model.Semester;
import com.inrainbows.mvp.presenter.EditSemesterPresenter;
import com.inrainbows.mvp.view.EditSemesterContract;

import org.joda.time.DateTime;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Activity for editing semester info
 * @author diego on 15/07/2018.
 */
public class EditSemesterActivity extends BaseActivity implements EditSemesterContract.View {

    /**
     * Presenter that the activity will use to interact with the DB.
     */
    EditSemesterContract.Presenter presenter;

    /**
     * Semester name edit text
     */
    @BindView(R.id.et_1)
    EditText et_1;

    /**
     * Semester start date edit text
     */
    @BindView(R.id.et_2)
    EditText et_2;

    /**
     * Semester end date edit text
     */
    @BindView(R.id.et_3)
    EditText et_3;

    /**
     * Initializes the activity
     * @param savedInstanceState state parameters
     */
    @Override
    public  void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setUIComponents();

        this.presenter = new EditSemesterPresenter(this);
    }

    /**
     * Initializes the UI componentes
     */
    @Override
    public void setUIComponents() {
        setContentView(R.layout.edit_act);
        ButterKnife.bind(this);

        et_1.setInputType(InputType.TYPE_CLASS_TEXT);
        et_2.setInputType(InputType.TYPE_DATETIME_VARIATION_DATE);
        et_3.setInputType(InputType.TYPE_DATETIME_VARIATION_DATE);

        et_1.setHint(R.string.semester_name);
        et_2.setHint(R.string.start_date);
        et_3.setHint(R.string.end_date);

        //Values for quick testing
        et_1.setText(R.string.sample_semester_name);
        et_2.setText(R.string.sample_start_date);
        et_3.setText(R.string.sample_end_date);

    }

    /**
     * Inserts a new semester and sets it as the current semester if ther isn't any, then
     * returns to the main activity
     */
    @OnClick(R.id.fab_check)
    public  void insertSemester(){

        boolean isThereCurrentSemester = presenter.getCurrentSemester() != null;

        Semester semester = new Semester.SemesterBuilder(0L, et_1.getText().toString(),
                new DateTime(et_2.getText().toString()),
                new DateTime(et_3.getText().toString()),
                !isThereCurrentSemester).build();

        presenter.insertSemester(semester);
        showMainView();
    }

    /**
     * Sets the presenter
     * @param presenter presenter
     */
    @Override
    public void setPresenter(EditSemesterContract.Presenter presenter) {
        this.presenter = presenter;
    }

    /**
     * Returns to the main activity
     */
    @Override
    public void showMainView() {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }
}
