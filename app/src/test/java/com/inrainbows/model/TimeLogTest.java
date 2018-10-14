package com.inrainbows.model;

import com.inrainbows.mvp.model.TimeLog;

import junit.framework.Assert;

import org.joda.time.DateTime;
import org.joda.time.Instant;
import org.joda.time.Interval;
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

    private DateTime startTime;

    private DateTime endTime;

    @Before
    public void setUp() {
        startTime = factory.manufacturePojo(DateTime.class);
        endTime =  new DateTime().plus(factory.manufacturePojo(Long.class));
        timeLog = new TimeLog(startTime, endTime);
    }

    @Test
    public void getStartTimeTest() {
        Assert.assertEquals(startTime, timeLog.getStartTime());
    }

    @Test
    public void setStartTimeTest() {
        startTime = startTime.plus(10000);
        timeLog.setStartTime(startTime);
        Assert.assertEquals(startTime, timeLog.getStartTime());
    }

    @Test
    public void getDurationTest() {
        Assert.assertEquals(new Interval(startTime, endTime).toDurationMillis(), timeLog.duration());
    }
}
