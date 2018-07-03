package com.diegoramos.mvp.model;

import org.joda.time.DateTime;
import org.joda.time.DateTimeConstants;
import org.joda.time.DateTimeZone;
import org.joda.time.IllegalFieldValueException;
import org.joda.time.MutableDateTime;
import org.joda.time.Period;
import org.joda.time.Weeks;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;


/**
 * @author diego on 1/08/2017.
 */

public class Semester {

    private String semesterName;

    private DateTime startDate;

    private DateTime endDate;

    private MutableDateTime currentDate;

    private Period currentWeek;

//    private static Weeks weeks;

    private List<Subject> subjects;

    private Semester(SemesterBuilder builder) {
        this.semesterName = builder.semesterName;
        this.startDate = builder.startDate;
        this.endDate = builder.endDate;

        this.currentDate = builder.getCurrentDate();
        this.currentWeek = builder.getCurrentWeek();
        this.subjects = builder.getSubjects();
    }

    public String getSemesterName() {
        return semesterName;
    }

    public void setSemesterName(String semsterNumber) {
        this.semesterName = semsterNumber;
    }

    /**
     * @return start date of the semester
     */
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
    public void setStartDate(int pStartYear, int pStartMonth, int pStartDay) throws IllegalArgumentException{
        try {
              MutableDateTime startDateCopy = startDate.toMutableDateTime();
              startDateCopy.setDate(pStartYear, pStartMonth, pStartDay);
              startDate = startDateCopy.toDateTime();
        }catch (IllegalFieldValueException e){
            throw new IllegalArgumentException("Date not valid");
        }
    }

    /**
     * @return end date of the semester
     */
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

    public void setEndDate(int pEndYear, int pEndMonth, int pEndDay) throws IllegalArgumentException{
        try {
            MutableDateTime endDateCopy = endDate.toMutableDateTime();
            endDateCopy.setDate(pEndYear, pEndMonth, pEndDay);
            endDate = endDateCopy.toDateTime();
        }catch (IllegalArgumentException e){
            throw  new IllegalArgumentException("Date not valid");
        }
    }

    /**
     * @return current dateTime
     */
    public MutableDateTime getCurrentDateTime() {
        return currentDate;
    }

    /**
     * @return current week
     */
    public Period getCurrentWeek() {
        return currentWeek;
    }

    /**
     * @return number of weeks of the semester
     */
    public Weeks getWeeks() {
//        return weeks;
        return null;
    }

    /**
     * @return number of the current week
     */
    public int getCurrentWeekNumber() {
        if( currentDate.isAfter(startDate) ){
            MutableDateTime mutableStartDate = startDate.toMutableDateTime();
            mutableStartDate.addDays(1 - mutableStartDate.getDayOfWeek());
            Long weeksBetween = (currentDate.getMillis() - mutableStartDate.getMillis())/DateTimeConstants.MILLIS_PER_WEEK;
            return weeksBetween.intValue() + 1;
        }else
            //TODO Find a way to make the method return negative numbers if the start date hasn't arrived yet.
            return 0;
    }

    /**
     * @return semester current GPA
     */
    public double getCurrentGPA() {

        double ans = 0;
        int credits = 0;
        try{
            double sum = 0;
            for( Subject currentSubject : subjects ){
                sum += (currentSubject.getCurrentGrade()*currentSubject.getCredits());
                credits += currentSubject.getCredits();
            }
            ans = sum / credits;
        }catch (NoSuchElementException e){
            return ans;
        }
        return ans;
    }

    /**
     * @return number of credits of the semester
     */
    public double getCredits() {
        int credits = 0;
        try{
            for( Subject currentSubject : subjects ){
                credits += currentSubject.getCredits();
            }
        }catch (NoSuchElementException e){
            return credits;
        }
        return credits;
    }

    /**
     * @param subject subject
     * @return true if the semester contains the subject with the given name
     * @throws IllegalArgumentException if the given name is not valid
     */
    public boolean containsSubject(Subject subject) throws IllegalArgumentException {
        try {
            return subjects.contains(subject);
        }catch (IllegalArgumentException e){
            throw new IllegalArgumentException("Subject cannot be null");
        }
    }

    /**
     * @return an iterable with all the subjects of the semester
     * @throws NoSuchElementException if there are no subjects
     */
    public Iterable<Subject> getSubjects() throws NoSuchElementException {
        if(!subjects.isEmpty()){
            return subjects;
        }
        else {
            throw new NoSuchElementException("The semester doesn't have any subjects");
        }
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
    public Subject getSubject(String pSubjectName) {
            if( !pSubjectName.equals("") ) {
                for(Subject subject : subjects ){
                    if(subject.getName().equals(pSubjectName)){
                        return subject;
                    }
                }
                    throw new NoSuchElementException("Subject not found");
            }
            else
                throw new IllegalArgumentException("Subject name not valid");
    }

    /**
     * Adds the subject to the semester and increases its credits counter
     *
     * @param pSubject Subject to be added
     */
    public void addSubject(Subject pSubject) {
        subjects.add(pSubject);
    }

    /**
     * Removes the subject from the semester and decreases its credits counter
     *
     * @param subject the subject to be deleted
     * @throws IllegalArgumentException if the given name is not valid
     * @throws NoSuchElementException   if the subject is not found
     */
    public void deleteSubject(Subject subject) throws NoSuchElementException, IllegalArgumentException {
        try {
            if (!subjects.remove(subject)){
                throw new NoSuchElementException("Subject not found");
            }
        }catch (NullPointerException e){
            throw new IllegalArgumentException("Trying to delete a null subject");
        }
    }

    /**
     * Changes the name of a subject
     *
     * @param subject current subject
     * @param pNewName     new subject name
     * @throws NoSuchElementException   if the subject is not found
     * @throws IllegalArgumentException if any name is not valid
     */
    public void setSubjectName(Subject subject, String pNewName) throws NoSuchElementException, IllegalArgumentException {
        if(pNewName != null && !pNewName.equals("")){
            try{
                deleteSubject(subject);
                subject.setName(pNewName);
                addSubject(subject);
            }
            catch (IllegalArgumentException e){
                throw e;
            }
        }else
            throw new IllegalArgumentException("Name not valid");
    }

    public static class SemesterBuilder {
        private String semesterName;

        private DateTime startDate;

        private DateTime endDate;

        private MutableDateTime currentDate;

        private Period currentWeek;

        private List<Subject> subjects;

        public SemesterBuilder(String semesterName, int startYear, int startMonth, int startDay, int endYear, int endMonth, int endDay) {
            this.semesterName = semesterName;
            currentDate = new MutableDateTime(DateTimeZone.forID("America/Bogota")); //Immutable timezone temporarily
            //Joda-Time verifies that dates are valid. When it throws an IllegalFieldValueException the constructor throws an IllegalArgumentException.
            try {
                startDate = new DateTime(startYear, startMonth, startDay, 0, 0, 0, 0, DateTimeZone.forID("America/Bogota"));
            }
            catch (IllegalFieldValueException e){
                throw new IllegalArgumentException("Start date not valid.");
            }
            try {
                endDate = new DateTime(endYear, endMonth, endDay, 0, 0, 0, 0, DateTimeZone.forID("America/Bogota"));
            }
            catch (IllegalFieldValueException e){
                throw new IllegalArgumentException("End date not valid.");
            }

//        weeks = new Weeks();

            setCurrentWeek();
            subjects = new ArrayList<>();
        }

        public MutableDateTime getCurrentDate() {
            return currentDate;
        }

        public SemesterBuilder setCurrentDate(MutableDateTime currentDate) {
            this.currentDate = currentDate;
            return this;
        }

        public Period getCurrentWeek() {
            return currentWeek;
        }

        public SemesterBuilder setCurrentWeek(Period currentWeek) {
            this.currentWeek = currentWeek;
            return this;
        }

        /**
         * Sets the current week according to the current dateTime.
         */
        private void setCurrentWeek() {
            MutableDateTime startOfCurrentWeek = currentDate.toMutableDateTime(); //Creates a mutable copy of currentDate
            startOfCurrentWeek.addDays( 1 - startOfCurrentWeek.getDayOfWeek() ); //Finds the first day of the current week by subtracting 1 minus the current day of week
            startOfCurrentWeek.setMillisOfDay(0); //Sets the hour of the copy to 00:00:00

            MutableDateTime endOfCurrentWeek = currentDate.toMutableDateTime();
            endOfCurrentWeek.addDays(7 - endOfCurrentWeek.getDayOfWeek()); //Finds the last day of the week by adding 7 minus the current day of the week
            endOfCurrentWeek.setMillisOfDay(DateTimeConstants.MILLIS_PER_DAY - 1); //Sets the hour of the copy to 23:59:59

            currentWeek = new Period(startOfCurrentWeek, endOfCurrentWeek); //Creates a period of 6D23H59M59.999S (a week)
        }

        public List<Subject> getSubjects() {
            return subjects;
        }

        public SemesterBuilder setSubjects(List<Subject> subjects) {
            this.subjects = subjects;
            return this;
        }

        public Semester build(){
            return new Semester(this);
        }
    }
}
