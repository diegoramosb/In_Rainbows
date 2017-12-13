package API.application;

import model.application.Task;

/**
 * @author Diego
 * Created by diego on 11/12/2017.
 */

public interface ISubject {
    /**
     * @return Subject name
     */
    String getName();

    /**
     * Sets the subject name to pName
     * @param pName new subject name
     */
    void setName( String pName );

    /**
     * @return Credits the subject is worth
     */
    double getCredits();

    /**
     * Sets the subject credits to pCredits
     * @param pCredits new amount of credits the subject is worth.
     */
    void setCredits( double pCredits );

    /**
     * @return Weekly hours of class of the subject
     */
    double getClassHours();

    /**
     * Sets weekly hours of class to pClassHours
     * @param pClassHours new weekly hours of class
     */
    void setClassHours( double pClassHours );

    /**
     * @return Total hours of study that the subject requires, by default credits * 3.
     */
    double getTotalHours();

    /**
     * @return Extra hours of study that the subject requires, meaning totalHours - classHours
     */
    double getExtraHours();

    /**
     * Sets extraHours to pExtraHours
     * @param pExtraHours new extra hours of the subject
     */
    void setExtraHours( double pExtraHours );

    /**
     * @return Studied hours for the current day
     */
    double getStudiedHoursDay();

    /**
     * Sets the studied hours for the current day to pStudiedHours
     * @param pStudiedHours new studied hours
     */
    void setStudiedHoursDay( double pStudiedHours );

    /**
     * Increases the studied hours of the current day by pStudiedHours
     * @param pStudiedHours hours to increase
     */
    void increaseStudiedHoursDay( double pStudiedHours );

    /**
     * @return Studied hours this week in the subject
     */
    double getStudiedHoursWeek();

    /**
     * Sets the amount of studied hours this week to pStudiedHours
     * @param pStudiedHours new amount of studied hours this week
     */
    void setStudiedHoursWeek( double pStudiedHours );

    /**
     * Increases the amount of studied hours this week by pStudiedHours
     * @param pStudiedHours hours to increase
     */
    void increaseStudiedHoursWeek( double pStudiedHours );

    /**
     * @return Amount of studiedHours this semester in the subject
     */
    double getStudiedHoursSemester();

    /**
     * Sets the amount of studied hours of this subject in this semester to pStudiedHours
     * @param pStudiedHours new amount of studied hours
     */
    void setStudiedHoursSemester( double pStudiedHours );

    /**
     * Increases the amount of studied hours of this subject in this semester by pStudiedHours
     * @param pStudiedHours hours to increase
     */
    void increaseStdudiedHoursSemester( double pStudiedHours );

    /**
     * @return an iterable containing the tasks of the subject
     */
    Iterable<Task> getAllTasks();

    /**
     * @return an iterable containing only the graded tasks of the subject
     */
    Iterable<Task> getGradedTasks();

    /**
     * @return an iterable containing only the non-graded tasks of the subject
     */
    Iterable<Task> getNonGradedTasks();

    /**
     * @return an iterable containing only the delivered tasks of the subject
     */
    Iterable<Task> getDeliveredTasks();

    /**
     * @return an iterable containing only the non-delivered tasks of the subject
     */
    Iterable<Task> getNonDeliveredTasks();

    /**
     * @param pTaskName name of the task
     * @return Task with name pTaskName
     */
    Task getTask( String pTaskName );

    /**
     * Adds a new task to the subject. It gets marked as non-delivered and non-graded by default
     * @param pTask new task to be added
     */
    void addTask( Task pTask );

    /**
     * Deletes task with name pTaskName from the task list of the subject
     * @param pTaskName name of the task to be deleted
     */
    void deleteTask( String pTaskName );

    /**
     * Marks the task with name pTaskName to delivered
     * @param pTaskName name of the task
     */
    void markAsDelivered( String pTaskName );

    /**
     * Changes the delivered status of task with name pTaskName to pDelivered
     * @param pTaskName name of the task
     * @param pDelivered new delivered status
     */
    void setDelivered( String pTaskName, boolean pDelivered );

    /**
     * Marks the task with name pTaskName to graded
     * @param pTaskName name of the task
     */
    void markAsGraded( String pTaskName );

    /**
     * Changes the graded status of the task with name pTaskName to pDelivered
     * @param pTaskName name of the task
     * @param pDelivered new GradedStatus
     */
    void setGraded( String pTaskName, boolean pDelivered );


    /**
     * @return percentage of tasks of the subject that have been graded
     */
    double getGradedTasksPercentage();

    /**
     * @return current subject grade
     */
    double getCurrentGrade();
}
