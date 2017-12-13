package model.application;

import API.application.ITask;

/**
 * Created by diego on 1/06/2017.
 */

public class Task implements ITask{

    /**
     * Task name.
     */
    private String name;

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
     * Creates a new task.
     * @param pName task name.
     * @param pPercentage final grade percentage of the task.
     */
    public Task(String pName, double pPercentage){
        name = pName;
        percentage = pPercentage;
        grade = -1.0; //Grade set to -1 when task has not been graded
        //Sets delivered and graded to false by default
        delivered = false;
        graded = false;
    }

    /**
     * @return a string indicating the task status
     */
    public String toString()
    {
        //shows "Grade: 5.0" if task has been graded or "Not graded yet if it hasn't"
        String gradeString = ((graded)? "Grade: " : "Not graded yet") + ((grade != -1)? "" : grade);
        //Shows "not delivered yet" if it has not been delivered or Delivered, + gradeString if it has
        String deliveredString = (delivered)? "Delivered, "  + gradeString : "Not delivered yet";
        return name + ", " + deliveredString + ", " + percentage + "%, ";
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
    }

    /**
     * Sets the task name to pName
     * @param pName new task name
     */
    @Override
    public void setName(String pName) {
        name = pName;
    }

    /**
     * @returns percentage of the Subject that the task is worth
     */
    @Override
    public double getPercentage() {
        return percentage;
    }

    /**
     * @returns percentage of the Subject that the task is worth
     */
    @Override
    public void setPercentage(double pPercentage) {
        percentage = pPercentage;
    }

    /**
     * @returns true if the task has been graded, false otherwise
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
    }

    /**
     * @returns true if the task was delivered by the student, false otherwise
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
    }
}
