package com.inrainbows.model;

import com.inrainbows.mvp.model.Grade;
import com.inrainbows.persistence.entities.GradeEntity;

import junit.framework.Assert;

import org.junit.Before;
import org.junit.Test;

import uk.co.jemos.podam.api.PodamFactory;
import uk.co.jemos.podam.api.PodamFactoryImpl;

/**
 * @author diego on 13/10/2018.
 */
public class GradeTest {

    private PodamFactory factory = new PodamFactoryImpl();

    private Grade grade;

    @Before
    public void setUp() {
        grade = new Grade.GradeBuilder(1L, 1L, "asdf", 20).build();
    }

    @Test
    public void getIdTest() {
        Assert.assertEquals(1L, grade.getId());
    }

    @Test
    public void setIdTest() {
        grade.setId(2L);
        Assert.assertEquals(2L, grade.getId());
    }

    @Test
    public void getSubjectIdTest() {
        Assert.assertEquals(1L, grade.getSubjectId());
    }

    @Test
    public void setSubjectIdTest() {
        grade.setSubjectId(3L);
        Assert.assertEquals(3L, grade.getSubjectId());
    }

    @Test
    public void toEntityTest() {
        GradeEntity entity = grade.toEntity();
        Assert.assertEquals(1L, entity.getId());
        Assert.assertEquals(1L, entity.getSubjectId());
        Assert.assertEquals("asdf", entity.getName());
        Assert.assertEquals(20.0, entity.getPercentage());
        Assert.assertEquals(-1.0, entity.getGrade());
        Assert.assertFalse(entity.isGraded());

        grade.setGrade(5.0);
        grade.setGraded(true);
        entity = grade.toEntity();
        Assert.assertEquals(5.0, entity.getGrade());
        Assert.assertTrue(entity.isGraded());
    }

    @Test
    public void GradeFromEntityTest() {
        GradeEntity entity = factory.manufacturePojoWithFullData(GradeEntity.class);
        grade = new Grade(entity);
        Assert.assertEquals(entity.getGrade(), grade.getGrade());
        Assert.assertEquals(entity.getName(), grade.getName());
        Assert.assertEquals(entity.getPercentage(), grade.getPercentage());
        Assert.assertEquals(entity.getSubjectId(), grade.getSubjectId());
        Assert.assertEquals(entity.getId(), grade.getId());
        Assert.assertEquals(entity.isGraded(), grade.isGraded());
    }
}
