package com.inrainbows.mvp.model;

import com.inrainbows.persistence.entities.SemesterEntity;
import com.inrainbows.persistence.entities.SubjectEntity;

import org.joda.time.DateTime;
import org.joda.time.DateTimeZone;
import org.joda.time.IllegalFieldValueException;
import org.joda.time.MutableDateTime;
import org.joda.time.Weeks;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;


/**
 * @author diego on 1/08/2017.
 */
public class Semester {

    private long id;

    private String semesterName;

    private DateTime startDate;

    private DateTime endDate;

    private boolean currentSemester;

    private List<Subject> subjects;

    private Semester(SemesterBuilder builder) {
        this.id = builder.id;
        this.semesterName = builder.semesterName;
        this.startDate = builder.startDate;
        this.endDate = builder.endDate;

        this.subjects = builder.getSubjects();
    }

    public Semester(SemesterEntity entity){
        if(entity != null) {
            this.id = entity.getId();
            this.semesterName = entity.getSemesterName();
            this.startDate = entity.getStartDate();
            this.endDate = entity.getEndDate();
            this.currentSemester = entity.isCurrentSemester();
        }
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
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

    public void setStartDate(DateTime startDate) {
        this.startDate = startDate;
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

    public void setEndDate(DateTime endDate) {
        this.endDate = endDate;
    }

    public boolean isCurrentSemester() {
        return currentSemester;
    }

    public void setCurrentSemester(boolean currentSemester) {
        this.currentSemester = currentSemester;
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

    public void setSubjects(List<Subject> subjects) {
        this.subjects = subjects;
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

    public SemesterEntity toEntity(){
        return new SemesterEntity(id, semesterName, startDate, endDate, isCurrentSemester());
    }

    public List<SubjectEntity> subjectsToEntity(){
        ArrayList<SubjectEntity> ans = new ArrayList<>();
        for(Subject subject : subjects){
            ans.add(new SubjectEntity.SubjectEntityBuilder(subject.getId(), subject.getName(),
                    subject.getCredits(), subject.getClassHours(), id).build());
        }
        return ans;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("ID: ")
                .append(id)
                .append("\n" + semesterName)
                .append("\nStart: ")
                .append(startDate.getYear()).append("-")
                .append(startDate.getMonthOfYear()).append("-")
                .append(startDate.getDayOfMonth())
                .append("\nEnd: ")
                .append(endDate.getYear()).append("-")
                .append(endDate.getMonthOfYear()).append("-")
                .append(endDate.getDayOfMonth());

        return sb.toString();
    }

    public static class SemesterBuilder {
        private long id;

        private String semesterName;

        private DateTime startDate;

        private DateTime endDate;

        private List<Subject> subjects;

        private boolean currentSemester;

        public SemesterBuilder(long id, String semesterName, DateTime startDate, DateTime endDate, boolean currentSemester) {
            this.id = id;
            this.semesterName = semesterName;
            this.startDate = startDate;
            this.endDate = endDate;
            this.currentSemester = currentSemester;
        }

        public SemesterBuilder(long id, String semesterName, int startYear, int startMonth,
                               int startDay, int endYear, int endMonth, int endDay, boolean currentSemester) {
            this.id = id;
            this.semesterName = semesterName;
            this.currentSemester = currentSemester;
            //Joda-Time verifies that dates are valid. When it throws an IllegalFieldValueException the constructor throws an IllegalArgumentException.
            try {
                startDate = new DateTime(startYear, startMonth, startDay,
                        0, 0, 0, 0, DateTimeZone.forID("America/Bogota"));
            }
            catch (IllegalFieldValueException e){
                throw new IllegalArgumentException("Start date not valid.");
            }
            try {
                endDate = new DateTime(endYear, endMonth, endDay,
                        0, 0, 0, 0, DateTimeZone.forID("America/Bogota"));
            }
            catch (IllegalFieldValueException e){
                throw new IllegalArgumentException("End date not valid.");
            }

            subjects = new ArrayList<>();
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
