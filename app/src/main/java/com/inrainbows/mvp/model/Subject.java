package com.inrainbows.mvp.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.inrainbows.persistence.entities.GradeEntity;
import com.inrainbows.persistence.entities.SubjectEntity;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by diego on 31/05/2017.
 * This class represents a subject
 */
public class Subject implements Parcelable{

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
     * Extra expected hours of study (hours without counting class)
     */
    private double extraHours;

    /**
     * Actual studied hours on this day
     */
    private double studiedHoursDay;

    /**
     * Actual studied hours this week
     */
    private double studiedHoursWeek;

    /**
     * Actual studied hours this semester
     */
    private double studiedHoursSemester;

    /**
     * Hash table containing grades.
     */
    private List<Grade> grades;

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
        this.extraHours = calculateExtraHours();

        this.studiedHoursDay = builder.getStudiedHoursDay();
        this.studiedHoursWeek = builder.getStudiedHoursWeek();
        this.studiedHoursSemester = builder.getStudiedHoursSemester();
        this.grades = builder.getGrades();
    }

    public Subject(SubjectEntity entity){
        if(entity != null) {
            this.id = entity.getId();
            this.semesterId = entity.getSemesterId();
            this.name = entity.getName();
            this.credits = entity.getCredits();

            this.totalHours = entity.getTotalHours();
            this.classHours = entity.getClassHours();
            this.extraHours = entity.getExtraHours();

            this.studiedHoursDay = entity.getStudiedHoursDay();
            this.studiedHoursWeek = entity.getStudiedHoursWeek();
            this.studiedHoursSemester = entity.getStudiedHoursSemester();
            this.grades = new ArrayList<>();
        }
    }

    @Override
    public String toString() {
        return name;
    }

    /**
     * @return Subject info as a String
     */


    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getSemesterId() {
        return semesterId;
    }

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
     * @return Extra hours of study that the subject requires, meaning totalHours - classHours
     */
    public double getExtraHours() {
        return extraHours;
    }

    /**
     * Sets extraHours to pExtraHours
     *
     * @param pExtraHours new extra hours of the subject
     */
    public void setExtraHours(double pExtraHours) {
        extraHours = pExtraHours;
    }

    /**
     * @return Studied hours for the current day
     */
    public double getStudiedHoursDay() {
        return studiedHoursDay;
    }

    /**
     * Sets the studied hours for the current day to pStudiedHours
     *
     * @param pStudiedHours new studied hours
     */
    public void setStudiedHoursDay(double pStudiedHours) {
        studiedHoursDay = pStudiedHours;
    }

    /**
     * Increases the studied hours of the current day by pStudiedHours
     *
     * @param pStudiedHours hours to increase
     */
    public void addStudiedHoursDay(double pStudiedHours) {
        studiedHoursDay += pStudiedHours;
    }

    public void removeStudiedHoursDay(double hours) {
        studiedHoursDay -= hours;
    }

    /**
     * @return Studied hours this week in the subject
     */
    public double getStudiedHoursWeek() {
        return studiedHoursWeek;
    }

    /**
     * Sets the amount of studied hours this week to pStudiedHours
     *
     * @param pStudiedHours new amount of studied hours this week
     */
    public void setStudiedHoursWeek(double pStudiedHours) {
        studiedHoursWeek = pStudiedHours;
    }

    /**
     * Increases the amount of studied hours this week by pStudiedHours
     *
     * @param pStudiedHours hours to increase
     */
    public void addStudiedHoursWeek(double pStudiedHours) {
        studiedHoursWeek += pStudiedHours;
    }

    public void removeStudiedHoursWeek(double hours) {
        studiedHoursWeek -= hours;
    }

    /**
     * @return Amount of studiedHours this semester in the subject
     */
    public double getStudiedHoursSemester() {
        return studiedHoursSemester;
    }

    /**
     * Sets the amount of studied hours of this subject in this semester to pStudiedHours
     *
     * @param pStudiedHours new amount of studied hours
     */
    public void setStudiedHoursSemester(double pStudiedHours) {
        studiedHoursSemester = pStudiedHours;
    }

    /**
     * Increases the amount of studied hours of this subject in this semester by pStudiedHours
     *
     * @param pStudiedHours hours to increase
     */
    public void addStudiedHoursSemester(double pStudiedHours) {
        studiedHoursSemester += pStudiedHours;
    }

    public void removeStudiedHoursSemester(double hours) {
        studiedHoursSemester -= hours;
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
     * Calculates the amount of extra study hours per week based on the number of total hours and class hours.
     * @return Amount of extra study hours per week.
     */
    private double calculateExtraHours(){
        return totalHours - classHours;
    }

    public void setGrades(List<Grade> grades) {
        this.grades = grades;
    }

    public List<Grade> getGrades(){
        return grades;
    }

    public void addGrade(Grade grade) {
        grades.add(grade);
    }

    public double getGradedPercentage() {
        double ans = 0;
            for (Grade currentGrade : grades) {
                if(currentGrade.isGraded()) {
                    ans += currentGrade.getPercentage();
                }
            }
        return ans;
    }

    public List<Grade> graded() {
        List<Grade> ans = new ArrayList<>();
        for(Grade currentGrade : grades){
            if(currentGrade.isGraded()){
                ans.add(currentGrade);
            }
        }
        return ans;
    }

    /**
     * Retorna la nota actual de la materia. El cálculo se hace así: Si la suma de las notas
     * entregadas es 100 se calcula calificacionActual * (porcentaje / 100). Sino, se calcula
     * calificacionActual * (porcentaje / porcentajeNotasEntregadas).
     * @return ITask actual de la materia (solo de las notas entregadas)
     */
    public double currentGrade() {
        double ans = 0;
        double gradedPercentage = getGradedPercentage();
            List<Grade> grades = graded();
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

    public double dailyHours() {
        //TODO: Let the user choose the number of weekly study days.
        return extraHours / 6;
    }

    public String dailyHoursString() {
        DecimalFormat format = new DecimalFormat("#.##");
        return format.format(dailyHours());
    }

    public int dailyStudiedPercentage() {
        Double ans = (studiedHoursWeek / dailyHours());
        return ans.intValue();
    }

    public int weeklyStudiedPercentage() {
        Double ans = studiedHoursWeek / extraHours;
        return ans.intValue();
    }

    public double semesterHours() {
        //TODO: Calculate the semester hours counting the number of weeks.
        return extraHours * 16;
    }

    public int semesterStudiedPercentage() {
        Double ans = studiedHoursSemester / semesterHours();
        return ans.intValue();
    }

    public SubjectEntity toEntity(long semesterId){
        return new SubjectEntity.SubjectEntityBuilder(id, name, credits, classHours, semesterId).build();
    }

    public List<GradeEntity> gradesToEntity(){
        ArrayList<GradeEntity> ans = new ArrayList<>();
        for(Grade grade : grades){
            ans.add(grade.toEntity());
        }
        return ans;
    }

    public SubjectEntity toEntity() {
        return new SubjectEntity(id, name, credits, totalHours, classHours, extraHours,
                studiedHoursDay, studiedHoursWeek, studiedHoursSemester, semesterId);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Subject subject = (Subject) o;

        if (Double.compare(subject.credits, credits) != 0) return false;
        if (Double.compare(subject.totalHours, totalHours) != 0) return false;
        if (Double.compare(subject.classHours, classHours) != 0) return false;
        if (Double.compare(subject.extraHours, extraHours) != 0) return false;
        if (Double.compare(subject.studiedHoursDay, studiedHoursDay) != 0) return false;
        if (Double.compare(subject.studiedHoursWeek, studiedHoursWeek) != 0) return false;
        if (Double.compare(subject.studiedHoursSemester, studiedHoursSemester) != 0) return false;
        return name.equals(subject.name);
    }

    @Override
    public int hashCode() {
        int result;
        long temp;
        result = name.hashCode();
        temp = Double.doubleToLongBits(credits);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        temp = Double.doubleToLongBits(totalHours);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        temp = Double.doubleToLongBits(classHours);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        temp = Double.doubleToLongBits(extraHours);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        temp = Double.doubleToLongBits(studiedHoursDay);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        temp = Double.doubleToLongBits(studiedHoursWeek);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        temp = Double.doubleToLongBits(studiedHoursSemester);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        return result;
    }

    public static class SubjectBuilder {

        private long id;

        private long semesterId;

        private String name;

        private double credits;

        private double totalHours;

        private double classHours;

        private double extraHours;

        private double studiedHoursDay;

        private double studiedHoursWeek;

        private double studiedHoursSemester;

        private List<Grade> grades;

        public SubjectBuilder(long id, String name, double credits, double classHours, long semesterId) {
            this.id = id;
            this.semesterId = semesterId;
            this.name = name;
            this.credits = credits;
            this.classHours = classHours;
            grades = new ArrayList<>();
        }

        public double getTotalHours() {
            return totalHours;
        }

        public SubjectBuilder setTotalHours(double totalHours) {
            this.totalHours = totalHours;
            return this;
        }

        public double getExtraHours() {
            return extraHours;
        }

        public SubjectBuilder setExtraHours(double extraHours) {
            this.extraHours = extraHours;
            return this;
        }

        public double getStudiedHoursDay() {
            return studiedHoursDay;
        }

        public SubjectBuilder setStudiedHoursDay(double studiedHoursDay) {
            this.studiedHoursDay = studiedHoursDay;
            return this;
        }

        public double getStudiedHoursWeek() {
            return studiedHoursWeek;
        }

        public SubjectBuilder setStudiedHoursWeek(double studiedHoursWeek) {
            this.studiedHoursWeek = studiedHoursWeek;
            return this;
        }

        public double getStudiedHoursSemester() {
            return studiedHoursSemester;
        }

        public SubjectBuilder setStudiedHoursSemester(double studiedHoursSemester) {
            this.studiedHoursSemester = studiedHoursSemester;
            return this;
        }

        public List<Grade> getGrades() {
            return grades;
        }

        public SubjectBuilder setGrades(List<Grade> grades) {
            this.grades = grades;
            return this;
        }

        public Subject build(){
            return new Subject(this);
        }
    }

    protected Subject(Parcel in){
        id = in.readLong();
        semesterId = in.readLong();
        name = in.readString();
        credits = in.readDouble();
        totalHours = in.readDouble();
        classHours = in.readDouble();
        extraHours = in.readDouble();
        studiedHoursDay = in.readDouble();
        studiedHoursWeek = in.readDouble();
        studiedHoursSemester = in.readDouble();
        grades = new ArrayList<>();
        in.readTypedList(grades, Grade.CREATOR);
        System.out.println(grades.toString());
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeLong(id);
        dest.writeLong(semesterId);
        dest.writeString(name);
        dest.writeDouble(totalHours);
        dest.writeDouble(classHours);
        dest.writeDouble(extraHours);
        dest.writeDouble(studiedHoursDay);
        dest.writeDouble(studiedHoursWeek);
        dest.writeDouble(studiedHoursSemester);
        dest.writeTypedList(grades);
    }

    @SuppressWarnings("unused")
    public static final Parcelable.Creator<Subject> CREATOR = new Parcelable.Creator<Subject>() {
        @Override
        public Subject createFromParcel(Parcel in) {
            return new Subject(in);
        }

        @Override
        public Subject[] newArray(int size) {
            return new Subject[size];
        }
    };
}
