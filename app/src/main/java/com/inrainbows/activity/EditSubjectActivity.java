package com.inrainbows.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.InputType;
import android.widget.EditText;

import com.inrainbows.R;
import com.inrainbows.mvp.model.Semester;
import com.inrainbows.mvp.model.Subject;
import com.inrainbows.mvp.presenter.EditSubjectPresenter;
import com.inrainbows.mvp.view.EditSubjectContract;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * @author diego on 22/07/2018.
 */
public class EditSubjectActivity extends BaseActivity implements EditSubjectContract.View {

    EditSubjectContract.Presenter presenter;

    private Semester currentSemester;

    /**
     * Name edit text
     */
    @BindView(R.id.et_1)
    EditText et_1;

    /**
     * Credits edit text
     */
    @BindView(R.id.et_2)
    EditText et_2;

    /**
     * Class hours edit text
     */
    @BindView(R.id.et_3)
    EditText et_3;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Bundle bundle = getIntent().getExtras();
        currentSemester = (Semester) bundle.getSerializable("currentSemester");

        setContentView(R.layout.edit_act);
        ButterKnife.bind(this);

        this.presenter = new EditSubjectPresenter(this);

        et_1.setInputType(InputType.TYPE_CLASS_TEXT);
        et_2.setInputType(InputType.TYPE_NUMBER_FLAG_DECIMAL);
        et_3.setInputType(InputType.TYPE_NUMBER_FLAG_DECIMAL);

        et_1.setHint(R.string.subject_name);
        et_2.setHint(R.string.subject_credits);
        et_3.setHint(R.string.subject_class_hours);

        et_1.setText(R.string.sample_subject_name);
        et_2.setText(R.string.sample_subject_credits);
        et_3.setText(R.string.sample_subject_class_hours);
    }

    @OnClick(R.id.fab_check)
    public void insertSubject() {
        if(currentSemester != null) {
            Subject subject = new Subject.SubjectBuilder(0L, et_1.getText().toString(),
                    Double.valueOf(et_2.getText().toString()), Double.valueOf(et_3.getText().toString()),
                    currentSemester.getId())
                    .build();
            presenter.insertSubject(subject);
            showMainView();
        }
        else {
            showErrorDialog(R.string.err_no_semester, R.string.err_no_semester_msg);
        }
    }

    @Override
    public void showMainView() {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }

    @Override
    public void setPresenter(EditSubjectContract.Presenter presenter) {
        this.presenter = presenter;
    }

    @Override
    public void showLoading() {

    }

    @Override
    public void showEmpty() {

    }
}
