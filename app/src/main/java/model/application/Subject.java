package model.application;

import api.application.ISubject;
import model.data_structures.Bag;
import model.data_structures.LinearProbingHash;

/**
 * Created by diego on 31/05/2017.
 * This class represents a subject
 */
public class Subject implements ISubject {

    /**
     * Subject name
     */
    private String name;

    /**
     * Amount of credits the subject is worth
     */
    private double credits;

    /**
     * Total expected weekly hours of study
     */
    private double totalHours;

    /**
     * Weekly hours of class
     */
    private double classHours;

    /**
     * Extra expected hours of study (hours without counting class)
     */
    private double extraHours;

    /**
     * Actual studied hours on this day
     */
    private double studiedHoursDay;

    /**
     * Actual studied hours this week
     */
    private double studiedHoursWeek;

    /**
     * Actual studied hours this semester
     */
    private double studiedHoursSemester;

    /**
     * Hash table containing tasks.
     */
    private LinearProbingHash<Integer, Task> tasks;

    /**
     * Creates a new subject, total hours are number of credits * 3 by default, extra hours are total hours - class hours.
     * @param pName Subject name
     * @param pCredits Amount of credits subject is worth
     * @param pClassHours Weekly hours of class of this subject
     */
    public Subject(String pName, int pCredits, double pClassHours ){
        name = pName;
        credits = pCredits;
        classHours = pClassHours;
        studiedHoursDay = 0;
        studiedHoursWeek = 0;
        studiedHoursSemester = 0;
        totalHours = pCredits * 3;
        extraHours = totalHours - classHours;
        tasks = new LinearProbingHash<>(10);
        assertSubject();
    }

    public Subject( String pName, int pCredits, double pTotalHours, double pClassHours, double pExtraHours, double pStudiedHoursDay, double pStudiedHoursWeek, double pStudiedHoursSemester, Iterable<Task> pTasks ) throws IllegalArgumentException{
        name = pName;
        credits = pCredits;
        classHours = pClassHours;
        studiedHoursDay = pStudiedHoursDay;
        studiedHoursWeek = pStudiedHoursWeek;
        studiedHoursSemester = pStudiedHoursSemester;
        totalHours = pTotalHours;
        extraHours = pExtraHours;
        tasks = new LinearProbingHash<>(10);
        for(Task currentTask : pTasks){
            tasks.put(currentTask.hashCode(), currentTask);
        }
        assertSubject();
    }
    /**
     * @return Subject info as a String
     */
    public String toString(){
        //Shows "Name, 3 credits, 5.0, 50% graded"
        return name + ", " + credits + " credits, " + getCurrentGrade() + ", " + getGradedTasksPercentage() + "% graded";
    }

    /**
     * @return Subject name
     */
    @Override
    public String getName() {
        return name;
    }

    /**
     * Sets the subject name to pName
     *
     * @param pName new subject name
     */
    @Override
    public void setName(String pName) {
        name = pName;
    }

    /**
     * @return Credits the subject is worth
     */
    @Override
    public double getCredits() {
        return credits;
    }

    /**
     * Sets the subject credits to pCredits
     *
     * @param pCredits new amount of credits the subject is worth.
     */
    @Override
    public void setCredits(double pCredits) {
        credits = pCredits;
    }

    /**
     * @return Weekly hours of class of the subject
     */
    @Override
    public double getClassHours() {
        return classHours;
    }

    /**
     * Sets weekly hours of class to pClassHours
     *
     * @param pClassHours new weekly hours of class
     */
    @Override
    public void setClassHours(double pClassHours) {
        classHours = pClassHours;
    }

    /**
     * @return Total hours of study that the subject requires, by default credits * 3.
     */
    @Override
    public double getTotalHours() {
        return totalHours;
    }

    /**
     * Sets the total hours to pTotalHours
     * @param pTotalHours new studied hours
     */
    @Override
    public void setTotalHours( double pTotalHours ){
        totalHours = pTotalHours;
    }

    /**
     * @return Extra hours of study that the subject requires, meaning totalHours - classHours
     */
    @Override
    public double getExtraHours() {
        return extraHours;
    }

    /**
     * Sets extraHours to pExtraHours
     *
     * @param pExtraHours new extra hours of the subject
     */
    @Override
    public void setExtraHours(double pExtraHours) {
        extraHours = pExtraHours;
    }

    /**
     * @return Studied hours for the current day
     */
    @Override
    public double getStudiedHoursDay() {
        return studiedHoursDay;
    }

    /**
     * Sets the studied hours for the current day to pStudiedHours
     *
     * @param pStudiedHours new studied hours
     */
    @Override
    public void setStudiedHoursDay(double pStudiedHours) {
        studiedHoursDay = pStudiedHours;
    }

    /**
     * Increases the studied hours of the current day by pStudiedHours
     *
     * @param pStudiedHours hours to increase
     */
    @Override
    public void increaseStudiedHoursDay(double pStudiedHours) {
        studiedHoursDay += pStudiedHours;
    }

    /**
     * @return Studied hours this week in the subject
     */
    @Override
    public double getStudiedHoursWeek() {
        return studiedHoursWeek;
    }

    /**
     * Sets the amount of studied hours this week to pStudiedHours
     *
     * @param pStudiedHours new amount of studied hours this week
     */
    @Override
    public void setStudiedHoursWeek(double pStudiedHours) {
        studiedHoursWeek = pStudiedHours;
    }

    /**
     * Increases the amount of studied hours this week by pStudiedHours
     *
     * @param pStudiedHours hours to increase
     */
    @Override
    public void increaseStudiedHoursWeek(double pStudiedHours) {
        studiedHoursWeek += pStudiedHours;
    }

    /**
     * @return Amount of studiedHours this semester in the subject
     */
    @Override
    public double getStudiedHoursSemester() {
        return studiedHoursSemester;
    }

    /**
     * Sets the amount of studied hours of this subject in this semester to pStudiedHours
     *
     * @param pStudiedHours new amount of studied hours
     */
    @Override
    public void setStudiedHoursSemester(double pStudiedHours) {
        studiedHoursSemester = pStudiedHours;
    }

    /**
     * Increases the amount of studied hours of this subject in this semester by pStudiedHours
     *
     * @param pStudiedHours hours to increase
     */
    @Override
    public void increaseStudiedHoursSemester(double pStudiedHours) {
        studiedHoursSemester += pStudiedHours;
    }

    /**
     * @return an iterable containing the tasks of the subject
     */
    @Override
    public Iterable<Task> getAllTasks() {
        return null;
    }

    /**
     * @return an iterable containing only the graded tasks of the subject
     */
    @Override
    public Iterable<Task> getGradedTasks() {
        Bag<Task> ans = new Bag<>();
        for( Integer currentKey : tasks.keys() ){ //Iterates over the hash table keys
            Task currentTask = tasks.get(currentKey); //Gets the current task
            if( currentTask.getGraded() )
                ans.addAtEnd(currentTask); //Adds the current task to the answer if it was graded
        }
        return ans;
    }

    /**
     * @return an iterable containing only the non-graded tasks of the subject
     */
    @Override
    public Iterable<Task> getNonGradedTasks() {
        Bag<Task> ans = new Bag<>();
        for( Integer currentKey : tasks.keys() ){ //Iterates over the hash table keys
            Task currentTask = tasks.get(currentKey); //Gets the current task
            if( !currentTask.getGraded() )
                ans.addAtEnd(currentTask); //Adds the current task to the answer if it wasn't graded
        }
        return ans;
    }

    /**
     * @return an iterable containing only the delivered tasks of the subject
     */
    @Override
    public Iterable<Task> getDeliveredTasks() {
        Bag<Task> ans = new Bag<>();
        for( Integer currentKey : tasks.keys() ){ //Iterates over the hash table keys
            Task currentTask = tasks.get(currentKey); //Gets the current task
            if( currentTask.getDelivered() )
                ans.addAtEnd(currentTask); //Adds the current task to the answer if it was delivered
        }
        return ans;
    }

    /**
     * @return an iterable containing only the non-delivered tasks of the subject
     */
    @Override
    public Iterable<Task> getNonDeliveredTasks() {
        Bag<Task> ans = new Bag<>();
        for( Integer currentKey : tasks.keys() ){ //Iterates over the hash table keys
            Task currentTask = tasks.get(currentKey); //Gets the current task
            if( !currentTask.getDelivered() )
                ans.addAtEnd(currentTask); //Adds the current task to the answer if it wasn't delivered
        }
        return ans;
    }

    /**
     * @param pTaskName name of the task
     * @return Task with name pTaskName
     */
    @Override
    public Task getTask(String pTaskName) {
        return null;
    }

    /**
     * Adds a new task to the subject. It gets marked as non-delivered and non-graded by default
     *
     * @param pTask new task to be added
     */
    @Override
    public void addTask(Task pTask) {
        tasks.put(pTask.hashCode(), pTask);
    }

    /**
     * Deletes task with name pTaskName from the task list of the subject
     *
     * @param pTaskName name of the task to be deleted
     */
    @Override
    public void deleteTask(String pTaskName) {
        tasks.delete(pTaskName.hashCode());
    }

    /**
     * Marks the task with name pTaskName to delivered
     *
     * @param pTaskName name of the task
     */
    @Override
    public void markAsDelivered(String pTaskName) {
        tasks.get(pTaskName.hashCode()).setDelivered(true);
    }

    /**
     * Changes the delivered status of task with name pTaskName to pDelivered
     *
     * @param pTaskName  name of the task
     * @param pDelivered new delivered status
     */
    @Override
    public void setDelivered(String pTaskName, boolean pDelivered) {
        tasks.get(pTaskName.hashCode()).setDelivered(pDelivered);
    }

    /**
     * Marks the task with name pTaskName to graded
     * @param pTaskName name of the task
     */
    @Override
    public void markAsGraded(String pTaskName) {
        tasks.get(pTaskName.hashCode()).setGraded(true);
    }

    /**
     * Changes the graded status of the task with name pTaskName to pDelivered
     *
     * @param pTaskName  name of the task
     * @param pGraded new GradedStatus
     */
    @Override
    public void setGraded(String pTaskName, boolean pGraded) {
        tasks.get(pTaskName.hashCode()).setGraded(pGraded);
    }

    /**
     * @return percentage of tasks of the subject that have been graded
     */
    @Override
    public double getGradedTasksPercentage() {
        double ans = 0;
        //Adds the percentage of all graded tasks to the answer
        for( Task currentTask : getGradedTasks() ){
            ans += currentTask.getPercentage();
        }
        return ans;
    }

    /**
     * @return current subject grade
     */
    /**
     * Retorna la nota actual de la materia. El cálculo se hace así: Si la suma de las notas
     * entregadas es 100 se calcula calificacionActual * (porcentaje / 100). Sino, se calcula
     * calificacionActual * (porcentaje / porcentajeNotasEntregadas).
     * @return ITask actual de la materia (solo de las notas entregadas)
     */
    @Override
    public double getCurrentGrade() {
        double ans = 0;
        double gradedPercentage = getGradedTasksPercentage();
        Iterable<Task> gradedTasks = getGradedTasks();
        if( gradedPercentage >= 100.0 ){
            for( Task currentTask : gradedTasks )
                ans += (currentTask.getGrade() * currentTask.getPercentage()) / 100.0;
        }
        else{
            for( Task currentTask : gradedTasks )
                ans += (currentTask.getGrade() * currentTask.getPercentage()) / gradedPercentage;
        }
        return ans;
    }

    /**
     * Verifies the subject fields
     * @throws AssertionError if any field has a non-valid value
     */
    private void assertSubject() throws AssertionError{
        if (name == null || !name.equals("")) throw new AssertionError("Name not valid");
        if (!(credits > 0)) throw new AssertionError("Credits must be a positive value");
        if (!(totalHours > 0)) throw new AssertionError("Total hours must be a positive value");
        if (!(classHours > 0)) throw new AssertionError("Class hours must be a positive value");
        if (!(extraHours > 0)) throw new AssertionError("Extra hours must be a positive value");
        if (!(studiedHoursDay >= 0)) throw new AssertionError("Studied hours in day cannot be a negative value");
        if (!(studiedHoursWeek >= 0)) throw new AssertionError("Studied hours in week cannot be a negative value");
        if (!(studiedHoursSemester >= 0)) throw new AssertionError("Studied hours in semester cannot be a negative value");
        if (tasks == null) throw new AssertionError("Tasks cannot be null");
    }
}
