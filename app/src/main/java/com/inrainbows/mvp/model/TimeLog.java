package com.inrainbows.mvp.model;

import org.joda.time.DateTime;
import org.parceler.Parcel;
import org.parceler.ParcelConstructor;

/**
 * Class that represents a study time log
 * @author diego on 13/10/2018.
 */
@Parcel()
public class TimeLog {

    /**
     * Duration of time log in duration
     */
    private double duration;

    /**
     * Start time of the log
     */
    private DateTime startTime;

    /**
     * Constructor for a new TimeLog
     * @param duration duration of the time log
     * @param startTime start time of the time log
     */
    @ParcelConstructor
    public TimeLog(double duration, DateTime startTime) {
        this.duration = duration;
        this.startTime = startTime;
    }

    /**
     * Returns the duration of the time log
     * @return the duration of the time log
     */
    public double getDuration() {
        return duration;
    }

    /**
     * Sets the time log duration to the given value
     * @param duration duration of time log
     */
    public void setDuration(double duration) {
        this.duration = duration;
    }

    /**
     * Returns the start time of the time log
     * @return the start time of the time log
     */
    public DateTime getStartTime() {
        return startTime;
    }

    /**
     * Sets the start time of the time log to the given value
     * @param startTime start time of time log
     */
    public void setStartTime(DateTime startTime) {
        this.startTime = startTime;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("TimeLog{");
        sb.append("duration=").append(duration);
        sb.append(", startTime=").append(startTime);
        sb.append('}');
        return sb.toString();
    }
}
