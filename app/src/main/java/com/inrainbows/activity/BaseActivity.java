package com.inrainbows.activity;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;

import com.inrainbows.mvp.model.Semester;
import com.inrainbows.mvp.presenter.BasePresenter;
import com.inrainbows.mvp.view.BaseView;
import com.inrainbows.persistence.AppDatabase;

import java.io.File;

/**
 * @author diego on 19/07/2018.
 */
public abstract class BaseActivity extends AppCompatActivity{

    protected AppDatabase db;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        deleteDatabaseFile(this, "in_rainbows");
        db = AppDatabase.getDatabase(getApplication());
    }

    public static void deleteDatabaseFile(Context context, String databaseName) {
        File databases = new File(context.getApplicationInfo().dataDir + "/databases");
        File db = new File(databases, databaseName);
        if (db.delete())
            System.out.println("Database deleted");
        else
            System.out.println("Failed to delete database");

        File journal = new File(databases, databaseName + "-journal");
        if (journal.exists()) {
            if (journal.delete())
                System.out.println("Database journal deleted");
            else
                System.out.println("Failed to delete database journal");
        }
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
