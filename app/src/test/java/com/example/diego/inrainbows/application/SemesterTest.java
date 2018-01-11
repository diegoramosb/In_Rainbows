package com.example.diego.inrainbows.application;

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
            semester.setStartDate(2018, 1, 22);
        }catch (IllegalArgumentException e){
            fail();
        }
    }

    @Test
    public void setEndDate() {
    }

    @Test
    public void getCurrentDateTime() {
    }

    @Test
    public void getCurrentWeek() {
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