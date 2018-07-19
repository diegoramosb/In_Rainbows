package com.inrainbows.persistence;

import android.arch.persistence.room.TypeConverter;

import org.joda.time.DateTime;

import java.util.Date;

/**
 * @author diego on 14/07/2018.
 */
public class DateTimeConverter {
    @TypeConverter
    public static DateTime toDateTime(Long timestamp){
        return timestamp == null ? null : new DateTime(timestamp);
    }
    @TypeConverter
    public static Long toTimestamp(DateTime date){
        return date == null ? null : date.getMillis();
    }
}
