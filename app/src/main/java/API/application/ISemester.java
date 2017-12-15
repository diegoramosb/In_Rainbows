package api.application;

import org.joda.time.DateTime;
import org.joda.time.Weeks;

import model.application.Subject;

/**
 * Created by diego on 13/12/2017.
 * Interface for Semesters
 */
public interface ISemester {

    /**
     * @return start date of the semester
     */
    DateTime getStart();

    /**
     * Sets the start date of the semester to pStartDate
     * @param pStartDate String format of the semester start date
     */
    void setStart(String pStartDate);

    /**
     * @return end date of the semester
     */
    DateTime getEnd();

    /**
     * Sets the end date of the semester to pEndDate
     * @param pEndDate String format of the semester end date
     */
    void setEnd( String pEndDate );

    /**
     * @return current dateTime
     */
    DateTime getCurrentDateTime();

    /**
     * @return current week
     */
    Weeks getCurrentWeek();

    /**
     * @return number of weeks of the semester
     */
    int getWeeks();

    /**
     * @return number of the current week
     */
    int getCurrentWeekNumber();

    /**
     * @return semester current GPA
     */
    double getCurrentGPA();

    /**
     * Sets the current semester GPA to pGPA
     * @param pGPA GPA to be set
     */
    void setGpa( double pGPA );

    /**
     * @return number of credits of the semester
     */
    double getCredits();

    /**
     * Sets the number of credits of the semester to pCredits
     * @param pCredits credits to be set
     */
    void setCredits( double pCredits );

    /**
     * @return an iterable with all the subjects of the semester
     */
    Iterable<Subject> getSubjects();

    /**
     * @param pSubjectName name of the subject
     * @return subject with name pSubjectName
     */
    Subject getSubject( String pSubjectName );

    /**
     * Adds the subject to the semester and increases its credits counter
     * @param pSubject Subject to be added
     */
    void addSubject( Subject pSubject );

    /**
     * Removes the subject from the semester and decreases its credits counter
     * @param pSubject Subject to be deleted
     */
    void removeSubject( Subject pSubject );
}
