package com.inrainbows.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.AppBarLayout;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toolbar;

import com.inrainbows.R;
import com.inrainbows.mvp.model.Subject;
import com.inrainbows.mvp.view.SubjectDetailContract;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * @author diego on 4/08/2018.
 */
public class SubjectDetailActivity extends BaseActivity {

    Subject subject;

    @BindView(R.id.tv_subject_name)
    TextView tvSubjectName;

//    @BindView(R.id.subject_detail_toolbar)
//    Toolbar toolbar;

    SubjectDetailContract.Presenter presenter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Bundle bundle = getIntent().getExtras();
        subject = bundle.getParcelable("subject");

        setContentView(R.layout.subject_detail_act);
        ButterKnife.bind(this);

//        setActionBar(toolbar);

        tvSubjectName.setText(subject.getName());
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.semester_detail_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        switch (id){
            case R.id.semester_detail_hours:
                //TODO: Show popup options to add or remove time, then show dialog to choose time to add or remove.
            default:
                break;
        }

        return super.onOptionsItemSelected(item);
    }
}
