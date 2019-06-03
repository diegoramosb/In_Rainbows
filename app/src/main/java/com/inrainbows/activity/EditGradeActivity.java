package com.inrainbows.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.text.InputType;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Spinner;

import com.inrainbows.R;
import com.inrainbows.mvp.model.Grade;
import com.inrainbows.mvp.model.Subject;
import com.inrainbows.mvp.presenter.EditGradePresenter;
import com.inrainbows.mvp.view.EditGradeContract;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Activity for grade editing
 * @author diego on 30/07/2018.
 */
public class EditGradeActivity extends BaseActivity implements EditGradeContract.View {

    /**
     * Presenter that the activity will use to interact with the DB.
     */
    EditGradeContract.Presenter presenter;

    /**
     * Name edit text
     */
    @BindView(R.id.et_1)
    EditText et_name;

    /**
     * Percentage edit text
     */
    @BindView(R.id.et_2)
    EditText et_percentage;

    /**
     * Graded checkbox
     */
    @BindView(R.id.cb_graded)
    CheckBox cb_graded;

    /**
     * Grade edit text
     */
    @BindView(R.id.et_3)
    EditText et_grade;

    /**
     * FAB to confirm editing
     */
    @BindView(R.id.fab_check)
    FloatingActionButton fab_check;

    /**
     * Subject selection spinner
     */
    @BindView(R.id.spinner_select_subject)
    Spinner spinner;

    /**
     * Creates a new activity
     * @param savedInstanceState parámetros de estado
     */
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setUIComponents();

        this.presenter = new EditGradePresenter(this);

        setDropdownData();
    }

    /**
     * Loads the subject names in the dropdown list
     */
    private void setDropdownData() {
        ArrayAdapter<Subject> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, presenter.getAllSubjects());
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                Subject subject = (Subject) adapterView.getSelectedItem();
                //Action
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
        spinner.setVisibility(View.VISIBLE);
    }

    /**
     * Vuelve a la vista principal
     */
    @Override
    public void showMainView() {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }

    /**
     * Initializes de UI components
     */
    @Override
    public void setUIComponents() {
        //Butterknife binding
        setContentView(R.layout.edit_act);
        ButterKnife.bind(this);

        //Edit texts' hints
        et_name.setHint(R.string.grade_name_hint);
        et_grade.setHint(R.string.grade_hint);
        et_percentage.setHint(R.string.grade_percentage_hint);

        //Edit texts' input types
        et_name.setInputType(InputType.TYPE_CLASS_TEXT);
        et_grade.setInputType(InputType.TYPE_CLASS_NUMBER);
        et_percentage.setInputType(InputType.TYPE_CLASS_NUMBER);

        //Checkbox visibility
        cb_graded.setVisibility(View.VISIBLE);
        cb_graded.setChecked(true);
    }

    /**
     * Adds a new grade to de subject if all info is correct, otherwise shows an error message
     */
    @OnClick(R.id.fab_check)
    public void fabCheckOnClick() {
        if(getSelectedSubject() != null) {
            Grade grade = new Grade.GradeBuilder(0L, getSelectedSubject().getId(),
                    et_name.getText().toString(),
                    Double.valueOf(et_percentage.getText().toString()))
                    .setGraded(false)
                    .build();
            if (cb_graded.isChecked()) {
                grade.setGraded(true);
                grade.setGrade(Double.valueOf(et_grade.getText().toString()));
            }
            presenter.insertGrade(grade);
            showMainView();
        }
        else {
            showErrorDialog(R.string.err_no_selected_subject, R.string.err_no_selected_subject_msg);
        }
    }

    /**
     * Returns the selected subject in the spinner
     * @return selected subject in the spinner
     */
    private Subject getSelectedSubject() {
        return (Subject) spinner.getSelectedItem();
    }

    /**
     * Changes the grade edit text visibility according to the checkbox state
     */
    @OnClick(R.id.cb_graded)
    public void isCbChecked(){
        if(cb_graded.isChecked()){
            et_grade.setVisibility(View.VISIBLE);
        }
        else {
            et_grade.setVisibility(View.GONE);
        }
    }

    /**
     * Sets the presenter
     * @param presenter presenter
     */
    @Override
    public void setPresenter(EditGradeContract.Presenter presenter) {
        this.presenter = presenter;
    }
}
