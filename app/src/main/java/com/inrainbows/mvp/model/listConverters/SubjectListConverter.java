package com.inrainbows.mvp.model.listConverters;

import android.os.Parcel;

import com.inrainbows.mvp.model.Subject;

import org.parceler.ParcelConverter;
import org.parceler.Parcels;

import java.util.ArrayList;
import java.util.List;

/**
 * Converter from a list of subjects to a parcel and vice-versa
 * @author diego on 1/10/2018.
 */
public class SubjectListConverter implements ParcelConverter<List<Subject>> {
    /**
     * Write the given input parameter to the destinationParcel.
     *
     * @param input  U instance
     * @param parcel Parcel to write to
     */
    @Override
    public void toParcel(List<Subject> input, Parcel parcel) {
        if(input == null) {
            parcel.writeInt(-1);
        }
        else {
            parcel.writeInt(input.size());
            for(Subject subject : input){
                parcel.writeParcelable(Parcels.wrap(subject), 0);
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
    public List<Subject> fromParcel(Parcel parcel) {
        int size = parcel.readInt();
        if(size < 0) {
            return null;
        }
        List<Subject> subjects = new ArrayList<>();
        for(int i = 0; i < size; i++) {
            subjects.add(Parcels.unwrap(parcel.readParcelable(Subject.class.getClassLoader())));
        }
        return subjects;
    }
}
