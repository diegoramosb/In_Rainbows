package com.example.diego.inrainbows.application;

import org.joda.time.DateTime;
import org.junit.Before;
import org.junit.Test;

import java.util.NoSuchElementException;

import model.application.Semester;
import model.application.Subject;
import model.application.Task;
import model.data_structures.LinearProbingHash;

import static org.junit.Assert.*;

/**
 * @author diego on 11/01/2018.
 */
public class SemesterTest {

    private Semester semester;

    @Before
    public void setUp() {
        semester = new Semester(2018, 1, 22, 2018, 5, 12);
    }

    public void setUp2(){
        setUp();

        Subject subject1 = new Subject("a", 3, 4);
        Task task11 = new Task("a", "", 20, 5.0, true, true, true);
        Task task12 = new Task("b", "", 20, 5.0, true, true, true);
        Task task13 = new Task("c", "", 60, 5.0, true, true, true);
        subject1.addTask(task11);
        subject1.addTask(task12);
        subject1.addTask(task13);
        semester.addSubject(subject1);


        Subject subject2 = new Subject("a", 1, 1);
        Task task21 = new Task("a", "", 20, 4, true, true, true);
        subject2.addTask(task21);
        semester.addSubject(subject2);
    }

    @Test
    public void semester(){
        try{
            semester = new Semester(2018, 2, 31, 2018, 12, 20);
            fail();
        }catch (IllegalArgumentException e){}
        try{
            semester = new Semester(2018, 1, 22, 2018, 5, 12);
        }catch (IllegalArgumentException e){
            fail();
        }
    }

    @Test
    public void setStartDate() {
        setUp();
        try{
            semester.setStartDate(2018, 2, 31);
            fail();
        }catch (IllegalArgumentException e){}
        try{
            semester.setStartDate(2018, 06, 20);
            fail();
        }catch (AssertionError e){}
        try{
            semester.setStartDate(2018, 1, 22);
            assertEquals(2018, semester.getStartDate().getYear());
            assertEquals(1, semester.getStartDate().getMonthOfYear());
            assertEquals(22, semester.getStartDate().getDayOfMonth());
        }catch (IllegalArgumentException e){
            fail();
        }
    }

    @Test
    public void setEndDate() {
        setUp();
        try{
            semester.setEndDate(2018, 2, 31);
            fail();
        }catch (IllegalArgumentException e){}
        try{
            semester.setEndDate(2018, 01, 05);
            fail();
        }catch (AssertionError e){}
        try{
            semester.setEndDate(2018, 06, 22);
            assertEquals(2018, semester.getEndDate().getYear());
            assertEquals(6, semester.getEndDate().getMonthOfYear());
            assertEquals(22, semester.getEndDate().getDayOfMonth());
        }catch (IllegalArgumentException e){
            fail();
        }
    }

    @Test
    public void getCurrentDateTime() {
        setUp();
        assertEquals(System.currentTimeMillis(), semester.getCurrentDateTime().getMillis());
    }

    @Test
    public void getCurrentWeek() {
        //TODO Finish dates stuff
        try {
            setUp();
            System.out.println(semester.getCurrentWeek());
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    @Test
    public void getWeeks()  {
    }

    @Test
    public void getCurrentWeekNumber() {
    }

    @Test
    public void getCurrentGPA() {
        setUp2();
        assertEquals(4.75, semester.getCurrentGPA(), 0.001);
    }

    @Test
    public void getCredits() {
        setUp2();
        assertEquals(4, semester.getCredits(), 0.0);
    }

    @Test
    public void containsSubject() {
        setUp2();
        try{
            semester.getSubject("b");
            fail();
        }catch (NoSuchElementException e){}
        try{
            assertTrue(semester.containsSubject("a"));
            semester.setSubjectName("a", "b");
            assertTrue(semester.containsSubject("b"));
        }catch (NoSuchElementException e){
            fail();
        }
    }

    @Test
    public void getSubjects() {
    }

    @Test
    public void getSubject() {
    }

    @Test
    public void addSubject() {
    }

    @Test
    public void deleteSubject() {
    }

}