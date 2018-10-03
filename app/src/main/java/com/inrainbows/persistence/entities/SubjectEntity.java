package com.inrainbows.persistence.entities;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.ForeignKey;
import android.arch.persistence.room.Index;
import android.support.annotation.NonNull;

/**
 * @author diego on 12/07/2018.
 */
@Entity(tableName = "SUBJECTS",
        foreignKeys = @ForeignKey(entity = SemesterEntity.class, parentColumns = "ID",
                childColumns = "SEMESTER_ID", onDelete = ForeignKey.CASCADE),
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
     * Extra weekly expected hours of study (hours without counting class)
     */
    @ColumnInfo(name = "WEEKLY_EXTRA_HOURS", typeAffinity = ColumnInfo.REAL)
    private Double weeklyExtraHours;


    /**
     * Extra daily expected hours of study (hours without counting class)
     */
    @ColumnInfo(name = "DAILY_EXTRA_HOURS", typeAffinity = ColumnInfo.REAL)
    private Double dailyExtraHours;

    /**
     * Extra expected hours of study in the semester(hours without counting class)
     */
    @ColumnInfo(name = "SEMESTER_EXTRA_HOURS", typeAffinity = ColumnInfo.REAL)
    private Double semesterExtraHours;

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
     * Creates a new subject entity
     * @param id subject id
     * @param name subject name
     * @param credits subject credits
     * @param totalHours subject toal weekly hours
     * @param classHours subject total weekly class hours
     * @param dailyExtraHours subject daily extra study hours
     * @param weeklyExtraHours subject weekly extra study hours
     * @param semesterExtraHours subject semester extra study hours
     * @param studiedHoursDay subject studied hours in the current day
     * @param studiedHoursWeek subject studied hours in the current week
     * @param studiedHoursSemester subject studied hours in the semester
     * @param semesterId id of the semester that the subject belongs to
     */
    public SubjectEntity(Long id, @NonNull String name, @NonNull Double credits, Double totalHours,
                         @NonNull Double classHours, Double dailyExtraHours, Double weeklyExtraHours,
                         Double semesterExtraHours, Double studiedHoursDay, Double studiedHoursWeek,
                         Double studiedHoursSemester, @NonNull Long semesterId) {
        this.id = id;
        this.name = name;
        this.credits = credits;
        this.totalHours = totalHours;
        this.classHours = classHours;
        this.dailyExtraHours = dailyExtraHours;
        this.weeklyExtraHours = weeklyExtraHours;
        this.semesterExtraHours = semesterExtraHours;
        this.studiedHoursDay = studiedHoursDay;
        this.studiedHoursWeek = studiedHoursWeek;
        this.studiedHoursSemester = studiedHoursSemester;
        this.semesterId = semesterId;
    }

    /**
     * Returns the subject name
     * @return subject name
     */
    @NonNull
    public String getName() {
        return name;
    }

    /**
     * Sets the subject name to the parameter
     * @param name subject new name
     */
    public void setName(@NonNull String name) {
        this.name = name;
    }

    /**
     * Returns the subject credits
     * @return subject credits
     */
    @NonNull
    public Double getCredits() {
        return credits;
    }

    /**
     * Sets the subject credits to the parameter
     * @param credits subject new credits
     */
    public void setCredits(@NonNull Double credits) {
        this.credits = credits;
    }

    /**
     * Returns the subject total weekly hours
     * @return subject total weekly hours
     */
    public Double getTotalHours() {
        return totalHours;
    }

    /**
     * Sets the total weekly hours to the parameter
     * @param totalHours total hours of the subject
     */
    public void setTotalHours(Double totalHours) {
        this.totalHours = totalHours;
    }

    /**
     * Returns the subject weekly class hours
     * @return subject weekly class hours
     */
    @NonNull
    public Double getClassHours() {
        return classHours;
    }

    /**
     * Sets the subject weekly class hours to the parameter
     * @param classHours new subject weekly class hours
     */
    public void setClassHours(@NonNull Double classHours) {
        this.classHours = classHours;
    }

    /**
     * Returns the subject daily extra hours
     * @return subject daily extra hours
     */
    public Double getDailyExtraHours() {
        return dailyExtraHours;
    }

    /**
     * Sets the subject daily extra hours to the parameter
     * @param dailyExtraHours new subject daily extra hours
     */
    public void setDailyExtraHours(Double dailyExtraHours) {
        this.dailyExtraHours = dailyExtraHours;
    }

    /**
     * Returns the subject weekly extra hours
     * @return subject weekly extra hours
     */
    public Double getWeeklyExtraHours() {
        return weeklyExtraHours;
    }

    /**
     * Sets the subject weekly extra hours to the parameter
     * @param weeklyExtraHours new subject weekly extra hours
     */
    public void setWeeklyExtraHours(Double weeklyExtraHours) {
        this.weeklyExtraHours = weeklyExtraHours;
    }

    /**
     * Returns the subject extra hours in the semester
     * @return subject extra hours in the semester
     */
    public Double getSemesterExtraHours() {
        return semesterExtraHours;
    }

    /**
     * Sets the subject extra hours in the semester to the parameter
     * @param semesterExtraHours new subject extra hours in the semester
     */
    public void setSemesterExtraHours(Double semesterExtraHours) {
        this.semesterExtraHours = semesterExtraHours;
    }

    /**
     * Returns the subject studied hours for the current day
     * @return subject studied hours for the current day
     */
    public Double getStudiedHoursDay() {
        return studiedHoursDay;
    }

    /**
     * Sets the subject studied hours for the current day to the parameter
     * @param studiedHoursDay new subject studied hours for the current day
     */
    public void setStudiedHoursDay(Double studiedHoursDay) {
        this.studiedHoursDay = studiedHoursDay;
    }

    /**
     * Returns the subject studied hours for the current week
     * @return subject studied hours for the current week
     */
    public Double getStudiedHoursWeek() {
        return studiedHoursWeek;
    }

    /**
     * Sets the subject studied hours for the current week to the parameter
     * @param studiedHoursWeek new subject studied hours for the current week
     */
    public void setStudiedHoursWeek(Double studiedHoursWeek) {
        this.studiedHoursWeek = studiedHoursWeek;
    }

    /**
     * Returns the subject studied hours for the semester
     * @return subject studied hours for the semester
     */
    public Double getStudiedHoursSemester() {
        return studiedHoursSemester;
    }

    /**
     * Sets the subject studied hours for the semester to the parameter
     * @param studiedHoursSemester new subject studied hours for the semester
     */
    public void setStudiedHoursSemester(Double studiedHoursSemester) {
        this.studiedHoursSemester = studiedHoursSemester;
    }

    /**
     * Returns the id of the semester that the subject belongs to
     * @return id of the semester that the subject belongs to
     */
    @NonNull
    public Long getSemesterId() {
        return semesterId;
    }

    /**
     * Sets the id of the semester that the subject belongs to to the parameter
     * @param semesterId new id of the semester that the subject belongs to
     */
    public void setSemesterId(@NonNull Long semesterId) {
        this.semesterId = semesterId;
    }
}
