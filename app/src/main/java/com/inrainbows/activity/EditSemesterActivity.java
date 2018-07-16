package com.inrainbows.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.inrainbows.R;

import butterknife.ButterKnife;

/**
 * @author diego on 15/07/2018.
 */
public class EditSemesterActivity extends AppCompatActivity {



    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.edit_semester_act);
        ButterKnife.bind(this);
    }
}
