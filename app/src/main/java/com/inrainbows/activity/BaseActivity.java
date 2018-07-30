package com.inrainbows.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;

import com.inrainbows.mvp.model.Semester;
import com.inrainbows.mvp.presenter.BasePresenter;
import com.inrainbows.mvp.view.BaseView;
import com.inrainbows.persistence.AppDatabase;

/**
 * @author diego on 19/07/2018.
 */
public abstract class BaseActivity extends AppCompatActivity{

    protected AppDatabase db;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        db = AppDatabase.getDatabase(getApplication());
    }

    public AppDatabase getDb() {
        return db;
    }

    public void showErrorDialog(int errorId, int errorMsgId) {
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(this);
        alertDialogBuilder.setTitle(errorId);
        alertDialogBuilder.setMessage(errorMsgId);
        AlertDialog alert = alertDialogBuilder.create();
        alert.show();
    }

    public void showLoading() {

    }

    public void showEmpty() {

    }
}
