package com.inrainbows.activity.fragments;

import android.app.Dialog;
import android.app.TimePickerDialog;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.widget.TimePicker;

/**
 * @author diego on 5/08/2018.
 */
public class TimePickerFragment extends DialogFragment implements TimePickerDialog.OnTimeSetListener {

    private int hours;

    private int minutes;

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        hours = 0;
        minutes = 25;
        return new TimePickerDialog(getActivity(), this, hours, minutes, false);
    }

    @Override
    public void onTimeSet(TimePicker timePicker, int i, int i1) {
        //Do stuff
    }


    public int getHours() {
        return hours;
    }

    public TimePickerFragment setHours(int hours) {
        this.hours = hours;
        return this;
    }

    public int getMinutes() {
        return minutes;
    }

    public TimePickerFragment setMinutes(int minutes) {
        this.minutes = minutes;
        return this;
    }
}
