package com.inrainbows.model.model;

import com.inrainbows.mvp.model.Subject;
import com.inrainbows.mvp.model.SubjectTask;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

import static org.junit.Assert.*;

/**
 * @author diego on 20/12/2017.
 */
public class SubjectTest {

    private Subject subject;

    public void setUp(){
        subject = new Subject.SubjectBuilder("a", 3, 3).build();
    }

    @Test
    public void subjectTest(){
        try{
            subject = new Subject.SubjectBuilder("a", 3, 3).build();
            List<SubjectTask> tasks = new ArrayList<>();
            tasks.add(new SubjectTask.TaskBuilder("a", 100).build());
            subject = new Subject.SubjectBuilder("a", 3, 3)
            .setExtraHours(2)
            .setTotalHours(2)
            .setStudiedHoursDay(2)
            .setStudiedHoursSemester(2)
            .setStudiedHoursWeek(2)
            .setTasks(tasks)
            .build();
        }catch (Exception error){
            fail();
        }
    }

    @Test
    public void toStringTest() {
        setUp();

        //assertEquals("a, credits: 3, class hours: ", subject.toString());
    }

    @Test
    public void setName() {
        setUp();
        subject.setName("b");
        assertEquals("b", subject.getName());
    }

    @Test
    public void setCredits() {
        setUp();
        subject.setCredits(1);
        assertEquals(1, subject.getCredits(), 0.0);
    }

    @Test
    public void setClassHours() {
        setUp();
        subject.setClassHours(4.5);
        assertEquals(4.5, subject.getClassHours(), 0.0);
    }

    @Test
    public void getTotalHours() {
        setUp();
        assertEquals(9, subject.getTotalHours(), 0.0);
    }

    @Test
    public void setTotalHours() {
        setUp();
        subject.setTotalHours(10);
        assertEquals(10, subject.getTotalHours(), 0.0);
    }

    @Test
    public void getExtraHours() {
        setUp();
        assertEquals(6, subject.getExtraHours(), 0.0);
    }

    @Test
    public void setExtraHours() {
        setUp();
        subject.setExtraHours(5);
        assertEquals(5, subject.getExtraHours(), 0.0);
    }

    @Test
    public void setStudiedHoursDay() {
        setUp();
        subject.setStudiedHoursDay(5.5);
        assertEquals(5.5, subject.getStudiedHoursDay(), 0.0);
    }

    @Test
    public void increaseStudiedHoursDay() {
        setUp();
        subject.setStudiedHoursDay(5.5);
        subject.increaseStudiedHoursDay(0.5);
        assertEquals(6, subject.getStudiedHoursDay(), 0.0);
    }

    @Test
    public void setStudiedHoursWeek() throws Exception {
        setUp();
        subject.setStudiedHoursWeek(5.5);
        assertEquals(5.5, subject.getStudiedHoursWeek(), 0.0);
    }

    @Test
    public void increaseStudiedHoursWeek() {
        setUp();
        subject.setStudiedHoursWeek(5.5);
        subject.increaseStudiedHoursWeek(0.5);
        assertEquals(6, subject.getStudiedHoursWeek(), 0.0);
    }

    @Test
    public void setStudiedHoursSemester() {
        setUp();
        subject.setStudiedHoursSemester(5.5);
        assertEquals(5.5, subject.getStudiedHoursSemester(), 0.0);
    }

    @Test
    public void increaseStudiedHoursSemester() {
        setUp();
        subject.setStudiedHoursSemester(5.5);
        subject.increaseStudiedHoursSemester(1.5);
        assertEquals(7, subject.getStudiedHoursSemester(), 0.0);
    }

    @Test
    public void getAllTasks() {
        setUp();
        SubjectTask task1 = new SubjectTask.TaskBuilder("a", 50).build();
        SubjectTask task2 = new SubjectTask.TaskBuilder("b", 50).build();
        SubjectTask task3 = new SubjectTask.TaskBuilder("c", 50).build();
        subject.addTask(task1);
        subject.addTask(task2);
        subject.addTask(task3);
        for( SubjectTask currentTask : subject.getTasks() ){
            if( !((currentTask.equals(task1) || currentTask.equals(task2) || currentTask.equals(task3)))){
                fail();
            }
        }
    }

    @Test
    public void getGradedTasks() {
        setUp();
        SubjectTask task1 = new SubjectTask.TaskBuilder("a", 20)
                .setTag("")
                .setGrade(5.0)
                .setDone(true)
                .setDelivered(true)
                .setGraded(true)
                .build();
        SubjectTask task2 = new SubjectTask.TaskBuilder("b", 20)
                .setTag("")
                .setGrade(5.0)
                .setDone(true)
                .setDelivered(true)
                .setGraded(true)
                .build();
        SubjectTask task3 = new SubjectTask.TaskBuilder("c", 50).build();
        subject.addTask(task1);
        subject.addTask(task2);
        subject.addTask(task3);
        for( SubjectTask currentTask : subject.getGradedTasks() ){
            if(!(currentTask.equals(task1)||currentTask.equals(task2)))
                fail();
        }
    }

    @Test
    public void getNonGradedTasks() {
        setUp();
        SubjectTask task1 = new SubjectTask.TaskBuilder("a", 20)
                .setTag("")
                .setGrade(5.0)
                .setDone(true)
                .setDelivered(true)
                .setGraded(true)
                .build();
        SubjectTask task2 = new SubjectTask.TaskBuilder("b", 50).build();
        SubjectTask task3 = new SubjectTask.TaskBuilder("c", 50).build();
        subject.addTask(task1);
        subject.addTask(task2);
        subject.addTask(task3);
        for( SubjectTask currentTask : subject.getNonGradedTasks() ){
            if(!(currentTask.equals(task2)||currentTask.equals(task3)))
                fail();
        }
    }

    @Test
    public void getDeliveredTasks() {
        setUp();
        SubjectTask task1 = new SubjectTask.TaskBuilder("a", 20)
                .setTag("")
                .setGrade(5.0)
                .setDone(true)
                .setDelivered(true)
                .setGraded(true)
                .build();
        SubjectTask task2 = new SubjectTask.TaskBuilder("b", 20)
                .setTag("")
                .setGrade(5.0)
                .setDone(true)
                .setDelivered(true)
                .setGraded(true)
                .build();
        SubjectTask task3 = new SubjectTask.TaskBuilder("c", 50).build();
        subject.addTask(task1);
        subject.addTask(task2);
        subject.addTask(task3);
        for( SubjectTask currentTask : subject.getDeliveredTasks() ){
            if(!(currentTask.equals(task1)||currentTask.equals(task2)))
                fail();
        }
    }

    @Test
    public void getNonDeliveredTasks() {
        setUp();
        SubjectTask task1 = new SubjectTask.TaskBuilder("a", 20)
                .setTag("")
                .setGrade(5.0)
                .setDone(true)
                .setDelivered(true)
                .setGraded(true)
                .build();
        SubjectTask task2 = new SubjectTask.TaskBuilder("b", 50).build();
        SubjectTask task3 = new SubjectTask.TaskBuilder("c", 50).build();
        subject.addTask(task1);
        subject.addTask(task2);
        subject.addTask(task3);
        for( SubjectTask currentTask : subject.getNonDeliveredTasks() ){
            if(!(currentTask.equals(task2)||currentTask.equals(task3)))
                fail();
        }
    }

    @Test
    public void containsTask(){
        setUp();
        SubjectTask task1 = new SubjectTask.TaskBuilder("a", 50).build();
        SubjectTask task2 = new SubjectTask.TaskBuilder("b", 50).build();
        SubjectTask task3 = new SubjectTask.TaskBuilder("c", 50).build();
        subject.addTask(task1);
        subject.addTask(task2);
        subject.addTask(task3);
        try{
            assertTrue(subject.containsTask(task1));
            assertTrue(subject.containsTask(task2));
            assertTrue(subject.containsTask(task3));
            assertFalse(subject.containsTask(new SubjectTask.TaskBuilder("a", 5).build()));
        }catch (IllegalArgumentException e){
            fail();
        }
        subject.setTaskName(task1, "e");
    }

    @Test
    public void addTask() {
        setUp();
        SubjectTask task1 = new SubjectTask.TaskBuilder("a", 50).build();
        SubjectTask task2 = new SubjectTask.TaskBuilder("b", 50).build();
        SubjectTask task3 = new SubjectTask.TaskBuilder("c", 50).build();
        subject.addTask(task1);
        subject.addTask(task2);
        subject.addTask(task3);
        assertEquals(task1, subject.getTask("a"));
        assertEquals(task2, subject.getTask("b"));
        assertEquals(task3, subject.getTask("c"));
    }

    @Test
    public void deleteTask() {
        setUp();
        SubjectTask task1 = new SubjectTask.TaskBuilder("a", 50).build();
        SubjectTask task2 = new SubjectTask.TaskBuilder("b", 50).build();
        SubjectTask task3 = new SubjectTask.TaskBuilder("c", 50).build();
        subject.addTask(task1);
        subject.addTask(task2);
        subject.addTask(task3);
        try{
            subject.deleteTask(task1);
            subject.deleteTask(task2);
            assertFalse(subject.containsTask(task1));
            assertFalse(subject.containsTask(task2));
            assertTrue(subject.containsTask(task3));
        }catch (IllegalArgumentException | NoSuchElementException e){
            fail();
        }
    }

    @Test
    public void setDelivered() {
        setUp();
        SubjectTask task1 = new SubjectTask.TaskBuilder("a", 50).build();
        subject.addTask(task1);
        try{
            subject.setDelivered("b", true);
            fail();
        }catch (NoSuchElementException e){}
        try{
            subject.setDelivered("", true);
            fail();
        }catch (IllegalArgumentException e){}
        try{
            subject.markAsDone("a");
            subject.setDelivered("a", true);
            assertTrue(subject.getTask("a").isDelivered());
        }catch (IllegalArgumentException | NoSuchElementException e){
            fail();
        }
    }

    @Test
    public void setGraded() {
        setUp();
        SubjectTask task1 = new SubjectTask.TaskBuilder("a", 50).build();
        subject.addTask(task1);
        try{
            subject.setGrade("b",5.0);
            subject.setGraded("b", true);
            fail();
        }catch (NoSuchElementException e){}
        try{
            subject.setGraded("", true);
            fail();
        }catch (IllegalArgumentException e){}
        try{
            subject.markAsDone("a");
            subject.markAsDelivered("a");
            subject.setGrade("a", 5.0);
            subject.setGraded("a", true);
            assertTrue(subject.getTask("a").isGraded());
        }catch (IllegalArgumentException | NoSuchElementException e){
            fail();
        }
    }

    @Test
    public void setDone(){
        setUp();
        SubjectTask task1 = new SubjectTask.TaskBuilder("a", 50).build();
        subject.addTask(task1);
        try{
            subject.setDone("b", true);
            fail();
        }catch (NoSuchElementException e){}
        try{
            subject.setDone("", true);
            fail();
        }catch (IllegalArgumentException e){}
        try{
            subject.setDone("a", true);
            assertTrue(subject.getTask("a").isDone());
        }catch (IllegalArgumentException | NoSuchElementException e){
            fail();
        }
    }

    @Test
    public void setGrade(){
        setUp();
        SubjectTask task1 = new SubjectTask.TaskBuilder("a", 50).build();
        subject.addTask(task1);
        subject.setDone("a", true);
        subject.setDelivered("a", true);
        try{
            subject.setGrade("a", 5.0);
            assertEquals(5.0, subject.getTask("a").getGrade(), 0.0);
        }catch (IllegalArgumentException | NoSuchElementException e){
            fail();
        }
        try{
            subject.setGrade("a", 5.0);
            assertEquals(5.0, subject.getTask("a").getGrade(), 0.0);
        }catch (IllegalArgumentException | NoSuchElementException e){
            fail();
        }
    }

    @Test
    public void getGradedTasksPercentage() {
        setUp();
        SubjectTask task1 = new SubjectTask.TaskBuilder("a", 25).build();
        SubjectTask task2 = new SubjectTask.TaskBuilder("b", 20).build();
        SubjectTask task3 = new SubjectTask.TaskBuilder("c", 25).build();
        subject.addTask(task1);
        subject.addTask(task2);
        subject.addTask(task3);

        subject.markAsDone("a");
        subject.markAsDone("b");
        subject.markAsDone("c");

        subject.markAsDelivered("a");
        subject.markAsDelivered("b");
        subject.markAsDelivered("c");

        subject.setGrade("a", 5.0);
        subject.markAsGraded("a");

        assertEquals(25, subject.getGradedTasksPercentage(), 0.0);

        subject.setGrade("b", 5.0);
        subject.markAsGraded("b");
        subject.setGrade("c", 5.0);
        subject.markAsGraded("c");
        assertEquals(70, subject.getGradedTasksPercentage(), 0.0);
    }

    @Test
    public void getCurrentGrade() {
        setUp();
        SubjectTask task1 = new SubjectTask.TaskBuilder("a", 25).build();
        SubjectTask task2 = new SubjectTask.TaskBuilder("b", 20).build();
        SubjectTask task3 = new SubjectTask.TaskBuilder("c", 55).build();
        subject.addTask(task1);
        subject.addTask(task2);
        subject.addTask(task3);

        subject.markAsDone("a");
        subject.markAsDone("b");
        subject.markAsDone("c");

        subject.markAsDelivered("a");
        subject.markAsDelivered("b");
        subject.markAsDelivered("c");
        assertEquals(0, subject.getCurrentGrade(), 0.000000001);
        subject.setGrade("a", 3.0);
        subject.markAsGraded("a");
        assertEquals(3, subject.getCurrentGrade(), 0.0000000001);
        subject.setGrade("b", 4);
        subject.markAsGraded("b");
        assertEquals(3.4, subject.getCurrentGrade(), 0.0000000001);
        subject.setGrade("c", 5);
        subject.markAsGraded("c");
        assertEquals(4.3, subject.getCurrentGrade(), 0.0000000001);
    }
}