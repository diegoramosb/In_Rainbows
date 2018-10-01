package com.inrainbows.mvp.model.listConverters;

import android.os.Parcel;

import com.inrainbows.mvp.model.Grade;

import org.parceler.ParcelConverter;
import org.parceler.Parcels;

import java.util.ArrayList;
import java.util.List;

/**
 * @author diego on 1/10/2018.
 */
public class GradeListsConverter implements ParcelConverter<List<Grade>> {
    /**
     * Write the given input parameter to the destinationParcel.
     *
     * @param input  U instance
     * @param parcel Parcel to write to
     */
    @Override
    public void toParcel(List<Grade> input, Parcel parcel) {
        if(input == null) {
            parcel.writeInt(-1);
        }
        else {
            parcel.writeInt(input.size());
            for(Grade grade : input){
                parcel.writeParcelable(Parcels.wrap(grade), 0);
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
    public List<Grade> fromParcel(Parcel parcel) {
        int size = parcel.readInt();
        if(size < 0) {
            return null;
        }
        List<Grade> subjects = new ArrayList<>();
        for(int i = 0; i < size; i++) {
            subjects.add((Grade) Parcels.unwrap(parcel.readParcelable(Grade.class.getClassLoader())));
        }
        return subjects;
    }
}
