package com.inrainbows.activity.fragments;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatDialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.NumberPicker;

import com.inrainbows.R;

import butterknife.BindView;

/**
 * Fragment to choose an mount of time to register in a time log
 * @author diego on 5/08/2018.
 */
public class NumberPickerDialogFragment extends AppCompatDialogFragment implements NumberPicker.OnValueChangeListener {

    /**
     * Constant for add time mode
     */
    public final static int ADD = 1;

    /**
     * Constant for remove time mode
     */
    public final static int REMOVE = 2;

    /**
     * NumberPicker for hours
     */
    NumberPicker npHours;

    /**
     * NumberPicker for minutes
     */
    NumberPicker npMinutes;

    /**
     *
     */
    private NumberPickerDialogListener listener;

    @BindView(R.id.header_frame)
    public FrameLayout headerFrame;

    /**
     * Creates a new dialog
     * @param savedInstanceState instance state
     * @return a new dialog
     */
    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {

        //Gets the dialog builder
        final AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());

        //Gets the dialog inflater
        final LayoutInflater inflater = getActivity().getLayoutInflater();
        View view = inflater.inflate(R.layout.number_picker_dialog, null);

        //Configures the number pickers
        npHours = view.findViewById(R.id.np_hour);
        npHours.setMinValue(0);
        npHours.setMaxValue(23);
        npMinutes = view.findViewById(R.id.np_minute);
        npMinutes.setMinValue(0);
        npMinutes.setMaxValue(59);

        //Sets the title to add time or remove time accordingly

        final int title = getArguments().getInt("title");

        //Configures the dialog elements
        builder.setView(view)
                .setTitle( title == ADD ? R.string.add_time_dialog_title : R.string.remove_time_dialog_title)
                .setPositiveButton(android.R.string.ok, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        int hours = npHours.getValue();
                        int minutes = npHours.getValue();

                        listener.modifyTime(hours, minutes, (title == ADD ? ADD : REMOVE));
                    }
                }).setNegativeButton(android.R.string.cancel, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                        dialogInterface.dismiss();
            }
        });
        return builder.create();
    }

    /**
     * Method to set the app context as the listener
     * @param context app context
     */
    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

        try {
            listener = (NumberPickerDialogListener) context;
        }
        catch (ClassCastException e){
            throw new ClassCastException(context.toString() + " must implement NumberPickerDialogListener");
        }
    }

    /**
     * Interface of the NumberPickerDialogListener for the fragment listener
     */
    public interface NumberPickerDialogListener {
        void modifyTime(int hours, int minutes, int mode);
    }

    @Override
    public void onValueChange(NumberPicker numberPicker, int i, int i1) {
        //TODO: Do something when the value is changed
    }
}
