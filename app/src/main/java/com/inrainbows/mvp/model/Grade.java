package com.inrainbows.mvp.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.inrainbows.persistence.entities.GradeEntity;

import org.joda.time.DateTime;

/**
 * @author diego on 31/07/2018.
 */
@org.parceler.Parcel(org.parceler.Parcel.Serialization.BEAN)
public class Grade implements GradedAssignment{

    long id;

    long subjectId;

    String name;

    double grade;

    double percentage;

    boolean graded;

    /**
     * Empty constructor for Parceler
     */
    Grade() {
    }

    private Grade(GradeBuilder builder){
        this.id = builder.id;
        this.subjectId = builder.subjectId;
        this.name = builder.name;
        this.percentage = builder.percentage;

        this.grade = builder.getGrade();
        this.graded = builder.isGraded();
    }

    public Grade(GradeEntity entity) {
        this.id = entity.getId();
        this.subjectId = entity.getSubjectId();
        this.name = entity.getName();
        this.percentage = entity.getPercentage();
        this.grade = entity.getGrade();
        this.graded = entity.isGraded();
    }


    @Override
    public long getId() {
        return id;
    }

    @Override
    public void setId(long id) {
        this.id = id;
    }

    public long getSubjectId() {
        return subjectId;
    }

    public Grade setSubjectId(long subjectId) {
        this.subjectId = subjectId;
        return this;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public void setName(String name) {
        this.name = name;
    }

    @Override
    public double getGrade() {
        return grade;
    }

    @Override
    public void setGrade(double grade) {
        this.grade = grade;
    }

    @Override
    public double getPercentage() {
        return percentage;
    }

    @Override
    public void setPercentage(double percentage) {
        this.percentage = percentage;
    }

    @Override
    public boolean isGraded() {
        return graded;
    }

    @Override
    public void setGraded(boolean graded) {
        this.graded = graded;
    }

    public GradeEntity toEntity(){
        return new GradeEntity(id, subjectId, name, grade, percentage, graded);
    }

    public static class GradeBuilder {

        private long id;

        public long subjectId;

        private String name;

        private double grade;

        private double percentage;

        private boolean graded;

        public GradeBuilder(long id, long subjectId, String name, double percentage) {
            this.id = id;
            this.subjectId = subjectId;
            this.name = name;
            this.percentage = percentage;

            this.graded = false;
            this.grade = -1;
        }

        public double getGrade() {
            return grade;
        }

        public GradeBuilder setGrade(double grade) {
            this.grade = grade;
            return this;
        }


        public boolean isGraded() {
            return graded;
        }

        public GradeBuilder setGraded(boolean graded) {
            this.graded = graded;
            return this;
        }

        public Grade build(){
            return new Grade(this);
        }
    }
}
