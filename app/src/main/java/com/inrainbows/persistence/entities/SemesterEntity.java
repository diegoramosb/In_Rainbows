package com.inrainbows.persistence.entities;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;

import org.joda.time.DateTime;

import java.util.Date;

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
    private Date startDate;

    @ColumnInfo(name = "END_DATE")
    private Date endDate;

    public SemesterEntity(long id, String semesterName, DateTime startDate, DateTime endDate) {
        this.id = id;
        this.semesterName = semesterName;
        this.startDate = startDate.toDate();
        this.endDate = endDate.toDate();
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

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }
}
