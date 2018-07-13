package com.inrainbows.persistence.entities;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.ForeignKey;
import android.arch.persistence.room.PrimaryKey;

import com.inrainbows.persistence.daos.SubjectDao;

/**
 * @author diego on 12/07/2018.
 */
@Entity(tableName = "SUBJECT_TASKS",
        foreignKeys = @ForeignKey( entity = SubjectDao.class, parentColumns = "ID", childColumns = "SUBJECT_ID", onDelete = ForeignKey.CASCADE))
public class SubjectTaskEntity {
    /**
     * Subject task id;
     */
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "ID")
    private long id;

    /**
     * Task name.
     */
    @ColumnInfo(name = "NAME")
    private String name;

    /**
     * Task tag
     */
    @ColumnInfo(name = "TAG")
    private String tag;

    /**
     * Task description
     */
    @ColumnInfo(name = "DESCRIPTION")
    private String description;

    /**
     * Done status of the task
     */
    @ColumnInfo(name = "DONE")
    private boolean done;

    /**
     * Task grade.
     */
    @ColumnInfo(name = "GRADE")
    private double grade;


    /**
     * Final subject grade percentage.
     */
    @ColumnInfo(name = "PERCENTAGE")
    private double percentage;

    /**
     * Indicates if task has been delivered.
     */
    @ColumnInfo(name = "DELIVERED")
    private boolean delivered;

    /**
     * Indicates if task has been graded.
     */
    @ColumnInfo(name = "GRADED")
    private boolean graded;

    /**
     * Parent subject id.
     */
    @ColumnInfo(name = "SUBJECT_ID")
    private long subjectId;

    public SubjectTaskEntity() {
    }

    public SubjectTaskEntity(SubjectTaskEntityBuilder builder) {
        this.id = builder.id;
        this.name = builder.name;
        this.percentage = builder.percentage;
        this.subjectId = builder.subjectId;

        this.tag = builder.getTag();
        this.description = builder.getDescription();
        this.done = builder.isDone();
        this.grade = builder.getGrade();
        this.delivered = builder.isDelivered();
        this.graded = builder.graded;
    }

    /**
     * @return task name
     */
    public String getName() {
        return name;
    }

    /**
     * @return task grade
     */
    public double getGrade() {
        return grade;
    }

    /**
     * Sets the task grade to pGrade
     */
    public void setGrade(double pGrade) {
        grade = pGrade;
    }

    /**
     * Sets the task name to pName
     * @param pName new task name
     */
    public void setName(String pName) {
        name = pName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * @return true if the task has been completed, false otherwise
     */
    public boolean isDone() {
        return done;
    }

    /**
     * Sets the done status to pDone
     * @param pDone new done status
     */
    public void setDone(boolean pDone) {
        done = pDone;
    }

    /**
     * @return Task tag
     */
    public String getTag() {
        return tag;
    }

    /**
     * Sets the task tag to pTag
     * @param pTag tag to be set
     */
    public void setTag(String pTag) {
        tag = pTag;
    }

    /**
     * @return percentage of the Subject that the task is worth
     */
    public double getPercentage() {
        return percentage;
    }

    /**
     * Sets the percentage of the task to pPercentage
     * @param pPercentage new percentage of the task
     */
    public void setPercentage(double pPercentage) {
        percentage = pPercentage;
    }

    /**
     * @return true if the task has been graded, false otherwise
     */
    public boolean isGraded() {
        return graded;
    }

    /**
     * Changes the graded status of the task
     * @param pGraded new graded status
     */
    public void setGraded(boolean pGraded) {
        graded = pGraded;
    }

    /**
     * @return true if the task was delivered by the student, false otherwise
     */
    public boolean isDelivered() {
        return delivered;
    }

    /**
     * Changes the delivered status of this task to pDelivered
     * @param pDelivered new delivered status
     */
    public void setDelivered(boolean pDelivered) {

        delivered = pDelivered;
    }
    public long getSubjectId() {
        return subjectId;
    }

    public void setSubjectId(long subjectId) {
        this.subjectId = subjectId;
    }

    public static class SubjectTaskEntityBuilder {

        private long id;

        private String name;

        private String tag;

        private String description;

        private boolean done;

        private double grade;

        private double percentage;

        private boolean delivered;

        private boolean graded;

        private long subjectId;

        public SubjectTaskEntityBuilder(long id, String name, double percentage, long subjectId){
            this.name = name;
            this.percentage = percentage;
            this.id = id;
            this.subjectId = subjectId;
        }

        public String getTag() {
            return tag;
        }

        public SubjectTaskEntityBuilder setTag(String tag) {
            this.tag = tag;
            return this;
        }

        public String getDescription() {
            return description;
        }

        public SubjectTaskEntityBuilder setDescription(String description) {
            this.description = description;
            return this;
        }

        public boolean isDone() {
            return done;
        }

        public SubjectTaskEntityBuilder setDone(boolean done) {
            this.done = done;
            return this;
        }

        public double getGrade() {
            return grade;
        }

        public SubjectTaskEntityBuilder setGrade(double grade) {
            this.grade = grade;
            return this;
        }

        public boolean isDelivered() {
            return delivered;
        }

        public SubjectTaskEntityBuilder setDelivered(boolean delivered) {
            this.delivered = delivered;
            return this;
        }

        public boolean isGraded() {
            return graded;
        }

        public SubjectTaskEntityBuilder setGraded(boolean graded) {
            this.graded = graded;
            return this;
        }

        public SubjectTaskEntity build(){
            return new SubjectTaskEntity(this);
        }
    }
}
