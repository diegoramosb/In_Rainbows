package com.inrainbows.activity;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;

import com.inrainbows.persistence.AppDatabase;

import java.io.File;

/**
 * Abstract class with the db and methods that all activities can use
 * @author diego on 19/07/2018.
 */
public abstract class BaseActivity extends AppCompatActivity{

    /**
     * App data base
     */
    protected AppDatabase db;

    /**
     * Sets db to the database
     * @param savedInstanceState state parameters
     */
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        deleteDatabaseFile(this, "in_rainbows");
        this.db = AppDatabase.getDatabase(getApplication());
    }

    /**
     * Deletes a database.
     * @param context application context
     * @param databaseName database name
     */
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

    /**
     * Returns the database
     * @return app database database
     */
    public AppDatabase getDb() {
        return db;
    }

    /**
     * Shows an error message with custom title and body
     * @param errorId resource id of message title
     * @param errorMsgId resource id of message body
     */
    public void showErrorDialog(int errorId, int errorMsgId) {
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(this);
        alertDialogBuilder.setTitle(errorId);
        alertDialogBuilder.setMessage(errorMsgId);
        AlertDialog alert = alertDialogBuilder.create();
        alert.show();
    }

    /**
     * Shows a loading animation
     */
    public void showLoading() {
        //TODO
    }

    /**
     * Shows empty page
     */
    public void showEmpty() {
        //TODO consider adding parameters to reuse it for different classes
    }
}
