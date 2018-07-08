package com.inrainbows.mvp.model;

import org.joda.time.DateTime;
import org.joda.time.DateTimeConstants;
import org.joda.time.DateTimeZone;
import org.joda.time.MutableDateTime;
import org.joda.time.Period;
import org.joda.time.Weeks;

/**
 * @author diego on 6/07/2018.
 */
public class Calendar implements DateManager {

    @Override
    public DateTime getCurrentDateTime() {
        return new DateTime(DateTimeZone.forID("America/Bogota")); //Immutable timezone temporarily
    }

//    @Override
//    public Period getCurrentWeek() {
//
//        DateTime currentDateTime = getCurrentDateTime();
//
//        MutableDateTime startOfCurrentWeek = currentDateTime.toMutableDateTime(); //Creates a mutable copy of currentDate
//        startOfCurrentWeek.addDays( 1 - startOfCurrentWeek.getDayOfWeek() ); //Finds the first day of the current week by subtracting 1 minus the current day of week
//        startOfCurrentWeek.setMillisOfDay(0); //Sets the hour of the copy to 00:00:00
//
//        MutableDateTime endOfCurrentWeek = currentDateTime.toMutableDateTime();
//        endOfCurrentWeek.addDays(7 - endOfCurrentWeek.getDayOfWeek()); //Finds the last day of the week by adding 7 minus the current day of the week
//        endOfCurrentWeek.setMillisOfDay(DateTimeConstants.MILLIS_PER_DAY - 1); //Sets the hour of the copy to 23:59:59
//
//        return new Period(startOfCurrentWeek, endOfCurrentWeek); //Creates a period of 6D23H59M59.999S (a week)
//    }

    /**
     * @return number of the current week relative to the start of the semester
     */
    public Integer getCurrentWeekNumber(Semester semester) {
        DateTime currentDateTime = getCurrentDateTime();
        DateTime startDate = semester.getStartDate();

        if( currentDateTime.isAfter(startDate) ){
            return integerWeeksBetween(currentDateTime, startDate) + 1;
        }else {
            return integerWeeksBetween(currentDateTime, startDate) - 1;
        }
    }

    private Integer integerWeeksBetween(DateTime firstDateTime, DateTime secondDateTime){
        return Weeks.weeksBetween(secondDateTime, firstDateTime).getWeeks();
    }
}
