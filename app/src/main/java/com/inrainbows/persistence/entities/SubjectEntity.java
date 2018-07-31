package com.inrainbows.persistence.entities;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.ForeignKey;
import android.arch.persistence.room.Ignore;
import android.arch.persistence.room.Index;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.NonNull;

/**
 * @author diego on 12/07/2018.
 */
@Entity(tableName = "SUBJECTS",
        foreignKeys = @ForeignKey(entity = SemesterEntity.class, parentColumns = "ID", childColumns = "SEMESTER_ID", onDelete = ForeignKey.CASCADE),
        indices = @Index(value = "SEMESTER_ID"))
public class SubjectEntity extends BaseEntity{

    /**
     * SubjectEntity name
     */
    @ColumnInfo(name = "NAME", typeAffinity = ColumnInfo.TEXT)
    @NonNull
    private String name;

    /**
     * Amount of credits the SubjectEntity is worth
     */
    @ColumnInfo(name = "CREDITS", typeAffinity = ColumnInfo.REAL)
    @NonNull
    private Double credits;

    /**
     * Total expected weekly hours of study
     */
    @ColumnInfo(name = "TOTAL_HOURS", typeAffinity = ColumnInfo.REAL)
    private Double totalHours;

    /**
     * Weekly hours of class
     */
    @ColumnInfo(name = "CLASS_HOURS", typeAffinity = ColumnInfo.REAL)
    @NonNull
    private Double classHours;

    /**
     * Extra expected hours of study (hours without counting class)
     */
    @ColumnInfo(name = "EXTRA_HOURS", typeAffinity = ColumnInfo.REAL)
    private Double extraHours;

    /**
     * Actual studied hours on this day
     */
    @ColumnInfo(name = "STUDIED_HOURS_DAY", typeAffinity = ColumnInfo.REAL)
    private Double studiedHoursDay;

    /**
     * Actual studied hours this week
     */
    @ColumnInfo(name = "STUDIED_HOURS_WEEK", typeAffinity = ColumnInfo.REAL)
    private Double studiedHoursWeek;

    /**
     * Actual studied hours this semester
     */
    @ColumnInfo(name = "STUDIED_HOURS_SEMESTER", typeAffinity = ColumnInfo.REAL)
    private Double studiedHoursSemester;

    /**
     * Parent semester Id.
     */
    @ColumnInfo(name = "SEMESTER_ID")
    @NonNull
    private Long semesterId;

    /**
     * Private constructor for builder.
     * @param builder Builder.
     */
    private SubjectEntity(SubjectEntityBuilder builder){
        this.id = builder.id;
        this.name = builder.name;
        this.credits = builder.credits;
        this.classHours = builder.classHours;

        this.totalHours = builder.getTotalHours();
        this.extraHours = builder.getExtraHours();

        this.studiedHoursDay = builder.getStudiedHoursDay();
        this.studiedHoursWeek = builder.getStudiedHoursWeek();
        this.studiedHoursSemester = builder.getStudiedHoursSemester();
    }

    public SubjectEntity(Long id, @NonNull String name, @NonNull Double credits, Double totalHours, @NonNull Double classHours, Double extraHours, Double studiedHoursDay, Double studiedHoursWeek, Double studiedHoursSemester, @NonNull Long semesterId) {
        this.id = id;
        this.name = name;
        this.credits = credits;
        this.totalHours = totalHours;
        this.classHours = classHours;
        this.extraHours = extraHours;
        this.studiedHoursDay = studiedHoursDay;
        this.studiedHoursWeek = studiedHoursWeek;
        this.studiedHoursSemester = studiedHoursSemester;
        this.semesterId = semesterId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Double getCredits() {
        return credits;
    }

    public void setCredits(Double credits) {
        this.credits = credits;
    }

    public Double getTotalHours() {
        return totalHours;
    }

    public void setTotalHours(Double totalHours) {
        this.totalHours = totalHours;
    }

    public Double getClassHours() {
        return classHours;
    }

    public void setClassHours(Double classHours) {
        this.classHours = classHours;
    }

    public Double getExtraHours() {
        return extraHours;
    }

    public void setExtraHours(Double extraHours) {
        this.extraHours = extraHours;
    }

    public Double getStudiedHoursDay() {
        return studiedHoursDay;
    }

    public void setStudiedHoursDay(Double studiedHoursDay) {
        this.studiedHoursDay = studiedHoursDay;
    }

    public Double getStudiedHoursWeek() {
        return studiedHoursWeek;
    }

    public void setStudiedHoursWeek(Double studiedHoursWeek) {
        this.studiedHoursWeek = studiedHoursWeek;
    }

    public Double getStudiedHoursSemester() {
        return studiedHoursSemester;
    }

    public void setStudiedHoursSemester(Double studiedHoursSemester) {
        this.studiedHoursSemester = studiedHoursSemester;
    }

    public Long getSemesterId() {
        return semesterId;
    }

    public void setSemesterId(Long semesterId) {
        this.semesterId = semesterId;
    }

    public static class SubjectEntityBuilder {

        private Long id;

        private String name;

        private Double credits;

        private Double totalHours;

        private Double classHours;

        private Double extraHours;

        private Double studiedHoursDay;

        private Double studiedHoursWeek;

        private Double studiedHoursSemester;

        private Long semesterId;

        public SubjectEntityBuilder(Long id, String name, Double credits, Double classHours, Long semesterId) {
            this.id = id;
            this.name = name;
            this.credits = credits;
            this.classHours = classHours;
            this.semesterId = semesterId;
        }

        public Double getTotalHours() {
            return totalHours;
        }

        public SubjectEntityBuilder setTotalHours(Double totalHours) {
            this.totalHours = totalHours;
            return this;
        }

        public Double getExtraHours() {
            return extraHours;
        }

        public SubjectEntityBuilder setExtraHours(Double extraHours) {
            this.extraHours = extraHours;
            return this;
        }

        public Double getStudiedHoursDay() {
            return studiedHoursDay;
        }

        public SubjectEntityBuilder setStudiedHoursDay(Double studiedHoursDay) {
            this.studiedHoursDay = studiedHoursDay;
            return this;
        }

        public Double getStudiedHoursWeek() {
            return studiedHoursWeek;
        }

        public SubjectEntityBuilder setStudiedHoursWeek(Double studiedHoursWeek) {
            this.studiedHoursWeek = studiedHoursWeek;
            return this;
        }

        public Double getStudiedHoursSemester() {
            return studiedHoursSemester;
        }

        public SubjectEntityBuilder setStudiedHoursSemester(Double studiedHoursSemester) {
            this.studiedHoursSemester = studiedHoursSemester;
            return this;
        }

        public Long getSemesterId() {
            return semesterId;
        }

        public void setSemesterId(Long semesterId) {
            this.semesterId = semesterId;
        }

        public SubjectEntity build(){
            return new SubjectEntity(this);
        }
    }
}
