package model.application;

import org.joda.time.DateTime;
import org.joda.time.Weeks;

import java.util.Calendar;

import api.application.ISemester;

/**
 * Created by diego on 1/08/2017.
 */

public class Semester implements ISemester{

    private Calendar startDate;

    private Calendar endDate;

    private Calendar currentDate;

    private int currentWeek;

    private int endWeek;

    private int startWeek;

    private double gpa;

    private double credits;

    public Semester(String pName, int pAnioInicio, int pMesInicio, int pDiaInicio, int pMesFin, int pDiaFin, int pSemanaActual){

        currentDate = Calendar.getInstance();
        currentDate.setFirstDayOfWeek(Calendar.MONDAY);
        actualizarSemana();

        startDate.set(pAnioInicio + 1900, pMesInicio, pDiaInicio);
        startDate.setFirstDayOfWeek(Calendar.MONDAY);
        startWeek = startDate.getWeekYear();


        endDate.set( pAnioInicio + 1900, pMesInicio, pDiaInicio);
        endDate.setFirstDayOfWeek(Calendar.MONDAY);
        endWeek = endDate.getWeekYear();

//        while( actual != null ){
//            credits += ((Subject) actual).darCreditos();
//            actual = actual.darSiguiente();
//        }

    }

    public Calendar darFechaActual(){
        return currentDate;
    }

    public Calendar darFechaIncio(){
        return startDate;
    }

    public Calendar darFechaFin(){
        return endDate;
    }

    public int darSemanaActual(){
        return currentWeek;
    }

    public void actualizarSemana(){
        currentWeek = (currentDate.getWeekYear()) - startWeek + 1;
    }

    public void calcularPromedioSemestre(){
//        Nodo actual = darPrimerHijo();
//        double sumaNotas = 0;
//        while( actual != null ){
//            sumaNotas += ((Subject) actual ).calcularNota();
//            actual = actual.darSiguiente();
//        }
//        gpa = sumaNotas / credits;
    }

    public void VerificarInvariante(){
        assert( startDate.before(endDate) ) : "La fecha de inicio debe ser anterior a la fecha de fin.";
        assert( startWeek < endWeek) : "La semana de incio debe ser anterior a la semana de fin.";
    }

    /**
     * @return start date of the semester
     */
    @Override
    public DateTime getStart() {
        return null;
    }

    /**
     * Sets the start date of the semester to pStartDate
     *
     * @param pStartDate String format of the semester start date
     */
    @Override
    public void setStart(String pStartDate) {

    }

    /**
     * @return end date of the semester
     */
    @Override
    public DateTime getEnd() {
        return null;
    }

    /**
     * Sets the end date of the semester to pEndDate
     *
     * @param pEndDate String format of the semester end date
     */
    @Override
    public void setEnd(String pEndDate) {

    }

    /**
     * @return current dateTime
     */
    @Override
    public DateTime getCurrentDateTime() {
        return null;
    }

    /**
     * @return current week
     */
    @Override
    public Weeks getCurrentWeek() {
        return null;
    }

    /**
     * @return number of weeks of the semester
     */
    @Override
    public int getWeeks() {
        return 0;
    }

    /**
     * @return number of the current week
     */
    @Override
    public int getCurrentWeekNumber() {
        return 0;
    }

    /**
     * @return semester current GPA
     */
    @Override
    public double getCurrentGPA() {
        return 0;
    }

    /**
     * Sets the current semester GPA to pGPA
     *
     * @param pGPA GPA to be set
     */
    @Override
    public void setGpa(double pGPA) {

    }

    /**
     * @return number of credits of the semester
     */
    @Override
    public double getCredits() {
        return 0;
    }

    /**
     * Sets the number of credits of the semester to pCredits
     *
     * @param pCredits credits to be set
     */
    @Override
    public void setCredits(double pCredits) {

    }

    /**
     * @return an iterable with all the subjects of the semester
     */
    @Override
    public Iterable<Subject> getSubjects() {
        return null;
    }

    /**
     * @param pSubjectName name of the subject
     * @return subject with name pSubjectName
     */
    @Override
    public Subject getSubject(String pSubjectName) {
        return null;
    }

    /**
     * Adds the subject to the semester and increases its credits counter
     *
     * @param pSubject Subject to be added
     */
    @Override
    public void addSubject(Subject pSubject) {

    }

    /**
     * Removes the subject from the semester and decreases its credits counter
     *
     * @param pSubject Subject to be deleted
     */
    @Override
    public void removeSubject(Subject pSubject) {

    }
}
