package api.application;

import org.joda.time.DateTime;
import org.joda.time.MutableDateTime;
import org.joda.time.Period;
import org.joda.time.Weeks;

import java.util.NoSuchElementException;

import model.application.Subject;

/**
 * Created by diego on 13/12/2017.
 * Interface for Semesters
 */
public interface ISemester {

    /**
     * @return start date of the semester
     */
    DateTime getStartDate();

    /**
     * Sets the start date of the semester to pStartDate
     * @param pStartYear Start year
     * @param pStartMonth Start month
     * @param pStartDay Start day
     */
    void setStartDate(int pStartYear, int pStartMonth, int pStartDay);

    /**
     * @return end date of the semester
     */
    DateTime getEndDate();

    /**
     * Sets the end date of the semester to pEndDate
     * @param pEndYear End year
     * @param pEndMonth End month
     * @param pEndDay End day
     */
    void setEndDate( int pEndYear, int pEndMonth, int pEndDay );

    /**
     * @return current dateTime
     */
    MutableDateTime getCurrentDateTime();

    /**
     * @return current week
     */
    Period getCurrentWeek();

    /**
     * @return number of weeks of the semester
     */
    Weeks getWeeks();

    /**
     * @return number of the current week
     */
    int getCurrentWeekNumber();

    /**
     * @return semester current GPA
     */
    double getCurrentGPA();

//    /**
//     * Sets the current semester GPA to pGPA
//     * @param pGPA GPA to be set
//     */
//    void setGpa( double pGPA );

    /**
     * @return number of credits of the semester
     */
    double getCredits();

//    /**
//     * Sets the number of credits of the semester to pCredits
//     * @param pCredits credits to be set
//     */
//    void setCredits( double pCredits );

    /**
     * @return true if the semester contains the subject with the given name
     * @param pSubjectName subject name
     * @throws IllegalArgumentException if the given name is not valid
     */
    boolean containsSubject(String pSubjectName) throws IllegalArgumentException;

    /**
     * @return an iterable with all the subjects of the semester
     * @throws NoSuchElementException if there are no subjects
     */
    Iterable<Subject> getSubjects() throws NoSuchElementException;

    /**
     * @param pSubjectName name of the subject
     * @return subject with name pSubjectName
     * @throws IllegalArgumentException if the name is not valid
     * @throws NoSuchElementException if there is no subject with the given name
     */
    Subject getSubject( String pSubjectName ) throws IllegalArgumentException, NoSuchElementException;

    /**
     * Adds the subject to the semester and increases its credits counter
     * @param pSubject Subject to be added
     */
    void addSubject( Subject pSubject );

    /**
     * Removes the subject from the semester and decreases its credits counter
     * @param pSubjectName name of the subject to be deleted
     * @throws IllegalArgumentException if the given name is not valid
     * @throws NoSuchElementException if the subject is not found
     */
    void deleteSubject( String pSubjectName ) throws  NoSuchElementException, IllegalArgumentException;

    /**
     * Changes the name of a subject
     * @param pCurrentName current subject name
     * @param pNewName new subject name
     * @throws NoSuchElementException if the subject is not found
     * @throws IllegalArgumentException if any name is not valid
     */
    void setSubjectName( String pCurrentName, String pNewName ) throws NoSuchElementException, IllegalArgumentException;
}
