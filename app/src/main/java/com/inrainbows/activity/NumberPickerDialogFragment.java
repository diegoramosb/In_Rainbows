package com.inrainbows.activity;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatDialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.NumberPicker;
import android.widget.Toast;

import com.inrainbows.R;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * @author diego on 5/08/2018.
 */
public class NumberPickerDialogFragment extends AppCompatDialogFragment implements NumberPicker.OnValueChangeListener {

    private int hours;

    private int minutes;

    NumberPicker npHours;

    NumberPicker npMinutes;

    private NumberPickerDialogListener listener;

    @BindView(R.id.header_frame)
    FrameLayout headerFrame;


//    public static NumberPickerDialogFragment newInstance() {
//        return new NumberPickerDialogFragment();
//    }

//    @Override
//    public void onCreate(@Nullable Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//    }

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {

        final AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());

        final LayoutInflater inflater = getActivity().getLayoutInflater();
        View view = inflater.inflate(R.layout.number_picker_dialog, null);

        npHours = view.findViewById(R.id.np_hour);
        npHours.setMinValue(0);
        npHours.setMaxValue(23);
        npMinutes = view.findViewById(R.id.np_minute);
        npMinutes.setMinValue(0);
        npMinutes.setMaxValue(59);

        builder.setView(view)
                .setTitle(R.string.number_picker_dialog_title)
                .setPositiveButton(android.R.string.ok, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        int hours = npHours.getValue();
                        int minutes = npHours.getValue();

                        listener.saveInput(hours, minutes);
                    }
                }).setNegativeButton(android.R.string.cancel, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        dialogInterface.dismiss();
                    }
        });
        return builder.create();
    }

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

    public interface NumberPickerDialogListener {
        void saveInput(int hours, int minutes);
    }

    @Override
    public void onValueChange(NumberPicker numberPicker, int i, int i1) {

    }
}
