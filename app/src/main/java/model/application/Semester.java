package model.application;

import org.joda.time.DateTime;
import org.joda.time.DateTimeConstants;
import org.joda.time.DateTimeZone;
import org.joda.time.IllegalFieldValueException;
import org.joda.time.MutableDateTime;
import org.joda.time.Period;
import org.joda.time.Weeks;

import java.util.NoSuchElementException;

import api.application.ISemester;
import model.data_structures.Bag;
import model.data_structures.LinearProbingHash;

/**
 * @author diego on 1/08/2017.
 */

public class Semester implements ISemester{

    private DateTime startDate;

    private DateTime endDate;

    private MutableDateTime currentDate;

    private Period currentWeek;

//    private static Weeks weeks;

    private LinearProbingHash<Integer, Subject> subjects;

    /**
     * Creates a new Semester object
     * @param pStartYear Start year
     * @param pStartMonth Start month of year (from 1 to 12)
     * @param pStartDay Start day of month (from 1 to 31)
     * @param pEndYear End year
     * @param pEndMonth End month of year (from 1 to 12)
     * @param pEndDay End day of month (from 1 to 31)
     * @throws IllegalArgumentException If dates that are not valid are given as parameters
     */
    public Semester(int pStartYear, int pStartMonth, int pStartDay, int pEndYear, int pEndMonth, int pEndDay) throws IllegalArgumentException {

        currentDate = new MutableDateTime(DateTimeZone.forID("America/Bogota")); //Immutable timezone temporarily
        //Joda-Time verifies that dates are valid. When it throws an IllegalFieldValueException the constructor throws an IllegalArgumentException.
        try {
            startDate = new DateTime(pStartYear, pStartMonth, pStartDay, 0, 0, 0, 0, DateTimeZone.forID("America/Bogota"));
        }
        catch (IllegalFieldValueException e){
            throw new IllegalArgumentException("Start date not valid.");
        }
        try {
            endDate = new DateTime(pEndYear, pEndMonth, pEndDay, 0, 0, 0, 0, DateTimeZone.forID("America/Bogota"));
        }
        catch (IllegalFieldValueException e){
            throw new IllegalArgumentException("End date not valid.");
        }

//        weeks = new Weeks();

        setCurrentWeek();
        subjects = new LinearProbingHash<>(10); //Size set as 10 by default

        assertSemester();
    }

    private void assertSemester(){
        if (!startDate.isBefore(endDate.toInstant()))
            throw new AssertionError("Start date must be before end date");
    }

    /**
     * @return start date of the semester
     */
    @Override
    public DateTime getStartDate() {
        return startDate;
    }

    /**
     * Sets the start date of the semester to pStartDate
     *
     * @param pStartYear  Start year
     * @param pStartMonth Start month
     * @param pStartDay   Start day
     */
    @Override
    public void setStartDate(int pStartYear, int pStartMonth, int pStartDay) throws IllegalArgumentException{
        try {
              MutableDateTime startDateCopy = startDate.toMutableDateTime();
              startDateCopy.setDate(pStartYear, pStartMonth, pStartDay);
              startDate = startDateCopy.toDateTime();
        }catch (IllegalFieldValueException e){
            throw new IllegalArgumentException("Date not valid");
        }
        assertSemester();
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
    public void setEndDate(int pEndYear, int pEndMonth, int pEndDay) throws IllegalArgumentException{
        try {
            MutableDateTime endDateCopy = endDate.toMutableDateTime();
            endDateCopy.setDate(pEndYear, pEndMonth, pEndDay);
            endDate = endDateCopy.toDateTime();
        }catch (IllegalArgumentException e){
            throw  new IllegalArgumentException("Date not valid");
        }
        assertSemester();
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
     * Sets the current week according to the current dateTime.
     */
    @Override
    public void setCurrentWeek() {
        MutableDateTime startOfCurrentWeek = currentDate.toMutableDateTime(); //Creates a mutable copy of currentDate
        startOfCurrentWeek.addDays( 1 - startOfCurrentWeek.getDayOfWeek() ); //Finds the first day of the current week by subtracting 1 minus the current day of week
        startOfCurrentWeek.setMillisOfDay(0); //Sets the hour of the copy to 00:00:00

        MutableDateTime endOfCurrentWeek = currentDate.toMutableDateTime();
        endOfCurrentWeek.addDays(7 - endOfCurrentWeek.getDayOfWeek()); //Finds the last day of the week by adding 7 minus the current day of the week
        endOfCurrentWeek.setMillisOfDay(DateTimeConstants.MILLIS_PER_DAY - 1); //Sets the hour of the copy to 23:59:59

        currentWeek = new Period(startOfCurrentWeek, endOfCurrentWeek); //Creates a period of 6D23H59M59.999S (a week)
    }

    /**
     * @return number of weeks of the semester
     */
    @Override
    public Weeks getWeeks() {
//        return weeks;
        return null;
    }

    /**
     * @return number of the current week
     */
    @Override
    public int getCurrentWeekNumber() {
//        return weeks.getWeeks();
        return 0;
    }

    /**
     * @return semester current GPA
     */
    @Override
    public double getCurrentGPA() {

        double ans = 0;
        int credits = 0;
        try{
            double sum = 0;
            for( Integer currentKey : subjects.keys() ){
                Subject currentSubject = subjects.get(currentKey);
                sum += (currentSubject.getCurrentGrade()*currentSubject.getCredits());
                credits += currentSubject.getCredits();
            }
            ans = sum / credits;
        }catch (NoSuchElementException e){
            return ans;
        }
        return ans;
    }

//    /**
//     * Sets the current semester GPA to pGPA
//     * @param pGPA GPA to be set
//     */
//    @Override
//    public void setGpa(double pGPA) {
//        gpa = pGPA;
//    }

    /**
     * @return number of credits of the semester
     */
    @Override
    public double getCredits() {
        int credits = 0;
        try{
            for( Integer currentKey : subjects.keys() ){
                Subject currentSubject = subjects.get(currentKey);
                credits += currentSubject.getCredits();
            }
        }catch (NoSuchElementException e){
            return credits;
        }
        return credits;
    }

    /**
     * @param pSubjectName subject name
     * @return true if the semester contains the subject with the given name
     * @throws IllegalArgumentException if the given name is not valid
     */
    @Override
    public boolean containsSubject(String pSubjectName) throws IllegalArgumentException {
        try {
            if( !pSubjectName.equals("") )
                return subjects.contains(pSubjectName.hashCode());
            else
                throw new IllegalArgumentException("Task name not valid");
        }catch (IllegalArgumentException e){
            throw new IllegalArgumentException("Task name not valid");
        }
    }

//    /**
//     * Sets the number of credits of the semester to pCredits
//     *
//     * @param pCredits credits to be set
//     */
//    @Override
//    public void setCredits(double pCredits) {
//        credits = pCredits;
//    }

    /**
     * @return an iterable with all the subjects of the semester
     * @throws NoSuchElementException if there are no subjects
     */
    @Override
    public Iterable<Subject> getSubjects() throws NoSuchElementException {
        if( subjects.isEmpty() )
            throw new NoSuchElementException("The semester has no subjects");
        Bag<Subject> ans = new Bag<>();
        for (Integer currentKey : subjects.keys()) { //Iterates over the hash table keys
            Subject currentSubject = subjects.get(currentKey); //Gets the current subject
            ans.addAtEnd(currentSubject); //Adds the current subject to the answer
        }
        return ans;
    }

    public int getSubjectAmount(){
        return subjects.size();
    }

    /**
     * @param pSubjectName name of the subject
     * @return subject with name pSubjectName
     * @throws IllegalArgumentException if the name is not valid
     * @throws NoSuchElementException if there is no subject with the given name
     */
    @Override
    public Subject getSubject(String pSubjectName) {
        try{
            if( !pSubjectName.equals("") ) {
                Subject ans = subjects.get(pSubjectName.hashCode());
                if (ans != null) {
                    return ans;
                } else {
                    throw new NoSuchElementException("Subject not found");
                }
            }
            else
                throw new IllegalArgumentException("Subject name not valid");
        }catch (IllegalArgumentException e){
            throw new IllegalArgumentException("The given name is not valid");
        }
    }

    /**
     * Adds the subject to the semester and increases its credits counter
     *
     * @param pSubject Subject to be added
     */
    @Override
    public void addSubject(Subject pSubject) {
        subjects.put(pSubject.hashCode(), pSubject);
    }

    /**
     * Removes the subject from the semester and decreases its credits counter
     *
     * @param pSubjectName name of the subject to be deleted
     * @throws IllegalArgumentException if the given name is not valid
     * @throws NoSuchElementException   if the subject is not found
     */
    @Override
    public void deleteSubject(String pSubjectName) throws NoSuchElementException, IllegalArgumentException {
        try {
            if (!pSubjectName.equals(""))
                //TODO Check HashTable.delete(). Think about throwing an exception if element to delete is not found.
                if( containsSubject(pSubjectName) )
                    subjects.delete(pSubjectName.hashCode());
                else
                    throw new NoSuchElementException("Subject not found");
            else
                throw new IllegalArgumentException("Subject name not valid");
        }catch (IllegalArgumentException e){
            throw new IllegalArgumentException("Trying to delete a null subject");
        }
    }

    /**
     * Changes the name of a subject
     *
     * @param pCurrentName current subject name
     * @param pNewName     new subject name
     * @throws NoSuchElementException   if the subject is not found
     * @throws IllegalArgumentException if any name is not valid
     */
    @Override
    public void setSubjectName(String pCurrentName, String pNewName) throws NoSuchElementException, IllegalArgumentException {
        if( !pCurrentName.equals("") && !pCurrentName.equals("") ){
            if (subjects.contains(pCurrentName.hashCode())) {
                //Makes a copy of the subject and deletes it
                int oldHash = pCurrentName.hashCode();
                Subject temp = subjects.get(oldHash);
                subjects.delete(oldHash);
                //Changes the the copy name and puts it in the subject with the new hashCode
                temp.setName(pNewName);
                subjects.put(pNewName.hashCode(), temp);
            } else
                throw new NoSuchElementException("Subject not found.");
        }else
            throw new IllegalArgumentException("Name not valid");
    }
}
