package com.example.diego.inrainbows.application;

import org.joda.time.DateTime;
import org.junit.Before;
import org.junit.Test;

import model.application.Semester;

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
    }

    @Test
    public void getCredits() {
    }

    @Test
    public void containsSubject() {
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