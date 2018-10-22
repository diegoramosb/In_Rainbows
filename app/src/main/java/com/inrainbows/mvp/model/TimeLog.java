package com.inrainbows.mvp.model;

import org.joda.time.DateTime;
import org.joda.time.Interval;
import org.parceler.Parcel;
import org.parceler.ParcelConstructor;

/**
 * Class that represents a study time log
 * @author diego on 13/10/2018.
 */
@Parcel()
public class TimeLog {

    /**
     * Start time of the log
     */
    private DateTime startTime;

    /**
     * End time of the log
     */
    private DateTime endTime;

    /**
     * Constructor for a new TimeLog
     *
     * @param startTime start time of the time log
     * @param endTime   end time of the log
     */
    @ParcelConstructor
    public TimeLog(DateTime startTime, DateTime endTime) {
        this.startTime = startTime;
        this.endTime = endTime;
    }

    /**
     * Returns the start time of the time log
     *
     * @return the start time of the time log
     */
    public DateTime getStartTime() {
        return startTime;
    }

    /**
     * Sets the start time of the time log to the given value
     *
     * @param startTime start time of time log
     */
    public void setStartTime(DateTime startTime) {
        this.startTime = startTime;
    }

    /**
     * Returns the end time of the time log
     *
     * @return the end time of the time log
     */
    public DateTime getEndTime() {
        return endTime;
    }

    /**
     * Sets the end time of the time log to the given value
     *
     * @param endTime end time of time log
     * @return
     */
    public TimeLog setEndTime(DateTime endTime) {
        this.endTime = endTime;
        return this;
    }

    /**
     * Returns the duration of the time log in milliseconds
     * @return the duration of the time log in milliseconds
     */
    public long duration() {
        return new Interval(startTime, endTime).toDurationMillis();
    }

    /**
     * Returns the duration of the time log in minutes
     * @return the duration of the time log in minutes
     */
    public long durationMinutes() {
        return duration()/60000;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("TimeLog{");
        sb.append("startTime=").append(startTime);
        sb.append(", endTime=").append(endTime);
        sb.append(", duration=").append(durationMinutes());
        sb.append('}');
        return sb.toString();
    }
}
