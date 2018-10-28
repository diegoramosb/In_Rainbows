package com.inrainbows.model;

import com.inrainbows.mvp.model.Grade;
import com.inrainbows.mvp.model.Semester;
import com.inrainbows.mvp.model.Subject;
import com.inrainbows.mvp.model.TimeLog;

import junit.framework.Assert;

import org.joda.time.DateTime;
import org.junit.Before;
import org.junit.Test;
/**
 * @author diego on 11/01/2018.
 */
public class SemesterTest {

    private Semester semester;

    @Before
    public void setUp() {
        semester = new Semester.SemesterBuilder(1L, "a", 2018, 1,
                22, 2018, 5, 12, true).build();
        addSubjects();
    }

    private void addSubjects() {
        Subject subject = new Subject.SubjectBuilder(1L, "asdf", 3, 3, semester.getId()).build();
        addGrades(subject);
        addTimeLogs(subject);
        semester.addSubject(subject);

        subject = new Subject.SubjectBuilder(2L, "sdaf", 1, 1.5, semester.getId()).build();
        addGrades(subject);
        addTimeLogs(subject);
        semester.addSubject(subject);
    }

    private void addGrades(Subject subject) {
        if(subject.getId() == 1L) {
            Grade grade = new Grade.GradeBuilder(1L, 1L, "asdf", 100)
                    .setGraded(true).setGrade(4.0).build();
            subject.addGrade(grade);
        }
        else if(subject.getId() == 2L) {
            Grade grade = new Grade.GradeBuilder(1L, 1L, "asdf", 100)
                    .setGraded(true).setGrade(5.0).build();
            subject.addGrade(grade);
        }
    }

    private void addTimeLogs(Subject subject) {
        DateTime current = DateTime.now();

        DateTime yesterday = current.minusDays(1);
        TimeLog timeLog = new TimeLog(yesterday, yesterday.plusHours(1));
        subject.addTimeLog(timeLog);

        DateTime twoAgo = current.minusDays(2);
        timeLog = new TimeLog(twoAgo, twoAgo.plusHours(2));
        subject.addTimeLog(timeLog);
    }

    @Test
    public void creditsTest() {
        Assert.assertEquals(4.0, semester.credits());
    }

    @Test
    public void currentGradeTest() {
        Assert.assertEquals(4.25, semester.currentGrade());
    }
}