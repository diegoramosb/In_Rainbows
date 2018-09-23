package com.inrainbows.model.model;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * @author diego on 20/12/2017.
 */
public class TaskTest {

    private SubjectTask task;

    @Before
    public void setUp() {
        task = new SubjectTask.TaskBuilder(1L,"a", 100).build();
    }

    @Test
    public void taskTest(){
        try{
            task = new SubjectTask.TaskBuilder(1L,"a", 100).build();
            task = new SubjectTask.TaskBuilder(1L,"a", 100).setTag("a").build();
            task = new SubjectTask.TaskBuilder(1L,"a",100)
                    .setTag("a")
                    .setGrade(5)
                    .setDone(true)
                    .setDelivered(true)
                    .setGraded(true)
                    .build();
        }catch (AssertionError error){
            fail();
        }
    }

    @Test
    public void toStringTest() {
        setUp();
        assertEquals("a, null, not done yet, not delivered yet, 0.0, 100.0%", task.toString());
        task.setTag("a");
        assertEquals("a, a, not done yet, not delivered yet, 0.0, 100.0%", task.toString());
        task.setDone(true);
        assertEquals("a, a, done, not delivered yet, 0.0, 100.0%", task.toString());
        task.setDelivered(true);
        assertEquals("a, a, done, delivered, 0.0, 100.0%", task.toString());
        task.setGrade(5.0);
        task.setGraded(true);
        assertEquals("a, a, done, delivered, 5.0, 100.0%", task.toString());
    }

    @Test
    public void setName() {
        setUp();
        assertEquals("a",task.getName());
        try {
            task.setName("");
            fail();
        }catch (AssertionError error){}
    }

    @Test
    public void setGrade() {
        setUp();
        task.setGrade(5.0);
        task.setDone(true);
        task.setDelivered(true);
        task.setGraded(true);
        assertEquals(5.0, task.getGrade(), 0.0);
        setUp();
        try{
            task.setGraded(true);
            fail();
        }catch( AssertionError error ){}
    }

    @Test
    public void getDone() {
        setUp();
        task.setDone(true);
        assertTrue(task.isDone());
    }

    @Test
    public void setTag() {
        setUp();
        task.setTag("a");
        assertEquals("a", task.getTag());
        try {
            task.setTag(null);
            fail();
        }catch( AssertionError error ){}
    }

    @Test
    public void setPercentage() {
        setUp();
        task.setPercentage(100);
        assertEquals(100, task.getPercentage(), 0.0);
        try{
            task.setPercentage(0);
            fail();
        }catch( AssertionError error ){}
    }

    @Test
    public void getDelivered() {
        setUp();
        try {
            task.setDone(true);
            task.setDelivered(true);
            assertTrue(task.isDelivered());
        }catch (AssertionError error){
            fail();
        }
        setUp();
        try{
            task.setDelivered(true);
            fail();
        }catch (AssertionError error){}
    }

}