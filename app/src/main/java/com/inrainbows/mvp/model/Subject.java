package com.inrainbows.mvp.model;

import com.inrainbows.mvp.model.listConverters.GradeListsConverter;
import com.inrainbows.mvp.model.listConverters.TimeLogListConverter;
import com.inrainbows.persistence.entities.SubjectEntity;

import org.parceler.ParcelPropertyConverter;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by diego on 31/05/2017.
 * This class represents a subject
 */
@org.parceler.Parcel(org.parceler.Parcel.Serialization.BEAN)
public class Subject {

    /*--------------------------------------------------------------------------------------------*/
    /* FIELDS */
    /*--------------------------------------------------------------------------------------------*/

    /**
     * Subject id
     */
    private long id;

    /**
     * Id of the semester that the subject belongs to
     */
    private long semesterId;

    /**
     * Subject name
     */
    private String name;

    /**
     * Amount of credits the subject is worth
     */
    private double credits;

    /**
     * Total expected weekly hours of study
     */
    private double totalHours;

    /**
     * Weekly hours of class
     */
    private double classHours;

    /**
     * Daily extra expected hours of study (hours without counting class)
     */
    private double weeklyExtraHours;

    /**
     * Weekly extra expected hours of study
     */
    private double dailyExtraHours;

    /**
     * Expected extra studiy hours in all semester
     */
    private double semesterExtraHours;

    /**
     * List with the subject's grades
     */
    @ParcelPropertyConverter(GradeListsConverter.class)
    private List<Grade> grades;

    @ParcelPropertyConverter(TimeLogListConverter.class)
    private List<TimeLog> timeLogs;

    /*--------------------------------------------------------------------------------------------*/
    /* CONSTRUCTORS */
    /*--------------------------------------------------------------------------------------------*/

    /**
     * Empty constructor for Parceler
     */
    Subject(){
    }

    /**
     * Private constructor for builder.
     * @param builder Builder.
     */
    private Subject(SubjectBuilder builder){
        this.id = builder.id;
        this.semesterId = builder.semesterId;
        this.name = builder.name;
        this.credits = builder.credits;
        this.classHours = builder.classHours;

        this.totalHours = calculateTotalHours();
        this.weeklyExtraHours = weeklyExtraHours();
        this.dailyExtraHours = dailyExtraHours();
        this.semesterExtraHours = semesterExtraHours();

        this.grades = builder.getGrades();
        this.timeLogs = builder.getTimeLogs();
    }

    /**
     * Creates a new Subject with the data of a SubjectEntity
     * @param entity SubjectEntity retrieved from the database
     */
    public Subject(SubjectEntity entity){
        if(entity != null) {
            this.id = entity.getId();
            this.semesterId = entity.getSemesterId();
            this.name = entity.getName();
            this.credits = entity.getCredits();

            this.totalHours = entity.getTotalHours();
            this.classHours = entity.getClassHours();
            this.dailyExtraHours = entity.getDailyExtraHours();
            this.weeklyExtraHours = entity.getWeeklyExtraHours();
            this.semesterExtraHours = entity.getSemesterExtraHours();

            this.grades = new ArrayList<>();
            this.timeLogs = new ArrayList<>();
        }
    }

    /*--------------------------------------------------------------------------------------------*/
    /* GETTERS AND SETTERS */
    /*--------------------------------------------------------------------------------------------*/

    /**
     * Returns the subject id
     * @return subject id
     */
    public long getId() {
        return id;
    }

    /**
     * Sets the subject id to id
     * @param id new subject id
     */
    public void setId(long id) {
        this.id = id;
    }

    /**
     * Returns the id of the semester that the subject belongs to
     * @return id of the semester that the subject belongs to
     */
    public long getSemesterId() {
        return semesterId;
    }

    /**
     * Sets the semesterId to the new value
     * @param semesterId new semesterId
     */
    public void setSemesterId(long semesterId) {
        this.semesterId = semesterId;
    }

    /**
     * @return Subject name
     */
    public String getName() {
        return name;
    }

    /**
     * Sets the subject name to pName
     *
     * @param pName new subject name
     */
    public void setName(String pName) {
        name = pName;
    }

    /**
     * @return Credits the subject is worth
     */
    public double getCredits() {
        return credits;
    }

    /**
     * Sets the subject credits to pCredits
     *
     * @param pCredits new amount of credits the subject is worth.
     */
    public void setCredits(double pCredits) {
        credits = pCredits;
    }

    /**
     * @return Weekly hours of class of the subject
     */
    public double getClassHours() {
        return classHours;
    }

    /**
     * Sets weekly hours of class to pClassHours
     *
     * @param pClassHours new weekly hours of class
     */
    public void setClassHours(double pClassHours) {
        classHours = pClassHours;
    }

    /**
     * @return Total hours of study that the subject requires, by default credits * 3.
     */
    public double getTotalHours() {
        return totalHours;
    }

    /**
     * Sets the total hours to pTotalHours
     * @param pTotalHours new studied hours
     */
    public void setTotalHours( double pTotalHours ){
        totalHours = pTotalHours;
    }

    /**
     * @return Weekly extra hours of study that the subject requires, meaning totalHours - classHours
     */
    public double getWeeklyExtraHours() {
        return weeklyExtraHours;
    }

    /**
     * Returns the daily extra study hours of the subject
     *
     * @return daily extra study hours of the subject
     */
    public double getDailyExtraHours() {
        return dailyExtraHours;
    }

    /**
     * Returns the semseter extra study hours of the subject
     *
     * @return semseter extra study hours of the subject
     */
    public double getSemesterExtraHours() {
        return semesterExtraHours;
    }

    /**
     * @return Studied hours for the current day
     */
//    public double getStudiedHoursDay() {
//        return studiedHoursDay;
//    }
//
//    /**
//     * Sets the studied hours for the current day to pStudiedHours
//     *
//     * @param pStudiedHours new studied hours
//     */
//    public void setStudiedHoursDay(double pStudiedHours) {
//        studiedHoursDay = pStudiedHours;
//    }
//
//    /**
//     * Increases the studied hours of the current day by pStudiedHours
//     *
//     * @param pStudiedHours hours to increase
//     */
//    public void addStudiedHoursDay(double pStudiedHours) {
//        studiedHoursDay += pStudiedHours;
//    }
//
//    /**
//     * Decreases the studied hours of the day by hours
//     * @param hours number of hours to decrease
//     */
//    public void removeStudiedHoursDay(double hours) {
//        studiedHoursDay -= hours;
//    }
//
//    /**
//     * @return Studied hours this week in the subject
//     */
//    public double getStudiedHoursWeek() {
//        return studiedHoursWeek;
//    }
//
//    /**
//     * Sets the amount of studied hours this week to pStudiedHours
//     *
//     * @param pStudiedHours new amount of studied hours this week
//     */
//    public void setStudiedHoursWeek(double pStudiedHours) {
//        studiedHoursWeek = pStudiedHours;
//    }
//
//    /**
//     * @return Amount of studiedHours this semester in the subject
//     */
//    public double getStudiedHoursSemester() {
//        return studiedHoursSemester;
//    }
//
//    /**
//     * Sets the amount of studied hours of this subject in this semester to pStudiedHours
//     *
//     * @param pStudiedHours new amount of studied hours
//     */
//    public void setStudiedHoursSemester(double pStudiedHours) {
//        studiedHoursSemester = pStudiedHours;
//    }

    /**
     * Returns a list with the subject grades
     * @return list with the subject grades
     */
    public List<Grade> getGrades(){
        return grades;
    }

    /**
     * Sets the list of grades to the parameter
     * @param grades new list of grades
     */
    public void setGrades(List<Grade> grades) {
        this.grades = grades;
    }

    /**
     * Returns the subject time logs
     * @return the subject time logs
     */
    public List<TimeLog> getTimeLogs() {
        return timeLogs;
    }

    /**
     * Sets the subject time logs to the given value
     * @param timeLogs new subject time logs
     */
    public void setTimeLogs(List<TimeLog> timeLogs) {
        this.timeLogs = timeLogs;
    }

    /*--------------------------------------------------------------------------------------------*/
    /* OTHER METHODS */
    /*--------------------------------------------------------------------------------------------*/

    public double studiedTimeDay() {
        return 0;
    }

    public double studiedTimeWeek() {
        return 0;
    }

    public double studiedTimeSemester() {
        return 0;
    }

    /**
     * Adds a grade to the list
     * @param grade grade to add
     */
    public void addGrade(Grade grade) {
        grades.add(grade);
    }

    /**
     * Adds a time log to the list
     * @param timeLog time log to add
     */
    public void addTimeLog(TimeLog timeLog) {
        timeLogs.add(timeLog);
    }

    /**
     * Calculates the amount of total hours of study per week based on the number of credits.
     *
     * @return Total amount of studied hours per week.
     */
    private double calculateTotalHours() {
        return credits * 3;
    }

    /**
     * Calculates the amount of extra study hours per day based on the number of total hours and class hours.
     * @return Amount of extra study hours per day.
     */
    private double weeklyExtraHours(){
        return totalHours - classHours;
    }

    /**
     * Calculates the amount of extra study hours per week, assuming 6 days of study a week
     * @return amount of extra study hours per week
     */
    private double dailyExtraHours() {
        //TODO: Let the user choose the number of weekly study days.
        return weeklyExtraHours() / 6;
    }

    /**
     * Calculates the amount of extra study hours in the semester, assuming a semester of 16 weeks
     * @return the amount of extra study hours in the semester
     */
    private double semesterExtraHours() {
        //Assuming a 16 week long semester
        return weeklyExtraHours() * 16;
    }

    /**
     * Returns the percentage of graded grades of the subject
     * @return percentage of graded grades of the subject
     */
    public double gradedPercentage() {
        double ans = 0;
            for (Grade currentGrade : grades) {
                if(currentGrade.isGraded()) {
                    ans += currentGrade.getPercentage();
                }
            }
        return ans;
    }

    /**
     * Returns the graded grades of the subject
     * @return graded grades of the subject
     */
    private List<Grade> gradedGrades() {
        List<Grade> ans = new ArrayList<>();
        for(Grade currentGrade : grades){
            if(currentGrade.isGraded()){
                ans.add(currentGrade);
            }
        }
        return ans;
    }

    /**
     * Returns the current subject grade. The calculation is made like this:
     * If the sum of the given grades is 100 it calculates currentGrade * (percentage/100).
     * Else, it calculates currentGrade * (percentage/gradedPercentage).
     * @return Current subject grade
     */
    public double currentGrade() {
        double ans = 0;
        double gradedPercentage = gradedPercentage();
            List<Grade> grades = gradedGrades();
            if( gradedPercentage >= 100.0 ){
                for( Grade currentGrade : grades )
                    ans += (currentGrade.getGrade() * currentGrade.getPercentage()) / 100.0;
            }
            else{
                for( Grade currentGrade : grades )
                    ans += (currentGrade.getGrade() * currentGrade.getPercentage()) / gradedPercentage;
            }
        return ans;
    }

    /**
     * Returns the formatted daily study hours
     * @return formatted daily study hours
     */
    public String dailyHoursString() {
        DecimalFormat format = new DecimalFormat("#.##");
        return format.format(dailyExtraHours);
    }

    /**
     * Returns the daily studied percentage
     * @return daily studied percentage
     */
    public int dailyStudiedPercentage() {
        Double ans = (studiedTimeDay() / dailyExtraHours);
        return ans.intValue();
    }

    /**
     * Returns the weekly studied percentage
     * @return weekly studied percentage
     */
    public int weeklyStudiedPercentage() {
        Double ans = studiedTimeWeek() / weeklyExtraHours;
        return ans.intValue();
    }

    /**
     * Retunrns the semester studied percentage
     * @return semester studied percentage
     */
    public int semesterStudiedPercentage() {
        Double ans = studiedTimeSemester() / semesterExtraHours;
        return ans.intValue();
    }

    /**
     * Creates a new entity with the subject data
     * @return
     */
    public SubjectEntity toEntity() {
        return new SubjectEntity.SubjectEntityBuilder(id, name, credits, classHours, semesterId)
                .setDailyExtraHours(dailyExtraHours)
                .setWeeklyExtraHours(weeklyExtraHours)
                .setSemseterExtraHours(semesterExtraHours)
//                .setStudiedHoursDay(studiedHoursDay)
//                .setStudiedHoursWeek(studiedHoursWeek)
//                .setStudiedHoursSemester(studiedHoursSemester)
                .setTotalHours(totalHours)
                .build();
    }

    /**
     * @return Subject info as a String
     */
    @Override
    public String toString() {
        return name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Subject subject = (Subject) o;

        if (Double.compare(subject.credits, credits) != 0) return false;
        if (Double.compare(subject.totalHours, totalHours) != 0) return false;
        if (Double.compare(subject.classHours, classHours) != 0) return false;
        if (Double.compare(subject.weeklyExtraHours, weeklyExtraHours) != 0) return false;
//        if (Double.compare(subject.studiedHoursDay, studiedHoursDay) != 0) return false;
//        if (Double.compare(subject.studiedHoursWeek, studiedHoursWeek) != 0) return false;
//        if (Double.compare(subject.studiedHoursSemester, studiedHoursSemester) != 0) return false;
        return name.equals(subject.name);
    }

    public static class SubjectBuilder {

        private long id;

        private long semesterId;

        private String name;

        private double credits;

        private double totalHours;

        private double classHours;

        private double dailyExtraHours;

        private double weeklyExtraHours;

        private double semesterExtraHours;

        private List<Grade> grades;

        private List<TimeLog> timeLogs;

        public SubjectBuilder(long id, String name, double credits, double classHours, long semesterId) {
            this.id = id;
            this.semesterId = semesterId;
            this.name = name;
            this.credits = credits;
            this.classHours = classHours;
            grades = new ArrayList<>();
            timeLogs = new ArrayList<>();
        }

        public SubjectBuilder setTotalHours(double totalHours) {
            this.totalHours = totalHours;
            return this;
        }

        public SubjectBuilder setDailyExtraHours(double dailyExtraHours) {
            this.dailyExtraHours = dailyExtraHours;
            return this;
        }


        public SubjectBuilder setWeeklyExtraHours(double weeklyExtraHours) {
            this.weeklyExtraHours = weeklyExtraHours;
            return this;
        }

        public SubjectBuilder setSemesterExtraHours(double semesterExtraHours) {
            this.semesterExtraHours = semesterExtraHours;
            return this;
        }


        public List<Grade> getGrades() {
            return grades;
        }

        public SubjectBuilder setGrades(List<Grade> grades) {
            this.grades = grades;
            return this;
        }

        public List<TimeLog> getTimeLogs() {
            return timeLogs;
        }

        public SubjectBuilder setTimeLogs(List<TimeLog> timeLogs) {
            this.timeLogs = timeLogs;
            return this;
        }

        public Subject build(){
            return new Subject(this);
        }
    }
}
