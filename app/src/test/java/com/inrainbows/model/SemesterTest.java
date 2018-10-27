package com.inrainbows.model;

import com.inrainbows.mvp.model.Semester;
import com.inrainbows.mvp.model.Subject;

import org.junit.Before;
import org.junit.Test;

import java.util.NoSuchElementException;

import static org.junit.Assert.*;

/**
 * @author diego on 11/01/2018.
 */
public class SemesterTest {

    private Semester semester;
    
    @Before
    public void setUp() {
        semester = new Semester.SemesterBuilder(1L,"a",2018, 1,
                22, 2018, 5, 12, true).build();

    }
}