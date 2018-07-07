package com.inrainbows.mvp.model;

import org.joda.time.DateTime;
import org.joda.time.Period;

import dagger.Component;

/**
 * @author diego on 6/07/2018.
 */
public interface DateManager {
    DateTime getCurrentDateTime();

    Period getCurrentWeek();

    Integer getCurrentWeekNumber(Semester semester);
}
