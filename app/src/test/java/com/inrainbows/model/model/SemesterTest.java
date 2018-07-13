package com.inrainbows.model.model;

import com.inrainbows.mvp.model.Semester;
import com.inrainbows.mvp.model.Subject;
import com.inrainbows.mvp.model.SubjectTask;

import org.junit.Before;
import org.junit.Test;

import java.util.NoSuchElementException;

import static org.junit.Assert.*;

/**
 * @author diego on 11/01/2018.
 */
public class SemesterTest {

    private Semester semester;

    private Subject subject1;

    private Subject subject2;

    @Before
    public void setUp() {
        semester = new Semester.SemesterBuilder(1L,"a",2018, 1, 22, 2018, 5, 12).build();
    }

    @Before
    public void setUp2(){
        setUp();

        subject1 = new Subject.SubjectBuilder(1L,"a", 3, 4).build();
        SubjectTask task11 = new SubjectTask.TaskBuilder(1L,"a", 20)
                .setTag("")
                .setGrade(5.0)
                .setDelivered(true)
                .setDone(true)
                .setGraded(true)
                .build();
        SubjectTask task12 = new SubjectTask.TaskBuilder(1L,"b", 20)
                .setTag("")
                .setGrade(5.0)
                .setDelivered(true)
                .setDone(true)
                .setGraded(true)
                .build();
        SubjectTask task13 = new SubjectTask.TaskBuilder(1L,"c", 60)
                .setTag("")
                .setGrade(5.0)
                .setDelivered(true)
                .setDone(true)
                .setGraded(true)
                .build();

        subject1.addTask(task11);
        subject1.addTask(task12);
        subject1.addTask(task13);
        semester.addSubject(subject1);


        subject2 = new Subject.SubjectBuilder(1L,"a", 1, 1).build();
        SubjectTask task21 = new SubjectTask.TaskBuilder(1L,"a", 20)
                .setTag("")
                .setGrade(4)
                .setDone(true)
                .setDelivered(true)
                .setGraded(true)
                .build();
        subject2.addTask(task21);
        semester.addSubject(subject2);
    }

    @Before
    public void setUp3(){
        setUp();

        subject1 = new Subject.SubjectBuilder(1L,"a", 3, 4).build();
        SubjectTask task11 = new SubjectTask.TaskBuilder(1L,"a", 20)
                .setTag("")
                .setGrade(5.0)
                .setDelivered(true)
                .setDone(true)
                .setGraded(true)
                .build();
        SubjectTask task12 = new SubjectTask.TaskBuilder(1L,"b", 20)
                .setTag("")
                .setGrade(5.0)
                .setDelivered(true)
                .setDone(true)
                .setGraded(true)
                .build();
        SubjectTask task13 = new SubjectTask.TaskBuilder(1L,"c", 60)
                .setTag("")
                .setGrade(5.0)
                .setDelivered(true)
                .setDone(true)
                .setGraded(true)
                .build();
        subject1.addTask(task11);
        subject1.addTask(task12);
        subject1.addTask(task13);
        semester.addSubject(subject1);


        subject2 = new Subject.SubjectBuilder(1L,"b", 1, 1).build();
        SubjectTask task21 = new SubjectTask.TaskBuilder(1L,"a", 20)
                .setGrade(4)
                .setDone(true)
                .setDelivered(true)
                .setGraded(true)
                .build();
        subject2.addTask(task21);
        semester.addSubject(subject2);
    }

    @Test
    public void semester(){
        try{
            semester = new Semester.SemesterBuilder(1L,"a", 2018, 2, 31, 2018, 12, 20).build();
            fail();
        }catch (IllegalArgumentException e){}
        try{
            semester = new Semester.SemesterBuilder(1L,"a", 2018, 1, 22, 2018, 5, 12).build();
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
            semester.setStartDate(2018, 6, 20);
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
            semester.setEndDate(2018, 1, 5);
            fail();
        }catch (AssertionError e){}
        try{
            semester.setEndDate(2018, 6, 22);
            assertEquals(2018, semester.getEndDate().getYear());
            assertEquals(6, semester.getEndDate().getMonthOfYear());
            assertEquals(22, semester.getEndDate().getDayOfMonth());
        }catch (IllegalArgumentException e){
            fail();
        }
    }

    @Test
    public void getCurrentGPA() {
        setUp3();
        assertEquals(4.75, semester.getCurrentGPA(), 0.001);
    }

    @Test
    public void getCredits() {
        setUp3();
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
            assertTrue(semester.containsSubject(subject1));
            semester.setSubjectName(subject1, "b");
            assertTrue(semester.containsSubject(subject2));
        }catch (NoSuchElementException e){
            fail();
        }
    }

    @Test
    public void getSubjects() {
        setUp2();
        for( Subject currentSubject : semester.getSubjects() ){
            assertEquals("a", currentSubject.getName());
        }
    }

    @Test
    public void getSubjectAmount(){
        setUp2();
        assertEquals(2, semester.getSubjectAmount());
    }

    @Test
    public void addSubject() {
        setUp2();
        assertEquals(2, semester.getSubjectAmount());
        Subject subject = new Subject.SubjectBuilder(1L,"b", 3 ,3).build();
        semester.addSubject(subject);
        assertTrue(semester.containsSubject(subject));
    }

    @Test
    public void deleteSubject() {
        setUp2();
        try {
            semester.deleteSubject(subject1);
            assertFalse(semester.containsSubject(subject1));
        }catch (NoSuchElementException e){
            fail();
        }
    }

}