package com.inrainbows.model.model;

import com.inrainbows.mvp.model.Calendar;
import com.inrainbows.mvp.model.Semester;

import org.joda.time.DateTime;
import org.joda.time.DateTimeComparator;
import org.junit.Test;

import static org.junit.Assert.*;


/**
 * @author diego on 6/07/2018.
 */
public class CalendarTest {

    Calendar calendar;

    public CalendarTest() {
        this.calendar = new Calendar();
    }

    @Test
    public void getCurrentWeek() {
        //TODO Find a way to get start and end instants of a period.
        try {
//            System.out.println(semester.getCurrentWeek());
        }catch (Exception e){
            e.printStackTrace();
        }
    }


    @Test
    public void getCurrentWeekNumber() {
        DateTime startDate = calendar.getCurrentDateTime();

        Semester semester = new Semester.SemesterBuilder("a", startDate, startDate.plusWeeks(16)).build();
        assertEquals(new Integer(1), calendar.getCurrentWeekNumber(semester));

        semester.setStartDate(startDate.minusWeeks(2));
        assertEquals(new Integer(3), calendar.getCurrentWeekNumber(semester));

        semester.setStartDate(startDate.plusWeeks(3));
        assertEquals(new Integer(-3), calendar.getCurrentWeekNumber(semester));
    }
}
