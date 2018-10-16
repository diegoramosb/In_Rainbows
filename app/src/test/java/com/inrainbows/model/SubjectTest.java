package com.inrainbows.model;

import com.inrainbows.mvp.model.Grade;
import com.inrainbows.mvp.model.Subject;
import com.inrainbows.mvp.model.TimeLog;

import junit.framework.Assert;

import org.joda.time.DateTime;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

import uk.co.jemos.podam.api.PodamFactory;
import uk.co.jemos.podam.api.PodamFactoryImpl;

import static org.junit.Assert.*;

/**
 * @author diego on 20/12/2017.
 */
public class SubjectTest {

    private PodamFactory factory = new PodamFactoryImpl();

    private Subject subject;

    @Before
    public void setUp(){
        subject = new Subject.SubjectBuilder(1L, "asdf", 3, 3, 1L).build();
        for(int i = 0; i < 5; i++) {
            Grade grade = factory.manufacturePojo(Grade.class);
            subject.addGrade(grade);
        }
        for(int i = 0; i < 2; i++) {
            TimeLog timeLog = new TimeLog(DateTime.now(), DateTime.now().plusMinutes(75));
            subject.addTimeLog(timeLog);
        }
        DateTime yesterday = DateTime.now().minusDays(1);
        TimeLog timeLog = new TimeLog(yesterday, yesterday.plusHours(1));
        subject.addTimeLog(timeLog);
    }

    @Test
    public void gradedGradesTest() {
        List<Grade> graded = subject.gradedGrades();
        List<Grade> allGrades = subject.getGrades();
        for(Grade grade : allGrades) {
            if(graded.contains(grade) && !grade.isGraded()){
                fail("All grades in Graded should be graded");
            }
        }
    }

    @Test
    public void studiedMinutesDayTest() {
        Assert.assertEquals(150.0, subject.studiedMinutesToday());
    }

    public void studiedMinutesWeek() {

    }

//
//    @Test
//    public void getGradedTasks() {
//        setUp();
//        SubjectTask task1 = new SubjectTask.TaskBuilder(1L,"a", 20)
//                .setTag("")
//                .setGrade(5.0)
//                .setDone(true)
//                .setDelivered(true)
//                .setGraded(true)
//                .build();
//        SubjectTask task2 = new SubjectTask.TaskBuilder(1L,"b", 20)
//                .setTag("")
//                .setGrade(5.0)
//                .setDone(true)
//                .setDelivered(true)
//                .setGraded(true)
//                .build();
//        SubjectTask task3 = new SubjectTask.TaskBuilder(1L,"c", 50).build();
//        subject.addGrade(task1);
//        subject.addGrade(task2);
//        subject.addGrade(task3);
//        for( SubjectTask currentTask : subject.getGradedTasks() ){
//            if(!(currentTask.equals(task1)||currentTask.equals(task2)))
//                fail();
//        }
//    }
//
//    @Test
//    public void getNonGradedTasks() {
//        setUp();
//        SubjectTask task1 = new SubjectTask.TaskBuilder(1L,"a", 20)
//                .setTag("")
//                .setGrade(5.0)
//                .setDone(true)
//                .setDelivered(true)
//                .setGraded(true)
//                .build();
//        SubjectTask task2 = new SubjectTask.TaskBuilder(1L,"b", 50).build();
//        SubjectTask task3 = new SubjectTask.TaskBuilder(1L,"c", 50).build();
//        subject.addGrade(task1);
//        subject.addGrade(task2);
//        subject.addGrade(task3);
//        for( SubjectTask currentTask : subject.getNonGradedTasks() ){
//            if(!(currentTask.equals(task2)||currentTask.equals(task3)))
//                fail();
//        }
//    }
//
//    @Test
//    public void getDeliveredTasks() {
//        setUp();
//        SubjectTask task1 = new SubjectTask.TaskBuilder(1L,"a", 20)
//                .setTag("")
//                .setGrade(5.0)
//                .setDone(true)
//                .setDelivered(true)
//                .setGraded(true)
//                .build();
//        SubjectTask task2 = new SubjectTask.TaskBuilder(1L,"b", 20)
//                .setTag("")
//                .setGrade(5.0)
//                .setDone(true)
//                .setDelivered(true)
//                .setGraded(true)
//                .build();
//        SubjectTask task3 = new SubjectTask.TaskBuilder(1L,"c", 50).build();
//        subject.addGrade(task1);
//        subject.addGrade(task2);
//        subject.addGrade(task3);
//        for( SubjectTask currentTask : subject.getDeliveredTasks() ){
//            if(!(currentTask.equals(task1)||currentTask.equals(task2)))
//                fail();
//        }
//    }
//
//    @Test
//    public void getNonDeliveredTasks() {
//        setUp();
//        SubjectTask task1 = new SubjectTask.TaskBuilder(1L,"a", 20)
//                .setTag("")
//                .setGrade(5.0)
//                .setDone(true)
//                .setDelivered(true)
//                .setGraded(true)
//                .build();
//        SubjectTask task2 = new SubjectTask.TaskBuilder(1L,"b", 50).build();
//        SubjectTask task3 = new SubjectTask.TaskBuilder(1L,"c", 50).build();
//        subject.addGrade(task1);
//        subject.addGrade(task2);
//        subject.addGrade(task3);
//        for( SubjectTask currentTask : subject.getNonDeliveredTasks() ){
//            if(!(currentTask.equals(task2)||currentTask.equals(task3)))
//                fail();
//        }
//    }
//
//    @Test
//    public void containsTask(){
//        setUp();
//        SubjectTask task1 = new SubjectTask.TaskBuilder(1L,"c", 50).build();
//        SubjectTask task2 = new SubjectTask.TaskBuilder(1L,"c", 50).build();
//        SubjectTask task3 = new SubjectTask.TaskBuilder(1L,"c", 50).build();
//
//        subject.addGrade(task1);
//        subject.addGrade(task2);
//        subject.addGrade(task3);
//        try{
//            assertTrue(subject.containsTask(task1));
//            assertTrue(subject.containsTask(task2));
//            assertTrue(subject.containsTask(task3));
//            assertFalse(subject.containsTask(new SubjectTask.TaskBuilder(1L,"a", 5).build()));
//        }catch (IllegalArgumentException e){
//            fail();
//        }
//        subject.setTaskName(task1, "e");
//    }
//
//    @Test
//    public void addTask() {
//        setUp();
//        SubjectTask task1 = new SubjectTask.TaskBuilder(1L,"c", 50).build();
//        SubjectTask task2 = new SubjectTask.TaskBuilder(1L,"c", 50).build();
//        SubjectTask task3 = new SubjectTask.TaskBuilder(1L,"c", 50).build();
//
//        subject.addGrade(task1);
//        subject.addGrade(task2);
//        subject.addGrade(task3);
//        assertEquals(task1, subject.getTask("a"));
//        assertEquals(task2, subject.getTask("b"));
//        assertEquals(task3, subject.getTask("c"));
//    }
//
//    @Test
//    public void deleteTask() {
//        setUp();
//        SubjectTask task1 = new SubjectTask.TaskBuilder(1L,"c", 50).build();
//        SubjectTask task2 = new SubjectTask.TaskBuilder(1L,"c", 50).build();
//        SubjectTask task3 = new SubjectTask.TaskBuilder(1L,"c", 50).build();
//
//        subject.addGrade(task1);
//        subject.addGrade(task2);
//        subject.addGrade(task3);
//        try{
//            subject.deleteTask(task1);
//            subject.deleteTask(task2);
//            assertFalse(subject.containsTask(task1));
//            assertFalse(subject.containsTask(task2));
//            assertTrue(subject.containsTask(task3));
//        }catch (IllegalArgumentException | NoSuchElementException e){
//            fail();
//        }
//    }
//
//    @Test
//    public void setDelivered() {
//        setUp();
//        SubjectTask task1 = new SubjectTask.TaskBuilder(1L,"a", 50).build();
//        subject.addGrade(task1);
//        try{
//            subject.setDelivered("b", true);
//            fail();
//        }catch (NoSuchElementException e){}
//        try{
//            subject.setDelivered("", true);
//            fail();
//        }catch (IllegalArgumentException e){}
//        try{
//            subject.markAsDone("a");
//            subject.setDelivered("a", true);
//            assertTrue(subject.getTask("a").isDelivered());
//        }catch (IllegalArgumentException | NoSuchElementException e){
//            fail();
//        }
//    }
//
//    @Test
//    public void setGraded() {
//        setUp();
//        SubjectTask task1 = new SubjectTask.TaskBuilder(1L,"a", 50).build();
//        subject.addGrade(task1);
//        try{
//            subject.setGrade("b",5.0);
//            subject.setGraded("b", true);
//            fail();
//        }catch (NoSuchElementException e){}
//        try{
//            subject.setGraded("", true);
//            fail();
//        }catch (IllegalArgumentException e){}
//        try{
//            subject.markAsDone("a");
//            subject.markAsDelivered("a");
//            subject.setGrade("a", 5.0);
//            subject.setGraded("a", true);
//            assertTrue(subject.getTask("a").isGraded());
//        }catch (IllegalArgumentException | NoSuchElementException e){
//            fail();
//        }
//    }
//
//    @Test
//    public void setDone(){
//        setUp();
//        SubjectTask task1 = new SubjectTask.TaskBuilder(1L,"a", 50).build();
//        subject.addGrade(task1);
//        try{
//            subject.setDone("b", true);
//            fail();
//        }catch (NoSuchElementException e){}
//        try{
//            subject.setDone("", true);
//            fail();
//        }catch (IllegalArgumentException e){}
//        try{
//            subject.setDone("a", true);
//            assertTrue(subject.getTask("a").isDone());
//        }catch (IllegalArgumentException | NoSuchElementException e){
//            fail();
//        }
//    }
//
//    @Test
//    public void setGrade(){
//        setUp();
//        SubjectTask task1 = new SubjectTask.TaskBuilder(1L,"a", 50).build();
//        subject.addGrade(task1);
//        subject.setDone("a", true);
//        subject.setDelivered("a", true);
//        try{
//            subject.setGrade("a", 5.0);
//            assertEquals(5.0, subject.getTask("a").getGrade(), 0.0);
//        }catch (IllegalArgumentException | NoSuchElementException e){
//            fail();
//        }
//        try{
//            subject.setGrade("a", 5.0);
//            assertEquals(5.0, subject.getTask("a").getGrade(), 0.0);
//        }catch (IllegalArgumentException | NoSuchElementException e){
//            fail();
//        }
//    }
//
//    @Test
//    public void getGradedTasksPercentage() {
//        setUp();
//        SubjectTask task1 = new SubjectTask.TaskBuilder(1L,"c", 25).build();
//        SubjectTask task2 = new SubjectTask.TaskBuilder(1L,"c", 25).build();
//        SubjectTask task3 = new SubjectTask.TaskBuilder(1L,"c", 25).build();
//        subject.addGrade(task1);
//        subject.addGrade(task2);
//        subject.addGrade(task3);
//
//        subject.markAsDone("a");
//        subject.markAsDone("b");
//        subject.markAsDone("c");
//
//        subject.markAsDelivered("a");
//        subject.markAsDelivered("b");
//        subject.markAsDelivered("c");
//
//        subject.setGrade("a", 5.0);
//        subject.markAsGraded("a");
//
//        assertEquals(25, subject.gradedPercentage(), 0.0);
//
//        subject.setGrade("b", 5.0);
//        subject.markAsGraded("b");
//        subject.setGrade("c", 5.0);
//        subject.markAsGraded("c");
//        assertEquals(70, subject.gradedPercentage(), 0.0);
//    }
//
//    @Test
//    public void getCurrentGrade() {
//        setUp();
//        SubjectTask task1 = new SubjectTask.TaskBuilder(1L,"a", 25).build();
//        SubjectTask task2 = new SubjectTask.TaskBuilder(1L,"b", 20).build();
//        SubjectTask task3 = new SubjectTask.TaskBuilder(1L,"c", 55).build();
//        subject.addGrade(task1);
//        subject.addGrade(task2);
//        subject.addGrade(task3);
//
//        subject.markAsDone("a");
//        subject.markAsDone("b");
//        subject.markAsDone("c");
//
//        subject.markAsDelivered("a");
//        subject.markAsDelivered("b");
//        subject.markAsDelivered("c");
//        assertEquals(0, subject.currentGrade(), 0.000000001);
//        subject.setGrade("a", 3.0);
//        subject.markAsGraded("a");
//        assertEquals(3, subject.currentGrade(), 0.0000000001);
//        subject.setGrade("b", 4);
//        subject.markAsGraded("b");
//        assertEquals(3.4, subject.currentGrade(), 0.0000000001);
//        subject.setGrade("c", 5);
//        subject.markAsGraded("c");
//        assertEquals(4.3, subject.currentGrade(), 0.0000000001);
//    }
}