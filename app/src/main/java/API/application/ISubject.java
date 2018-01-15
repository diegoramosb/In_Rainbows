package api.application;

import java.util.NoSuchElementException;

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
     * Sets the total hours to pTotalHours
     * @param pTotalHours
     */
    void setTotalHours( double pTotalHours );

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
     * @param pTaskName Name of the task
     * @return true if the subject contains the task with name pName
     * @throws IllegalArgumentException if the task name is not valid
     */
    boolean containsTask( String pTaskName ) throws IllegalArgumentException;

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
    void increaseStudiedHoursSemester( double pStudiedHours );

    /**
     * Changes the task name
     * @param pCurrentName current task name
     * @param pNewName new task name
     * @throws NoSuchElementException if the task is not found
     * @throws IllegalArgumentException if either name is not valid
     */
    void setTaskName(String pCurrentName, String pNewName) throws NoSuchElementException, IllegalArgumentException;

    /**
     * @return an iterable containing the tasks of the subject
     * @throws NoSuchElementException if there are no tasks
     */
    Iterable<Task> getAllTasks() throws NoSuchElementException;

    /**
     * @return an iterable containing only the graded tasks of the subject
     * @throws NoSuchElementException if there are no graded tasks
     */
    Iterable<Task> getGradedTasks() throws NoSuchElementException;

    /**
     * @return an iterable containing only the non-graded tasks of the subject
     * @throws NoSuchElementException if all tasks are graded
     */
    Iterable<Task> getNonGradedTasks() throws NoSuchElementException;

    /**
     * @return an iterable containing only the delivered tasks of the subject
     * @throws NoSuchElementException if there are not delivered tasks
     */
    Iterable<Task> getDeliveredTasks() throws NoSuchElementException;

    /**
     * @return an iterable containing only the non-delivered tasks of the subject
     * @throws NoSuchElementException if all tasks are delivered
     */
    Iterable<Task> getNonDeliveredTasks() throws NoSuchElementException;

    /**
     * @param pTaskName name of the task
     * @return Task with name pTaskName
     * @throws NoSuchElementException if there is no task with name pTaskName
     * @throws IllegalArgumentException if the given name is not valid
     */
    Task getTask( String pTaskName ) throws NoSuchElementException, IllegalArgumentException;

    /**
     * Adds a new task to the subject. It gets marked as non-delivered and non-graded by default
     * @param pTask new task to be added
     */
    void addTask( Task pTask );

    /**
     * Deletes task with name pTaskName from the task list of the subject
     * @param pTaskName name of the task to be deleted
     * @throws NoSuchElementException if there is no task with name pTaskName
     * @throws IllegalArgumentException if the given name is not valid
     */
    void deleteTask( String pTaskName ) throws NoSuchElementException, IllegalArgumentException;

    /**
     * Marks the task with name pTaskName to done
     * @param pTaskName name of the task
     * @throws NoSuchElementException if there is no task with name pTaskName
     * @throws IllegalArgumentException if the given name is not valid
     */
    void markAsDone( String pTaskName ) throws NoSuchElementException, IllegalArgumentException;

    /**
     * Changes the done status of the task with name pTaskName to pDone
     * @param pTaskName name of the task
     * @param pDone new done status
     * @throws NoSuchElementException if there is no task with name pTaskName
     * @throws IllegalArgumentException if the given name is not valid
     */
    void setDone( String pTaskName, boolean pDone ) throws NoSuchElementException, IllegalArgumentException;

    /**
     * Marks the task with name pTaskName to delivered
     * @param pTaskName name of the task
     * @throws NoSuchElementException if there is no task with name pTaskName
     * @throws IllegalArgumentException if the given name is not valid
     */
    void markAsDelivered( String pTaskName ) throws NoSuchElementException, IllegalArgumentException;

    /**
     * Changes the delivered status of task with name pTaskName to pDelivered
     * @param pTaskName name of the task
     * @param pDelivered new delivered status
     * @throws NoSuchElementException if there is no task with name pTaskName
     * @throws IllegalArgumentException if the given name is not valid
     */
    void setDelivered( String pTaskName, boolean pDelivered ) throws NoSuchElementException, IllegalArgumentException;

    /**
     * Marks the task with name pTaskName to graded
     * @param pTaskName name of the task
     * @throws NoSuchElementException if there is no task with name pTaskName
     * @throws IllegalArgumentException if the given name is not valid
     */
    void markAsGraded( String pTaskName ) throws NoSuchElementException, IllegalArgumentException;

    /**
     * Changes the graded status of the task with name pTaskName to pDelivered
     * @param pTaskName name of the task
     * @param pGraded new GradedStatus
     * @throws NoSuchElementException if there is no task with name pTaskName
     * @throws IllegalArgumentException if the given name is not valid
     */
    void setGraded( String pTaskName, boolean pGraded ) throws NoSuchElementException, IllegalArgumentException;

    /**
     * Changes the grade of the task with name pTaskName to pGrade
     * @param pTaskName name of the task
     * @param pGrade new grade
     * @throws NoSuchElementException if there is no task with name pTaskName
     * @throws IllegalArgumentException if the given name or grade are not valid
     */
    void setGrade(String pTaskName, double pGrade ) throws NoSuchElementException, IllegalArgumentException;


    /**
     * @return percentage of tasks of the subject that have been graded
     */
    double getGradedTasksPercentage();

    /**
     * @return current subject grade
     */
    double getCurrentGrade();
}
