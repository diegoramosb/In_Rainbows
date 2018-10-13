package com.inrainbows.model;

import com.inrainbows.mvp.model.TimeLog;

import junit.framework.Assert;

import org.joda.time.DateTime;
import org.junit.Before;
import org.junit.Test;

import uk.co.jemos.podam.api.PodamFactory;
import uk.co.jemos.podam.api.PodamFactoryImpl;

/**
 * @author diego on 13/10/2018.
 */
public class TimeLogTest  {

    private PodamFactory factory = new PodamFactoryImpl();

    private TimeLog timeLog;

    private DateTime testDateTime;

    @Before
    public void setUp() {
        testDateTime = factory.manufacturePojo(DateTime.class);
        timeLog = new TimeLog(100, testDateTime);
    }

    @Test
    public void getDurationTest() {
        Assert.assertEquals(100.0,timeLog.getDuration());
    }

    @Test
    public void setDurationTest() {
        timeLog.setDuration(500);
        Assert.assertEquals(500D, timeLog.getDuration());
    }

    @Test
    public void getStartTimeTest() {
        Assert.assertEquals(testDateTime, timeLog.getStartTime());
    }

    @Test
    public void setStartTimeTest() {
        testDateTime = testDateTime.plus(10000);
        timeLog.setStartTime(testDateTime);
        Assert.assertEquals(testDateTime, timeLog.getStartTime());
    }
}
