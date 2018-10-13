package com.inrainbows.model;

import com.inrainbows.mvp.model.TimeLog;

import junit.framework.Assert;

import org.joda.time.DateTime;
import org.junit.Before;
import org.junit.Test;

/**
 * @author diego on 13/10/2018.
 */
public class TimeLogTest  {

    private TimeLog timeLog;

    private DateTime testDateTime;

    @Before
    public void setUp() {
        testDateTime = new DateTime();
        timeLog = new TimeLog(100, testDateTime);
    }

    @Test
    public void getDuration() {
        Assert.assertEquals(100.0,timeLog.getDuration());
    }

    @Test
    public void getStartTime() {
        Assert.assertEquals(testDateTime, timeLog.getStartTime());
    }
}
