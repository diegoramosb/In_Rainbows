package com.inrainbows.persistence.entities;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.Index;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.NonNull;

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

    @ColumnInfo(name = "SEMESTER_NAME", typeAffinity = ColumnInfo.TEXT)
    @NonNull
    private String semesterName;

    @ColumnInfo(name = "START_DATE")
    @NonNull
    private Date startDate;

    @ColumnInfo(name = "END_DATE")
    @NonNull
    private Date endDate;

    @ColumnInfo(name = "CURRENT_SEMESTER")
    @NonNull
    private boolean currentSemester;

    public SemesterEntity(long id, String semesterName, Date startDate, Date endDate, boolean currentSemester) {
        this.id = id;
        this.semesterName = semesterName;
        this.startDate = startDate;
        this.endDate = endDate;
        this.currentSemester = currentSemester;
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

    @NonNull
    public boolean isCurrentSemester() {
        return currentSemester;
    }

    public void setCurrentSemester(@NonNull boolean currentSemester) {
        this.currentSemester = currentSemester;
    }
}
