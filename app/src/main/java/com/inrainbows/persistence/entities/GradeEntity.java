package com.inrainbows.persistence.entities;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.ForeignKey;
import android.arch.persistence.room.Index;
import android.support.annotation.NonNull;

import com.inrainbows.mvp.model.Grade;

/**
 * @author diego on 31/07/2018.
 */
@Entity(tableName = "GRADES",
        foreignKeys = @ForeignKey(entity = SubjectEntity.class, parentColumns = "ID", childColumns = "SUBJECT_ID", onDelete = ForeignKey.CASCADE),
        indices = @Index(value = "SUBJECT_ID")
)
public class GradeEntity extends BaseEntity {

    @ColumnInfo(name = "SUBJECT_ID")
    @NonNull
    private long subjectId;

    @ColumnInfo(name = "NAME", typeAffinity = ColumnInfo.TEXT)
    @NonNull
    private String name;

    @ColumnInfo(name = "GRADE", typeAffinity = ColumnInfo.REAL)
    private double grade;

    @ColumnInfo(name = "PERCENTAGE", typeAffinity = ColumnInfo.REAL)
    @NonNull
    private double percentage;

    @ColumnInfo(name = "GRADED")
    @NonNull
    private boolean graded;

    public GradeEntity(long id, Long subjectId, String name, double grade, double percentage, boolean graded) {
        this.id = id;
        this.subjectId = subjectId;
        this.name = name;
        this.grade = grade;
        this.percentage = percentage;
        this.graded = graded;
    }

    public long getSubjectId() {
        return subjectId;
    }

    public GradeEntity setSubjectId(long subjectId) {
        this.subjectId = subjectId;
        return this;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getGrade() {
        return grade;
    }

    public void setGrade(double grade) {
        this.grade = grade;
    }

    public double getPercentage() {
        return percentage;
    }

    public void setPercentage(double percentage) {
        this.percentage = percentage;
    }

    public boolean isGraded() {
        return graded;
    }

    public void setGraded(boolean graded) {
        this.graded = graded;
    }
}
