package model.application;

import api.application.ITask;

/**
 * @author diego on 1/06/2017.
 */

public class Task implements ITask{

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
        //shows "grade: 5.0" if task has been graded or "not graded yet" if it hasn't
        String gradeString = ((graded && delivered)? " grade: " : " not graded yet,") + ((grade >=0 )? grade + "," : "");
        //Shows "not delivered yet" if it has not been delivered or Delivered, + gradeString if it has
        String deliveredString = (delivered) ? (" delivered," + gradeString) : " not delivered yet,";
        String doneString = (done)? "done" : "not done yet";
        String tagString = !tag.equals("") ? " " + tag + "," : "";

        //Shows "name, tag, delivered, grade: 5.0, 100%"
        return String.format("%s,%s %s,%s %s", name, tagString, doneString, (done) ? deliveredString : "", percentage + "%");
    }

    /**
     * @return task name
     */
    @Override
    public String getName() {
        return name;
    }

    /**
     * @return task grade
     */
    @Override
    public double getGrade() {
        return grade;
    }

    /**
     * Sets the task grade to pGrade
     */
    @Override
    public void setGrade(double pGrade) {
        grade = pGrade;
        assertTask();
    }

    /**
     * Sets the task name to pName
     * @param pName new task name
     */
    @Override
    public void setName(String pName) {
        name = pName;
        assertTask();
    }

    /**
     * @return true if the task has been completed, false otherwise
     */
    @Override
    public boolean getDone() {
        return done;
    }

    /**
     * Sets the done status to pDone
     * @param pDone new done status
     */
    @Override
    public void setDone(boolean pDone) {
        done = pDone;
        assertTask();
    }

    /**
     * @return Task tag
     */
    @Override
    public String getTag() {
        return tag;
    }

    /**
     * Sets the task tag to pTag
     * @param pTag tag to be set
     */
    @Override
    public void setTag(String pTag) {
        tag = pTag;
        assertTask();
    }

    /**
     * @return percentage of the Subject that the task is worth
     */
    @Override
    public double getPercentage() {
        return percentage;
    }

    /**
     * Sets the percentage of the task to pPercentage
     * @param pPercentage new percentage of the task
     */
    @Override
    public void setPercentage(double pPercentage) {
        percentage = pPercentage;
        assertTask();
    }

    /**
     * @return true if the task has been graded, false otherwise
     */
    @Override
    public boolean getGraded() {
        return graded;
    }

    /**
     * Changes the graded status of the task
     * @param pGraded new graded status
     */
    @Override
    public void setGraded(boolean pGraded) {
        graded = pGraded;
        assertTask();
    }

    /**
     * @return true if the task was delivered by the student, false otherwise
     */
    @Override
    public boolean getDelivered() {
        return delivered;
    }

    /**
     * Changes the delivered status of this task to pDelivered
     * @param pDelivered new delivered status
     */
    @Override
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

        return name.equals(task.name);
    }

    /**
     * Task has code
     * @return task hash code using its name
     */
    @Override
    public int hashCode() {
        return name.hashCode();
    }
}
