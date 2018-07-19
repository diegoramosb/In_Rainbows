package com.inrainbows;

import android.app.Application;

import com.inrainbows.persistence.AppDatabase;

/**
 * @author diego on 19/07/2018.
 */
public class InRainbowsApp extends Application {

    private AppDatabase db;

    public InRainbowsApp() {
        super();
    }

    @Override
    public void onCreate() {
        super.onCreate();
        db = AppDatabase.getDatabase(this);
    }

    public AppDatabase getDb() {
        return db;
    }
}
