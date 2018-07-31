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
    private Long subjectId;

    @ColumnInfo(name = "NAME", typeAffinity = ColumnInfo.TEXT)
    @NonNull
    private String name;

    @ColumnInfo(name = "GRADE", typeAffinity = ColumnInfo.REAL)
    private Double grade;

    @ColumnInfo(name = "PERCENTAGE", typeAffinity = ColumnInfo.REAL)
    @NonNull
    private Double percentage;

    @ColumnInfo(name = "GRADED")
    @NonNull
    private Boolean graded;

    public GradeEntity(long id, Long subjectId, String name, Double grade, Double percentage, Boolean graded) {
        this.id = id;
        this.subjectId = subjectId;
        this.name = name;
        this.grade = grade;
        this.percentage = percentage;
        this.graded = graded;
    }

    public Long getSubjectId() {
        return subjectId;
    }

    public GradeEntity setSubjectId(Long subjectId) {
        this.subjectId = subjectId;
        return this;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Double getGrade() {
        return grade;
    }

    public void setGrade(Double grade) {
        this.grade = grade;
    }

    public Double getPercentage() {
        return percentage;
    }

    public void setPercentage(Double percentage) {
        this.percentage = percentage;
    }

    public Boolean isGraded() {
        return graded;
    }

    public void setGraded(Boolean graded) {
        this.graded = graded;
    }
}
