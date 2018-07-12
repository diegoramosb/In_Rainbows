package com.inrainbows.persistence.entities;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;

import org.joda.time.DateTime;

/**
 * @author diego on 12/07/2018.
 */
@Entity(tableName = "SEMESTERS")
public class SemesterEntity {

    @ColumnInfo(name = "ID")
    @PrimaryKey(autoGenerate = true)
    private long id;

    @ColumnInfo(name = "SEMESTER_NAME")
    private String semesterName;

    @ColumnInfo(name = "START_DATE")
    private DateTime startDate;

    @ColumnInfo(name = "END_DATE")
    private DateTime endDate;

    public SemesterEntity(long id, String semesterName, DateTime startDate, DateTime endDate) {
        this.id = id;
        this.semesterName = semesterName;
        this.startDate = startDate;
        this.endDate = endDate;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getSemesterName() {
        return semesterName;
    }

    public void setSemesterName(String semesterName) {
        this.semesterName = semesterName;
    }

    public DateTime getStartDate() {
        return startDate;
    }

    public void setStartDate(DateTime startDate) {
        this.startDate = startDate;
    }

    public DateTime getEndDate() {
        return endDate;
    }

    public void setEndDate(DateTime endDate) {
        this.endDate = endDate;
    }
}
