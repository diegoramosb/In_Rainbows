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
    private double credits;

    /**
     * Total expected weekly hours of study
     */
    @ColumnInfo(name = "TOTAL_HOURS", typeAffinity = ColumnInfo.REAL)
    private double totalHours;

    /**
     * Weekly hours of class
     */
    @ColumnInfo(name = "CLASS_HOURS", typeAffinity = ColumnInfo.REAL)
    @NonNull
    private double classHours;

    /**
     * Extra weekly expected hours of study (hours without counting class)
     */
    @ColumnInfo(name = "WEEKLY_EXTRA_HOURS", typeAffinity = ColumnInfo.REAL)
    private double weeklyExtraHours;


    /**
     * Extra daily expected hours of study (hours without counting class)
     */
    @ColumnInfo(name = "DAILY_EXTRA_HOURS", typeAffinity = ColumnInfo.REAL)
    private double dailyExtraHours;

    /**
     * Extra expected hours of study in the semester(hours without counting class)
     */
    @ColumnInfo(name = "SEMESTER_EXTRA_HOURS", typeAffinity = ColumnInfo.REAL)
    private double semesterExtraHours;

    /**
     * Parent semester Id.
     */
    @ColumnInfo(name = "SEMESTER_ID")
    @NonNull
    private long semesterId;

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
     * @param semesterId id of the semester that the subject belongs to
     */
    public SubjectEntity(long id, @NonNull String name, @NonNull double credits, double totalHours,
                         @NonNull double classHours, double dailyExtraHours, double weeklyExtraHours,
                         double semesterExtraHours, @NonNull long semesterId) {
        this.id = id;
        this.name = name;
        this.credits = credits;
        this.totalHours = totalHours;
        this.classHours = classHours;
        this.dailyExtraHours = dailyExtraHours;
        this.weeklyExtraHours = weeklyExtraHours;
        this.semesterExtraHours = semesterExtraHours;
        this.semesterId = semesterId;
    }

    /**
     * Private constructor for builder.
     *
     * @param builder Builder.
     */
    private SubjectEntity(SubjectEntityBuilder builder){
        this.id = builder.id;
        this.name = builder.name;
        this.credits = builder.credits;
        this.classHours = builder.classHours;
        this.semesterId = builder.semesterId;

        this.totalHours = builder.getTotalHours();
        this.dailyExtraHours = builder.getDailyExtraHours();
        this.weeklyExtraHours = builder.getWeeklyExtraHours();
        this.semesterExtraHours = builder.getSemseterExtraHours();
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
    public double getCredits() {
        return credits;
    }

    /**
     * Sets the subject credits to the parameter
     * @param credits subject new credits
     */
    public void setCredits(@NonNull double credits) {
        this.credits = credits;
    }

    /**
     * Returns the subject total weekly hours
     * @return subject total weekly hours
     */
    public double getTotalHours() {
        return totalHours;
    }

    /**
     * Sets the total weekly hours to the parameter
     * @param totalHours total hours of the subject
     */
    public void setTotalHours(double totalHours) {
        this.totalHours = totalHours;
    }

    /**
     * Returns the subject weekly class hours
     * @return subject weekly class hours
     */
    @NonNull
    public double getClassHours() {
        return classHours;
    }

    /**
     * Sets the subject weekly class hours to the parameter
     * @param classHours new subject weekly class hours
     */
    public void setClassHours(@NonNull double classHours) {
        this.classHours = classHours;
    }

    /**
     * Returns the subject daily extra hours
     * @return subject daily extra hours
     */
    public double getDailyExtraHours() {
        return dailyExtraHours;
    }

    /**
     * Sets the subject daily extra hours to the parameter
     * @param dailyExtraHours new subject daily extra hours
     */
    public void setDailyExtraHours(double dailyExtraHours) {
        this.dailyExtraHours = dailyExtraHours;
    }

    /**
     * Returns the subject weekly extra hours
     * @return subject weekly extra hours
     */
    public double getWeeklyExtraHours() {
        return weeklyExtraHours;
    }

    /**
     * Sets the subject weekly extra hours to the parameter
     * @param weeklyExtraHours new subject weekly extra hours
     */
    public void setWeeklyExtraHours(double weeklyExtraHours) {
        this.weeklyExtraHours = weeklyExtraHours;
    }

    /**
     * Returns the subject extra hours in the semester
     * @return subject extra hours in the semester
     */
    public double getSemesterExtraHours() {
        return semesterExtraHours;
    }

    /**
     * Sets the subject extra hours in the semester to the parameter
     * @param semesterExtraHours new subject extra hours in the semester
     */
    public void setSemesterExtraHours(double semesterExtraHours) {
        this.semesterExtraHours = semesterExtraHours;
    }

    /**
     * Returns the id of the semester that the subject belongs to
     * @return id of the semester that the subject belongs to
     */
    @NonNull
    public long getSemesterId() {
        return semesterId;
    }

    /**
     * Sets the id of the semester that the subject belongs to to the parameter
     * @param semesterId new id of the semester that the subject belongs to
     */
    public void setSemesterId(@NonNull long semesterId) {
        this.semesterId = semesterId;
    }

    public static class SubjectEntityBuilder {

        private long id;

        private String name;

        private double credits;

        private double totalHours;

        private double classHours;

        private double dailyExtraHours;

        private double weeklyExtraHours;

        private double semseterExtraHours;

        private long semesterId;

        public SubjectEntityBuilder(long id, String name, double credits, double classHours, long semesterId) {
            this.id = id;
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

        public double getDailyExtraHours() {
            return dailyExtraHours;
        }

        public SubjectEntityBuilder setDailyExtraHours(double dailyExtraHours) {
            this.dailyExtraHours = dailyExtraHours;
            return this;
        }

        public double getWeeklyExtraHours() {
            return weeklyExtraHours;
        }

        public SubjectEntityBuilder setWeeklyExtraHours(double weeklyExtraHours) {
            this.weeklyExtraHours = weeklyExtraHours;
            return this;
        }

        public double getSemseterExtraHours() {
            return semseterExtraHours;
        }

        public SubjectEntityBuilder setSemseterExtraHours(double semseterExtraHours) {
            this.semseterExtraHours = semseterExtraHours;
            return this;
        }

        public SubjectEntity build(){
            return new SubjectEntity(this);
        }
    }
}
