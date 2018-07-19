package com.inrainbows.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.inrainbows.InRainbowsApp;
import com.inrainbows.mvp.view.BaseView;
import com.inrainbows.persistence.AppDatabase;

/**
 * @author diego on 19/07/2018.
 */
public abstract class BaseActivity extends AppCompatActivity {

    protected AppDatabase db;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        db = AppDatabase.getDatabase(getApplication());
    }

    public AppDatabase getDb() {
        return db;
    }
}
