package model.application;

import api.application.ISubject;
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
    public double studiedHoursDay;

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
     * @throws IllegalArgumentException If any argument is invalid
     */
    public Subject(String pName, int pCredits, double pClassHours ) throws IllegalArgumentException {
        if( pName != "" && pName != " " && pName != null) name = pName;
        else throw new IllegalArgumentException("Subject name not valid.");
        if( pCredits > 0.0 ) credits = pCredits;
        else throw new IllegalArgumentException("A subject must have at least 1 credit.");
        if( pClassHours > 0.0 ) classHours = pClassHours;
        else throw new IllegalArgumentException("A subject must have at least 1 weekly hour of class.");
        studiedHoursDay = 0;
        studiedHoursWeek = 0;
        studiedHoursSemester = 0;
        totalHours = pCredits * 3;
        extraHours = totalHours - classHours;
        tasks = new LinearProbingHash<>(10);
    }

    /**
     * Retorna la suma de los porcentajes de las notas entregadas.
     * @return Suma de los porcentajes de las notas entregadas.
     */
    public double darSumaPorcentajesNotasEntregadas(){
//        Nodo actual = primerHijo;
//        double rta = 0;
//
//        while(actual!=null){
//            if( ((Task)actual).darEntregada() == true ) {
//                rta = rta + ((Task) actual).darPorcentajeNotaFinal();
//            }
//            actual = actual.darSiguiente();
//        }
//        return rta;
        return 0.0;
    }

    /**
     * Retorna la nota actual de la materia. El cálculo se hace así: Si la suma de las notas
     * entregadas es 100 se calcula calificacionActual * (porcentaje / 100). Sino, se calcula
     * calificacionActual * (porcentaje / porcentajeNotasEntregadas).
     * @return ITask actual de la materia (solo de las notas entregadas)
     */
    public double calcularNota(){
        double rta = 0;
//
//        if( darSumaPorcentajesNotasEntregadas() >= 100 ){
//            Nodo actual = primerHijo;
//            while( actual != null ) {
//                rta += ((Task) actual).darCalificacion() * (((Task) actual).darPorcentajeNotaFinal()/100.0);
//                actual = actual.darSiguiente();
//            }
//        }
//        else {
//            Nodo actual = primerHijo;
//            while (actual != null) {
//                if(((Task)actual).darEntregada() == true) {
//                    rta += ((Task) actual).darCalificacion() * (((Task) actual).darPorcentajeNotaFinal() / darSumaPorcentajesNotasEntregadas());
//                }
//                actual = actual.darSiguiente();
//            }
//        }
        return rta;
    }

    public String toString(){
        //TODO pensar en un toString para materia.
        return name + ", " + credits + " c, ";
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
    public void increaseStdudiedHoursSemester(double pStudiedHours) {
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
        return null;
    }

    /**
     * @return an iterable containing only the non-graded tasks of the subject
     */
    @Override
    public Iterable<Task> getNonGradedTasks() {
        return null;
    }

    /**
     * @return an iterable containing only the delivered tasks of the subject
     */
    @Override
    public Iterable<Task> getDeliveredTasks() {
        return null;
    }

    /**
     * @return an iterable containing only the non-delivered tasks of the subject
     */
    @Override
    public Iterable<Task> getNonDeliveredTasks() {
        return null;
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

    }

    /**
     * Deletes task with name pTaskName from the task list of the subject
     *
     * @param pTaskName name of the task to be deleted
     */
    @Override
    public void deleteTask(String pTaskName) {

    }

    /**
     * Marks the task with name pTaskName to delivered
     *
     * @param pTaskName name of the task
     */
    @Override
    public void markAsDelivered(String pTaskName) {

    }

    /**
     * Changes the delivered status of task with name pTaskName to pDelivered
     *
     * @param pTaskName  name of the task
     * @param pDelivered new delivered status
     */
    @Override
    public void setDelivered(String pTaskName, boolean pDelivered) {

    }

    /**
     * Marks the task with name pTaskName to graded
     *
     * @param pTaskName name of the task
     */
    @Override
    public void markAsGraded(String pTaskName) {

    }

    /**
     * Changes the graded status of the task with name pTaskName to pDelivered
     *
     * @param pTaskName  name of the task
     * @param pDelivered new GradedStatus
     */
    @Override
    public void setGraded(String pTaskName, boolean pDelivered) {

    }

    /**
     * @return percentage of tasks of the subject that have been graded
     */
    @Override
    public double getGradedTasksPercentage() {
        return 0;
    }

    /**
     * @return current subject grade
     */
    @Override
    public double getCurrentGrade() {
        return 0;
    }
}
