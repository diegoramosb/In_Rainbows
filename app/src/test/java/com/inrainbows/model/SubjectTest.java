package com.inrainbows.model;

import com.inrainbows.mvp.model.Grade;
import com.inrainbows.mvp.model.Subject;
import com.inrainbows.mvp.model.TimeLog;
import com.inrainbows.persistence.entities.SubjectEntity;

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

    private DateTime yesterday;

    private DateTime twoAgo;

    @Before
    public void setUp(){
        subject = new Subject.SubjectBuilder(1L, "asdf", 3, 3, 1L).build();

        addGrades();
        addTimeLogs();
    }

    private void addTimeLogs() {
        DateTime current = DateTime.now();

        yesterday = current.minusDays(1);
        TimeLog timeLog = new TimeLog(yesterday, yesterday.plusHours(1));
        subject.addTimeLog(timeLog);

        twoAgo = current.minusDays(2);
        timeLog = new TimeLog(twoAgo, twoAgo.plusHours(2));
        subject.addTimeLog(timeLog);
    }

    private void addGrades() {
        Grade grade = new Grade.GradeBuilder(1L, 1L, "asdf", 10)
                .setGraded(true).setGrade(4.5).build();
        subject.addGrade(grade);

        grade = new Grade.GradeBuilder(2L, 1L, "asdf", 50)
                .setGraded(true).setGrade(3.0).build();
        subject.addGrade(grade);

        grade = new Grade.GradeBuilder(3L, 1L, "ASDF", 40)
                .setGraded(true).setGrade(5.0).build();
        subject.addGrade(grade);
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
        Assert.assertEquals(100.0, subject.gradedPercentage());
    }

    @Test
    public void currentGradeTest() {
        Assert.assertEquals(3.95, subject.currentGrade());
    }

    @Test
    public void toEntityTest() {
        subject = factory.manufacturePojoWithFullData(Subject.class);
        SubjectEntity  entity = subject.toEntity();
        Assert.assertEquals(subject.getId(), entity.getId());
        Assert.assertEquals(subject.getSemesterId(), entity.getSemesterId());
        Assert.assertEquals(subject.getName(), entity.getName());
        Assert.assertEquals(subject.getCredits(), entity.getCredits());
        Assert.assertEquals(subject.getTotalHours(), entity.getTotalHours());
        Assert.assertEquals(subject.getClassHours(), entity.getClassHours());
        Assert.assertEquals(subject.getDailyExtraHours(), entity.getDailyExtraHours());
        Assert.assertEquals(subject.getWeeklyExtraHours(), entity.getWeeklyExtraHours());
        Assert.assertEquals(subject.getSemesterExtraHours(), entity.getSemesterExtraHours());
    }

    @Test
    public void subjectFromEntityTetst() {
        SubjectEntity entity = factory.manufacturePojoWithFullData(SubjectEntity.class);
        subject = new Subject(entity);
        Assert.assertEquals(entity.getId(), subject.getId());
        Assert.assertEquals(entity.getSemesterId(), subject.getSemesterId());
        Assert.assertEquals(entity.getName(), subject.getName());
        Assert.assertEquals(entity.getCredits(), subject.getCredits());
        Assert.assertEquals(entity.getClassHours(), subject.getClassHours());
        Assert.assertEquals(entity.getTotalHours(), subject.getTotalHours());
        Assert.assertEquals(entity.getWeeklyExtraHours(), subject.getWeeklyExtraHours());
        Assert.assertEquals(entity.getDailyExtraHours(), subject.getDailyExtraHours());
        Assert.assertEquals(entity.getSemesterExtraHours(), subject.getSemesterExtraHours());
    }
}