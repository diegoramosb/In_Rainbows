package com.inrainbows.model;

import com.inrainbows.mvp.model.Grade;
import com.inrainbows.mvp.model.Subject;
import com.inrainbows.mvp.model.TimeLog;

import junit.framework.Assert;

import org.joda.time.DateTime;
import org.junit.Before;
import org.junit.Test;

import java.util.List;


import static org.junit.Assert.*;

/**
 * @author diego on 20/12/2017.
 */
public class SubjectTest {


    private Subject subject;

    private DateTime yesterday;

    private DateTime twoAgo;

    @Before
    public void setUp(){
        subject = new Subject.SubjectBuilder(1L, "asdf", 3, 3, 1L).build();

        Grade grade = new Grade.GradeBuilder(1L, 1L, "asdf", 10).setGraded(true).build();
        subject.addGrade(grade);

        grade = new Grade.GradeBuilder(2L, 1L, "asdf", 50).setGraded(true).build();
        subject.addGrade(grade);

        grade = new Grade.GradeBuilder(3L, 1L, "ASDF", 50).build();
        subject.addGrade(grade);

        DateTime current = DateTime.now();

        yesterday = current.minusDays(1);
        TimeLog timeLog = new TimeLog(yesterday, yesterday.plusHours(1));
        subject.addTimeLog(timeLog);

        twoAgo = current.minusDays(2);
        timeLog = new TimeLog(twoAgo, twoAgo.plusHours(2));
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
    public void studiedMinutesSemesterTest() {
        Assert.assertEquals(180.0, subject.studiedMinutesThisSemester());
    }

    @Test
    public void studiedMinutesRangeTest() {
        Assert.assertEquals(180.0, subject.studiedMinutesRange(twoAgo, yesterday));
    }

    @Test
    public void studiedMinutesDateTest() {
        Assert.assertEquals(60.0, subject.studiedMinutesOnDate(yesterday));
    }

    @Test
    public void studiedMinutesToHoursTest() {
        Assert.assertEquals((120.0/60), subject.studiedMinutesToHours(subject.studiedMinutesOnDate(twoAgo)));
    }

    @Test
    public void gradedPercentageTest() {
        Assert.assertEquals(60.0, subject.gradedPercentage());
    }
}