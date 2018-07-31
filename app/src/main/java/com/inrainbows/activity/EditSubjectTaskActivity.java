package com.inrainbows.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.InputType;
import android.view.View;
import android.widget.EditText;
import android.widget.Spinner;

import com.inrainbows.R;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * @author diego on 30/07/2018.
 */
public class EditSubjectTaskActivity extends BaseActivity {

    /**
     * Name edit text
     */
    @BindView(R.id.et_1)
    EditText et_name;

    /**
     * Due date edit text
     */
    @BindView(R.id.et_2)
    EditText et_date;

    /**
     * Description edit text
     */
    @BindView(R.id.et_3)
    EditText et_desc;

    @BindView(R.id.et_4)
    EditText et_percentage;

    @BindView(R.id.spinner_select_subject)
    Spinner spinner;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.edit_act);
        ButterKnife.bind(this);

        //Set spinner elements here
        spinner.setVisibility(View.VISIBLE);

        et_name.setInputType(InputType.TYPE_CLASS_TEXT);
        et_date.setInputType(InputType.TYPE_CLASS_DATETIME);
        et_desc.setInputType(InputType.TYPE_CLASS_TEXT);
        et_percentage.setInputType(InputType.TYPE_CLASS_NUMBER);

        et_name.setHint(R.string.task_name_hint);
        et_date.setHint(R.string.task_due_date_hint);
        et_desc.setHint(R.string.task_description_hint);
        et_percentage.setHint(R.string.subject_task_percentage_hint);

//        et_1.setText(R.string.sample_subject_task_name);
//        et_2
    }
}
