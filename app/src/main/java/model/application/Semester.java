package model.application;

import org.joda.time.DateTime;
import org.joda.time.DateTimeZone;
import org.joda.time.Interval;
import org.joda.time.MutableDateTime;
import org.joda.time.Period;
import org.joda.time.Weeks;

import api.application.ISemester;

/**
 * @author diego on 1/08/2017.
 */

public class Semester implements ISemester{

    private DateTime startDate;

    private DateTime endDate;

    private MutableDateTime currentDate;

    private Period currentWeek;

    private int endWeek;

    private int startWeek;

    private double gpa;

    private double credits;

    public Semester(int pStartYear, int pStartMonth, int pStartDay, int pEndYear, int pEndMonth, int pEndDay) {

        currentDate = new MutableDateTime(DateTimeZone.forID("America/Bogota")); //Immutable timezone temporarily
        //actualizarSemana();

        startDate = new DateTime(pStartYear, pStartMonth, pStartDay, 0, 0, 0, 0, DateTimeZone.forID("America/Bogota"));

        endDate = new DateTime(pEndYear, pEndMonth, pEndDay, 0, 0, 0, 0, DateTimeZone.forID("America/Bogota"));
    }

//    public Calendar darFechaActual(){
//        return currentDate;
//    }
//
//    public Calendar darFechaIncio(){
//        return startDate;
//    }
//
//    public Calendar darFechaFin(){
//        return endDate;
//    }
//
//    public int darSemanaActual(){
//        return currentWeek;
//    }
//
//    public void actualizarSemana(){
//        currentWeek = (currentDate.getWeekYear()) - startWeek + 1;
//    }

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
//        assert( startDate.before(endDate) ) : "La fecha de inicio debe ser anterior a la fecha de fin.";
        assert( startWeek < endWeek) : "La semana de incio debe ser anterior a la semana de fin.";
    }

    /**
     * @return start date of the semester
     */
    @Override
    public DateTime getStartDate() {
        return null;
    }

    /**
     * Sets the start date of the semester to pStartDate
     *
     * @param pStartYear  Start year
     * @param pStartMonth Start month
     * @param pStartDay   Start day
     */
    @Override
    public void setStartDate(int pStartYear, int pStartMonth, int pStartDay) {
        startDate.year().setCopy(pStartYear);
        startDate.monthOfYear().setCopy(pStartMonth);
        startDate.dayOfMonth().setCopy(pStartDay);
    }

    /**
     * @return end date of the semester
     */
    @Override
    public DateTime getEndDate() {
        return endDate;
    }

    /**
     * Sets the end date of the semester to pEndDate
     *
     * @param pEndYear  End year
     * @param pEndMonth End month
     * @param pEndDay   End day
     */
    @Override
    public void setEndDate(int pEndYear, int pEndMonth, int pEndDay) {
        endDate.year().setCopy(pEndYear);
        endDate.monthOfYear().setCopy(pEndMonth);
        endDate.dayOfMonth().setCopy(pEndDay);
    }

    /**
     * @return current dateTime
     */
    @Override
    public MutableDateTime getCurrentDateTime() {
        return currentDate;
    }

    /**
     * @return current week
     */
    @Override
    public Period getCurrentWeek() {
        return currentWeek;
    }

    /**
     * @return number of weeks of the semester
     */
    @Override
    public Weeks getWeeks() {
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
