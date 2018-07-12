package com.inrainbows.persistence.entities;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.ForeignKey;
import android.arch.persistence.room.Ignore;
import android.arch.persistence.room.PrimaryKey;

/**
 * @author diego on 12/07/2018.
 */
@Entity(tableName = "SUBJECTS",
        foreignKeys = @ForeignKey(entity = SemesterEntity.class, parentColumns = "SEMESTER_ID", childColumns = "ID", onDelete = ForeignKey.CASCADE))
public class SubjectEntity {

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "ID")
    private long id;

    /**
     * SubjectEntity name
     */
    @ColumnInfo(name = "NAME")
    private String name;

    /**
     * Amount of credits the SubjectEntity is worth
     */
    @ColumnInfo(name = "CREDITS")
    private double credits;

    /**
     * Total expected weekly hours of study
     */
    @ColumnInfo(name = "TOTAL_HOURS")
    private double totalHours;

    /**
     * Weekly hours of class
     */
    @ColumnInfo(name = "CLASS_HOURS")
    private double classHours;

    /**
     * Extra expected hours of study (hours without counting class)
     */
    @ColumnInfo(name = "EXTRA_HOURS")
    private double extraHours;

    /**
     * Actual studied hours on this day
     */
    @ColumnInfo(name = "STUDIED_HOURS_DAY")
    private double studiedHoursDay;

    /**
     * Actual studied hours this week
     */
    @ColumnInfo(name = "STUDIED_HOURS_WEEK")
    private double studiedHoursWeek;

    /**
     * Actual studied hours this semester
     */
    @ColumnInfo(name = "STUDIED_HOURS_SEMESTER")
    private double studiedHoursSemester;

    /**
     * Parent semester Id.
     */
    @ColumnInfo(name = "SEMESTER_ID")
    private long semesterId;

    /**
     * Private constructor for builder.
     * @param builder Builder.
     */
    @Ignore
    private SubjectEntity(SubjectEntityBuilder builder){
        this.name = builder.name;
        this.credits = builder.credits;
        this.classHours = builder.classHours;

        this.totalHours = builder.getTotalHours();
        this.extraHours = builder.getExtraHours();

        this.studiedHoursDay = builder.getStudiedHoursDay();
        this.studiedHoursWeek = builder.getStudiedHoursWeek();
        this.studiedHoursSemester = builder.getStudiedHoursSemester();
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getCredits() {
        return credits;
    }

    public void setCredits(double credits) {
        this.credits = credits;
    }

    public double getTotalHours() {
        return totalHours;
    }

    public void setTotalHours(double totalHours) {
        this.totalHours = totalHours;
    }

    public double getClassHours() {
        return classHours;
    }

    public void setClassHours(double classHours) {
        this.classHours = classHours;
    }

    public double getExtraHours() {
        return extraHours;
    }

    public void setExtraHours(double extraHours) {
        this.extraHours = extraHours;
    }

    public double getStudiedHoursDay() {
        return studiedHoursDay;
    }

    public void setStudiedHoursDay(double studiedHoursDay) {
        this.studiedHoursDay = studiedHoursDay;
    }

    public double getStudiedHoursWeek() {
        return studiedHoursWeek;
    }

    public void setStudiedHoursWeek(double studiedHoursWeek) {
        this.studiedHoursWeek = studiedHoursWeek;
    }

    public double getStudiedHoursSemester() {
        return studiedHoursSemester;
    }

    public void setStudiedHoursSemester(double studiedHoursSemester) {
        this.studiedHoursSemester = studiedHoursSemester;
    }

    public long getSemesterId() {
        return semesterId;
    }

    public void setSemesterId(long semesterId) {
        this.semesterId = semesterId;
    }

    public static class SubjectEntityBuilder {

        private long id;

        private String name;

        private double credits;

        private double totalHours;

        private double classHours;

        private double extraHours;

        private double studiedHoursDay;

        private double studiedHoursWeek;

        private double studiedHoursSemester;

        private long semesterId;

        public SubjectEntityBuilder(String name, double credits, double classHours, long semesterId) {
            this.name = name;
            this.credits = credits;
            this.classHours = classHours;
            this.semesterId = semesterId;
        }

        public double getTotalHours() {
            return totalHours;
        }

        public SubjectEntityBuilder setTotalHours(double totalHours) {
            this.totalHours = totalHours;
            return this;
        }

        public double getExtraHours() {
            return extraHours;
        }

        public SubjectEntityBuilder setExtraHours(double extraHours) {
            this.extraHours = extraHours;
            return this;
        }

        public double getStudiedHoursDay() {
            return studiedHoursDay;
        }

        public SubjectEntityBuilder setStudiedHoursDay(double studiedHoursDay) {
            this.studiedHoursDay = studiedHoursDay;
            return this;
        }

        public double getStudiedHoursWeek() {
            return studiedHoursWeek;
        }

        public SubjectEntityBuilder setStudiedHoursWeek(double studiedHoursWeek) {
            this.studiedHoursWeek = studiedHoursWeek;
            return this;
        }

        public double getStudiedHoursSemester() {
            return studiedHoursSemester;
        }

        public SubjectEntityBuilder setStudiedHoursSemester(double studiedHoursSemester) {
            this.studiedHoursSemester = studiedHoursSemester;
            return this;
        }
    }
}
