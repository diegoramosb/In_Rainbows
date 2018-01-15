package model.application;

import java.util.NoSuchElementException;

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
    public Subject(String pName, int pCredits, double pClassHours){
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
    public String toString() {
        //Shows "Name, 3 credits, 5.0, 50% graded"
        return name + ", " + credits + " credits, " + "class hours: " + classHours + "extra: " + extraHours + "total: " + totalHours + "day: " + studiedHoursDay + "week:" + studiedHoursWeek + "semester: " + studiedHoursSemester + getCurrentGrade() + ", " + getGradedTasksPercentage() + "% graded" + tasks;
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
     * @param pTaskName Name of the task
     * @return true if the subject contains the task with name pName
     * @throws IllegalArgumentException if the task name is not valid
     */
    @Override
    public boolean containsTask(String pTaskName) throws IllegalArgumentException {
        try {
            if( !pTaskName.equals("") )
                return tasks.contains(pTaskName.hashCode());
            else
                throw new IllegalArgumentException("Task name not valid");
        }catch (IllegalArgumentException e){
            throw new IllegalArgumentException("Task name not valid");
        }
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
     * Changes the task name
     *
     * @param pCurrentName current task name
     * @param pNewName     new task name
     * @throws NoSuchElementException   if the task is not found
     * @throws IllegalArgumentException if either name is not valid
     */
    @Override
    public void setTaskName(String pCurrentName, String pNewName) throws NoSuchElementException, IllegalArgumentException {
        if( !pCurrentName.equals("") && !pCurrentName.equals("") ){
            if (tasks.contains(pCurrentName.hashCode())) {
                //Makes a copy of the task and deletes it
                int oldHash = pCurrentName.hashCode();
                Task temp = tasks.get(oldHash);
                tasks.delete(oldHash);
                //Changese the copy name and puts it in the task with the new hashCode
                temp.setName(pNewName);
                tasks.put(pNewName.hashCode(), temp);
            } else
                throw new NoSuchElementException("Task not found.");
        }else
            throw new IllegalArgumentException("Name not valid");
    }

    /**
     * @return an iterable containing the tasks of the subject
     * @throws NoSuchElementException if there are no tasks
     */
    @Override
    public Iterable<Task> getAllTasks() throws NoSuchElementException {
        if( tasks.isEmpty() )
            throw new NoSuchElementException("The subject has no tasks");
        Bag<Task> ans = new Bag<>();
        for (Integer currentKey : tasks.keys()) { //Iterates over the hash table keys
            Task currentTask = tasks.get(currentKey); //Gets the current task
            ans.addAtEnd(currentTask); //Adds the current task to the answer
        }
        return ans;
    }

    /**
     * @return an iterable containing only the graded tasks of the subject
     * @throws NoSuchElementException if there are no graded tasks
     */
    @Override
    public Iterable<Task> getGradedTasks() throws NoSuchElementException {
        Bag<Task> ans = new Bag<>();
        for( Integer currentKey : tasks.keys() ){ //Iterates over the hash table keys
            Task currentTask = tasks.get(currentKey); //Gets the current task
            if( currentTask.getGraded() )
                ans.addAtEnd(currentTask); //Adds the current task to the answer if it was graded
        }
        if( ans.isEmpty() )
            throw new NoSuchElementException("The subject does not have any graded tasks");
        return ans;
    }

    /**
     * @return an iterable containing only the non-graded tasks of the subject
     * @throws NoSuchElementException if all tasks are graded
     */
    @Override
    public Iterable<Task> getNonGradedTasks() throws NoSuchElementException {
        Bag<Task> ans = new Bag<>();
        for( Integer currentKey : tasks.keys() ){ //Iterates over the hash table keys
            Task currentTask = tasks.get(currentKey); //Gets the current task
            if( !currentTask.getGraded() )
                ans.addAtEnd(currentTask); //Adds the current task to the answer if it wasn't graded
        }
        if( ans.isEmpty() )
            throw new NoSuchElementException("All tasks in the subject are graded");
        return ans;
    }

    /**
     * @return an iterable containing only the delivered tasks of the subject
     * @throws NoSuchElementException if there are not delivered tasks
     */
    @Override
    public Iterable<Task> getDeliveredTasks() throws NoSuchElementException {
        Bag<Task> ans = new Bag<>();
        for( Integer currentKey : tasks.keys() ){ //Iterates over the hash table keys
            Task currentTask = tasks.get(currentKey); //Gets the current task
            if( currentTask.getDelivered() )
                ans.addAtEnd(currentTask); //Adds the current task to the answer if it was delivered
        }
        if( ans.isEmpty() )
            throw new NoSuchElementException("The subject has no delivered tasks");
        return ans;
    }

    /**
     * @return an iterable containing only the non-delivered tasks of the subject
     * @throws NoSuchElementException if all tasks are delivered
     */
    @Override
    public Iterable<Task> getNonDeliveredTasks() throws NoSuchElementException {
        Bag<Task> ans = new Bag<>();
        for( Integer currentKey : tasks.keys() ){ //Iterates over the hash table keys
            Task currentTask = tasks.get(currentKey); //Gets the current task
            if( !currentTask.getDelivered() )
                ans.addAtEnd(currentTask); //Adds the current task to the answer if it wasn't delivered
        }
        if( ans.isEmpty() )
            throw new NoSuchElementException("All tasks have been delivered");
        return ans;
    }

    /**
     * @param pTaskName name of the task
     * @return Task with name pTaskName
     * @throws NoSuchElementException if there is no task with name pTaskName
     * @throws IllegalArgumentException if the given name is not valid
     */
    @Override
    public Task getTask(String pTaskName) throws NoSuchElementException, IllegalArgumentException {
        try{
            if( !pTaskName.equals("") ) {
                Task ans = tasks.get(pTaskName.hashCode());
                if (ans != null) {
                    return ans;
                } else {
                    throw new NoSuchElementException("Task not found");
                }
            }
            else
                throw new IllegalArgumentException("Task name not valid");
        }catch (IllegalArgumentException e){
            throw new IllegalArgumentException("The given name is not valid");
        }
    }

    /**
     * Adds a new task to the subject. It gets marked as non-delivered and non-graded by default
     * @param pTask new task to be added
     */
    @Override
    public void addTask(Task pTask) {
        tasks.put(pTask.hashCode(), pTask);
    }

    /**
     * Deletes task with name pTaskName from the task list of the subject
     * @param pTaskName name of the task to be deleted
     * @throws NoSuchElementException if there is no task with name pTaskName
     * @throws IllegalArgumentException if the given name is not valid
     */
    @Override
    public void deleteTask(String pTaskName) throws NoSuchElementException, IllegalArgumentException {
        try {
            if (!pTaskName.equals(""))
                //TODO Check HashTable.delete(). Think about throwing an exception if element to delete is not found.
                if( containsTask(pTaskName) )
                    tasks.delete(pTaskName.hashCode());
                else
                    throw new NoSuchElementException("Task not found");
            else
                throw new IllegalArgumentException("Task name not valid");
        }catch (IllegalArgumentException e){
            throw new IllegalArgumentException("Trying to delete a null task");
        }
    }

    /**
     * Marks the task with name pTaskName to done
     *
     * @param pTaskName name of the task
     * @throws NoSuchElementException   if there is no task with name pTaskName
     * @throws IllegalArgumentException if the given name is not valid
     */
    @Override
    public void markAsDone(String pTaskName) throws NoSuchElementException, IllegalArgumentException {
        try{
            setDone(pTaskName,true);
        }catch (IllegalArgumentException e){
            throw new IllegalArgumentException(e.getMessage());
        }catch (NoSuchElementException e){
            throw new NoSuchElementException(e.getMessage());
        }
    }

    /**
     * Changes the done status of the task with name pTaskName to pDone
     *
     * @param pTaskName name of the task
     * @param pDone     new done status
     * @throws NoSuchElementException   if there is no task with name pTaskName
     * @throws IllegalArgumentException if the given name is not valid
     */
    @Override
    public void setDone(String pTaskName, boolean pDone) throws NoSuchElementException, IllegalArgumentException {
        try{
            getTask(pTaskName).setDone(pDone);
        }catch (IllegalArgumentException e){
            throw new IllegalArgumentException(e.getMessage());
        }catch (NoSuchElementException e){
            throw new NoSuchElementException(e.getMessage());
        }
    }

    /**
     * Marks the task with name pTaskName to delivered
     * @param pTaskName name of the task
     * @throws NoSuchElementException if there is no task with name pTaskName
     * @throws IllegalArgumentException if the given name is not valid
     */
    @Override
    public void markAsDelivered(String pTaskName) throws NoSuchElementException, IllegalArgumentException {
        try{
            setDelivered(pTaskName,true);
        }catch (IllegalArgumentException e){
            throw new IllegalArgumentException(e.getMessage());
        }catch (NoSuchElementException e){
            throw new NoSuchElementException(e.getMessage());
        }
    }

    /**
     * Changes the delivered status of task with name pTaskName to pDelivered
     * @param pTaskName name of the task
     * @param pDelivered new delivered status
     * @throws NoSuchElementException if there is no task with name pTaskName
     * @throws IllegalArgumentException if the given name is not valid
     */
    @Override
    public void setDelivered(String pTaskName, boolean pDelivered) throws NoSuchElementException, IllegalArgumentException {
        try{
            getTask(pTaskName).setDelivered(pDelivered);
        }catch (IllegalArgumentException e){
            throw new IllegalArgumentException(e.getMessage());
        }catch (NoSuchElementException e){
            throw new NoSuchElementException(e.getMessage());
        }
    }

    /**
     * Marks the task with name pTaskName to graded
     * @param pTaskName name of the task
     * @throws NoSuchElementException if there is no task with name pTaskName
     * @throws IllegalArgumentException if the given name is not valid
     */
    @Override
    public void markAsGraded(String pTaskName) throws NoSuchElementException, IllegalArgumentException {
        try{
            setGraded(pTaskName,true);
        }catch (IllegalArgumentException e){
            throw new IllegalArgumentException(e.getMessage());
        }catch (NoSuchElementException e){
            throw new NoSuchElementException(e.getMessage());
        }
    }
    /**
     * Changes the graded status of the task with name pTaskName to pDelivered
     * @param pTaskName name of the task
     * @param pGraded new GradedStatus
     * @throws NoSuchElementException if there is no task with name pTaskName
     * @throws IllegalArgumentException if the given name is not valid
     */
    @Override
    public void setGraded(String pTaskName, boolean pGraded) throws NoSuchElementException, IllegalArgumentException {
        try{
            getTask(pTaskName).setGraded(pGraded);
        }catch (IllegalArgumentException e){
            throw new IllegalArgumentException(e.getMessage());
        }catch (NoSuchElementException e){
            throw new NoSuchElementException(e.getMessage());
        }
    }

    /**
     * Changes the grade of the task with name pTaskName to pGrade
     *
     * @param pTaskName name of the task
     * @param pGrade    new grade
     * @throws NoSuchElementException   if there is no task with name pTaskName
     * @throws IllegalArgumentException if the given name or grade are not valid
     */
    @Override
    public void setGrade(String pTaskName, double pGrade) throws NoSuchElementException, IllegalArgumentException {
        try{
            getTask(pTaskName).setGrade(pGrade);
        }catch (IllegalArgumentException | AssertionError e){
            throw new IllegalArgumentException(e.getMessage());
        }catch (NoSuchElementException e){
            throw new NoSuchElementException(e.getMessage());
        }
    }

    /**
     * @return percentage of tasks of the subject that have been graded
     */
    @Override
    public double getGradedTasksPercentage() {
        double ans = 0;
        //Adds the percentage of all graded tasks to the answer
        try {
            for (Task currentTask : getGradedTasks()) {
                ans += currentTask.getPercentage();
            }
            return ans;
        }catch (NoSuchElementException ignored){}
        return ans;
    }

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
        try {
            Iterable<Task> gradedTasks = getGradedTasks();
            if( gradedPercentage >= 100.0 ){
                for( Task currentTask : gradedTasks )
                    ans += (currentTask.getGrade() * currentTask.getPercentage()) / 100.0;
            }
            else{
                for( Task currentTask : gradedTasks )
                    ans += (currentTask.getGrade() * currentTask.getPercentage()) / gradedPercentage;
            }
        }catch (NoSuchElementException ignored){
            return ans;
        }
        return ans;
    }

    /**
     * Verifies the subject fields
     * @throws AssertionError if any field has a non-valid value
     */
    private void assertSubject() throws AssertionError{
        if (name == null || name.equals("")) throw new AssertionError("Name not valid");
        if (!(credits > 0)) throw new AssertionError("Credits must be a positive value");
        if (!(totalHours > 0)) throw new AssertionError("Total hours must be a positive value");
        if (!(classHours > 0)) throw new AssertionError("Class hours must be a positive value");
        if (!(extraHours > 0)) throw new AssertionError("Extra hours must be a positive value");
        if (!(studiedHoursDay >= 0)) throw new AssertionError("Studied hours in day cannot be a negative value");
        if (!(studiedHoursWeek >= 0)) throw new AssertionError("Studied hours in week cannot be a negative value");
        if (!(studiedHoursSemester >= 0)) throw new AssertionError("Studied hours in semester cannot be a negative value");
        if (tasks == null) throw new AssertionError("Tasks cannot be null");
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Subject subject = (Subject) o;

        return name.equals(subject.name);
    }

    @Override
    public int hashCode() {
        return name.hashCode();
    }
}
