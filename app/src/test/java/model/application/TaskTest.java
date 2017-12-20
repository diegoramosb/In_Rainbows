package model.application;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * @author diego on 20/12/2017.
 */
public class TaskTest {

    private Task task;

    @Before
    public void setUp() throws Exception {
        task = new Task("a", 100);
    }

    @Test
    public void toStringTest() throws Exception {
        setUp();
        assertEquals("a, not done yet, 100.0%", task.toString());
        task.setTag("a");
        assertEquals("a, a, not done yet, 100.0%", task.toString());
        task.setDone(true);
        assertEquals("a, a, done, not delivered yet, 100.0%", task.toString());
        task.setDelivered(true);
        assertEquals("a, a, done, delivered, not graded yet, 100.0%", task.toString());
        task.setGraded(true);
        task.setGrade(5.0);
        assertEquals("a, a, done, delivered, grade: 5.0, 100.0%", task.toString());
    }

    @Test
    public void getName() throws Exception {
    }

    @Test
    public void getGrade() throws Exception {
    }

    @Test
    public void setGrade() throws Exception {
    }

    @Test
    public void setName() throws Exception {
    }

    @Test
    public void getDone() throws Exception {
    }

    @Test
    public void setDone() throws Exception {
    }

    @Test
    public void getTag() throws Exception {
    }

    @Test
    public void setTag() throws Exception {
    }

    @Test
    public void getPercentage() throws Exception {
    }

    @Test
    public void setPercentage() throws Exception {
    }

    @Test
    public void getGraded() throws Exception {
    }

    @Test
    public void setGraded() throws Exception {
    }

    @Test
    public void getDelivered() throws Exception {
    }

    @Test
    public void setDelivered() throws Exception {
    }

}