package model.application;

import org.junit.Test;

import model.data_structures.Bag;

import static org.junit.Assert.*;

/**
 * @author diego on 20/12/2017.
 */
public class SubjectTest {

    Subject subject;

    public void setUp(){
        subject = new Subject("a", 3, 3);
    }

    @Test
    public void subjectTest(){
        try{
            subject = new Subject("a", 3, 3);
            Bag<Task> tasks = new Bag<>();
            tasks.addAtEnd(new Task("a", 100));
            subject = new Subject("a", 3, 3, 2, 2, 2, 2, 2, tasks);
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
        Task task1 = new Task("a", 50);
        Task task2 = new Task("b", 50);
        Task task3 = new Task("c", 50);
        subject.addTask(task1);
        subject.addTask(task2);
        subject.addTask(task3);
        for( Task currentTask : subject.getAllTasks() ){
            if( !((currentTask.equals(task1) || currentTask.equals(task2) || currentTask.equals(task3)))){
                fail();
            }
        }
    }

    @Test
    public void getGradedTasks() {
        setUp();
        Task task1 = new Task("a", "", 20, 5.0, true, true, true);
        Task task2 = new Task("b", "", 20, 5.0, true, true, true);
        Task task3 = new Task("c", 50);
        subject.addTask(task1);
        subject.addTask(task2);
        subject.addTask(task3);
        for( Task currentTask : subject.getGradedTasks() ){
            if(!(currentTask.equals(task1)||currentTask.equals(task2)))
                fail();
        }
    }

    @Test
    public void getNonGradedTasks() {
        setUp();
        Task task1 = new Task("a", "", 20, 5.0, true, true, true);
        Task task2 = new Task("b", 50);
        Task task3 = new Task("c", 50);
        subject.addTask(task1);
        subject.addTask(task2);
        subject.addTask(task3);
        for( Task currentTask : subject.getNonGradedTasks() ){
            if(!(currentTask.equals(task2)||currentTask.equals(task3)))
                fail();
        }
    }

    @Test
    public void getDeliveredTasks() {
        setUp();
        Task task1 = new Task("a", "", 20, 5.0, true, true, true);
        Task task2 = new Task("b", "", 20, 5.0, true, true, true);
        Task task3 = new Task("c", 50);
        subject.addTask(task1);
        subject.addTask(task2);
        subject.addTask(task3);
        for( Task currentTask : subject.getDeliveredTasks() ){
            if(!(currentTask.equals(task1)||currentTask.equals(task2)))
                fail();
        }
    }

    @Test
    public void getNonDeliveredTasks() {
        setUp();
        Task task1 = new Task("a", "", 20, 5.0, true, true, true);
        Task task2 = new Task("b", 50);
        Task task3 = new Task("c", 50);
        subject.addTask(task1);
        subject.addTask(task2);
        subject.addTask(task3);
        for( Task currentTask : subject.getNonDeliveredTasks() ){
            if(!(currentTask.equals(task2)||currentTask.equals(task3)))
                fail();
        }
    }

    @Test
    public void addTask() {
        setUp();
        Task task1 = new Task("a", 50);
        Task task2 = new Task("b", 50);
        Task task3 = new Task("c", 50);
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
        Task task1 = new Task("a", 50);
        Task task2 = new Task("b", 50);
        Task task3 = new Task("c", 50);
        subject.addTask(task1);
        subject.addTask(task2);
        subject.addTask(task3);
        assertEquals(task1, subject.getTask("a"));
        assertEquals(task2, subject.getTask("b"));
        assertEquals(task3, subject.getTask("c"));
    }

    @Test
    public void markAsDelivered() {
    }

    @Test
    public void setDelivered() {
    }

    @Test
    public void markAsGraded() {
    }

    @Test
    public void setGraded() {
    }

    @Test
    public void getGradedTasksPercentage() {
    }

    @Test
    public void getCurrentGrade() {
    }
}