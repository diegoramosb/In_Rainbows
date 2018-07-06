package com.inrainbows.mvp.model;

/**
 * @author diego on 1/06/2017.
 */

public class SubjectTask implements Task, GradedAssignment {

    /**
     * Task name.
     */
    private String name;

    /**
     * Task tag
     */
    private String tag;

    /**
     * Task description
     */
    private String description;

    /**
     * Done status of the task
     */
    private boolean done;

    /**
     * Task grade.
     */
    private double grade;

    /**
     * Final subject grade percentage.
     */
    private double percentage;

    /**
     * Indicates if task has been delivered.
     */
    private boolean delivered;

    /**
     * Indicates if task has been graded.
     */
    private boolean graded;

    private SubjectTask(TaskBuilder builder){
        this.name = builder.name;
        this.percentage = builder.percentage;

        this.tag = builder.getTag();
        this.description = builder.getDescription();
        this.done = builder.isDone();
        this.grade = builder.getGrade();
        this.delivered = builder.isDelivered();
        this.graded = builder.graded;
    }

    /**
     * @return a string indicating the task status
     */
    public String toString()
    {
        //Shows "not delivered yet" if it has not been delivered or Delivered, + gradeString if it has
        String deliveredString = (delivered) ? "delivered" : "not delivered yet";
        String doneString = (done)? "done" : "not done yet";

        return String.format("%s, %s, %s, %s, %s, %s", name, tag, doneString, deliveredString, grade, percentage + "%");
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

    @Override
    public void markDone() {
        done = true;
    }

    @Override
    public void markUndone() {
        done = false;
    }

    /**
     * Indicates if the task is equal to another object
     * @param o Object to be compared to the task
     * @return true if the object is equal to the task, false otherwise
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        SubjectTask task = (SubjectTask) o;

        if (done != task.done) return false;
        if (Double.compare(task.grade, grade) != 0) return false;
        if (Double.compare(task.percentage, percentage) != 0) return false;
        if (delivered != task.delivered) return false;
        if (graded != task.graded) return false;
        if (!name.equals(task.name)) return false;
        return tag != null ? tag.equals(task.tag) : task.tag == null;
    }

    /**
     * Task has code
     * @return task hash code using its name
     */
    @Override
    public int hashCode() {
        int result;
        long temp;
        result = name.hashCode();
        result = 31 * result + (tag != null ? tag.hashCode() : 0);
        result = 31 * result + (done ? 1 : 0);
        temp = Double.doubleToLongBits(grade);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        temp = Double.doubleToLongBits(percentage);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        result = 31 * result + (delivered ? 1 : 0);
        result = 31 * result + (graded ? 1 : 0);
        return result;
    }


    public static class TaskBuilder {

        private String name;

        private String tag;

        private String description;

        private boolean done;

        private double grade;

        private double percentage;

        private boolean delivered;

        private boolean graded;

        public TaskBuilder(String name, double percentage){
            this.name = name;
            this.percentage = percentage;
        }

        public String getTag() {
            return tag;
        }

        public TaskBuilder setTag(String tag) {
            this.tag = tag;
            return this;
        }

        public String getDescription() {
            return description;
        }

        public TaskBuilder setDescription(String description) {
            this.description = description;
            return this;
        }

        public boolean isDone() {
            return done;
        }

        public TaskBuilder setDone(boolean done) {
            this.done = done;
            return this;
        }

        public double getGrade() {
            return grade;
        }

        public TaskBuilder setGrade(double grade) {
            this.grade = grade;
            return this;
        }

        public boolean isDelivered() {
            return delivered;
        }

        public TaskBuilder setDelivered(boolean delivered) {
            this.delivered = delivered;
            return this;
        }

        public boolean isGraded() {
            return graded;
        }

        public TaskBuilder setGraded(boolean graded) {
            this.graded = graded;
            return this;
        }

        public SubjectTask build(){
            return new SubjectTask(this);
        }
    }
}
