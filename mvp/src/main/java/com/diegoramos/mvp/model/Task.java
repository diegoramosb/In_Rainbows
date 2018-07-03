package com.diegoramos.mvp.model;

/**
 * @author diego on 1/06/2017.
 */

public class Task {

    /**
     * Task name.
     */
    private String name;

    /**
     * Task tag
     */
    private String tag;

    /**
     * Done status of the task
     */
    private boolean done;

    /**
     * Task grade.
     */
    private double grade;

    /**
     * Final subect grade percentage.
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

    /**
     * Creates a new task setting all properties but name and percentage to their default values
     * @param pName task name.
     * @param pPercentage final grade percentage of the task.
     * @throws IllegalArgumentException if any parameter is not valid
     */
    public Task(String pName, double pPercentage) {
        name = pName;
        percentage = pPercentage;
        //All the other properties set by default
        tag = "";
        done = false;
        grade = -1.0; //Grade set to -1 when task has not been graded
        delivered = false;
        graded = false;
        assertTask();
    }

    /**
     * Creates a new task setting all properties but name, tag and percentage to their default values
     * @param pName task name.
     * @param pTag task tag.
     * @param pPercentage final grade percentage of the task.
     */
    public Task(String pName, String pTag, double pPercentage) {
        name = pName;
        percentage = pPercentage;
        //All the other properties set by default
        tag = pTag;
        done = false;
        grade = -1.0; //Grade set to -1 when task has not been graded
        delivered = false;
        graded = false;
        assertTask();
    }

    /**
     * Creates a new task setting all properties to the values given by parameters
     * @param pName task name
     * @param pTag task tag
     * @param pPercentage task percentage
     * @param pGrade task grade
     * @param pDone task done status
     * @param pDelivered task delivered status
     * @param pGraded task graded status
     */
    public Task( String pName, String pTag, double pPercentage, double pGrade, boolean pDone, boolean pDelivered, boolean pGraded ) {
        name = pName;
        tag = pTag ;
        grade = pGrade;
        percentage = pPercentage;
        done = pDone;
        delivered = pDelivered;
        graded = pGraded;
        assertTask();
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
        assertTask();
    }

    /**
     * Sets the task name to pName
     * @param pName new task name
     */
    public void setName(String pName) {
        name = pName;
        assertTask();
    }

    /**
     * @return true if the task has been completed, false otherwise
     */
    public boolean getDone() {
        return done;
    }

    /**
     * Sets the done status to pDone
     * @param pDone new done status
     */
    public void setDone(boolean pDone) {
        done = pDone;
        assertTask();
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
        assertTask();
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
        assertTask();
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
        assertTask();
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
        assertTask();
    }

    /**
     * Verifies the task fields
     * @throws AssertionError if any field is not valid
     */
    private void assertTask()throws AssertionError{
        if (name == null || name.equals("")) throw new AssertionError("Name not valid");
        if (tag == null) throw new AssertionError("Tag not valid");
        if (!(percentage > 0)) throw new AssertionError("Percentage must be a positive value");
        if (graded) {
            if (!(grade >= 0))
                throw new AssertionError("If the task is graded, its grade cannot be negative");
            if (!delivered) throw new AssertionError("If the task is graded, it should have been delivered");
            if (!done) throw new AssertionError("If the task is graded, it should have been done");
        }
        if(delivered){
            if (!done) throw new AssertionError("If the task was delivered, it should have been done");
        }
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

        Task task = (Task) o;

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
}
