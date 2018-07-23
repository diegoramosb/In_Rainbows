package com.inrainbows;

import android.app.Application;

import com.inrainbows.mvp.model.Semester;
import com.inrainbows.persistence.AppDatabase;

/**
 * @author diego on 19/07/2018.
 */
public class InRainbowsApp extends Application {

    private Semester currentSemester;

    @Override
    public void onCreate() {
        super.onCreate();
    }

    public Semester getCurrentSemester() {
        return currentSemester;
    }

    public void setCurrentSemester(Semester currentSemester) {
        this.currentSemester = currentSemester;
    }
}
