package com.inrainbows.mvp.model.listConverters;

import android.os.Parcel;

import com.inrainbows.mvp.model.TimeLog;

import org.parceler.ParcelConverter;
import org.parceler.Parcels;

import java.util.ArrayList;
import java.util.List;

/**
 * Converter from a list of subjects to a parcel and vice-versa
 * @author diego on 13/10/2018.
 */
public class TimeLogListConverter implements ParcelConverter<List<TimeLog>> {
    /**
     * Write the given input parameter to the destinationParcel.
     *
     * @param input  U instance
     * @param parcel Parcel to write to
     */
    @Override
    public void toParcel(List<TimeLog> input, Parcel parcel) {
        if(input == null) {
            parcel.writeInt(-1);
        }
        else {
            parcel.writeInt(input.size());
            for(TimeLog timeLog : input){
                parcel.writeParcelable(Parcels.wrap(timeLog), 0);
            }
        }
    }

    /**
     * Generates an instance from the values provided in the given parcel.
     *
     * @param parcel Parcel to read from
     * @return instance of the mapped class.
     */
    @Override
    public List<TimeLog> fromParcel(Parcel parcel) {
        int size = parcel.readInt();
        if(size < 0) {
            return null;
        }
        List<TimeLog> timeLogs = new ArrayList<>();
        for(int i = 0; i < size; i++) {
            timeLogs.add(Parcels.unwrap(parcel.readParcelable(TimeLog.class.getClassLoader())));
        }
        return timeLogs;
    }
}
