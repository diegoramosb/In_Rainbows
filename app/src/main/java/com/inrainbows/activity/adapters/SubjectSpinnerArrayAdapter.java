package com.inrainbows.activity;

import android.content.Context;
import android.graphics.Color;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.inrainbows.R;
import com.inrainbows.mvp.model.Semester;
import com.inrainbows.mvp.model.Subject;

import java.util.ArrayList;
import java.util.List;

/**
 * @author diego on 31/07/2018.
 */
public class SubjectSpinnerArrayAdapter extends ArrayAdapter<Subject> {

    private Context context;

    private List<Subject> subjects;

    public SubjectSpinnerArrayAdapter(Context context, int resourceId, List<Subject> subjects) {
        super(context, resourceId, subjects);
        this.context = context;
        this.subjects = subjects;
    }

    @Override
    public int getCount() {
        return subjects.size();
    }

    @Override
    public Subject getItem(int position) {
        return subjects.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
//        TextView label = (TextView) super.getView(position, convertView, parent);
        TextView label = new TextView(context);
        label.setTextColor(Color.BLACK);
        label.setText(subjects.get(position).getName());
        return label;
    }

    @Override
    public View getDropDownView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View v = super.getDropDownView(position, convertView, parent);
        TextView tv = ((TextView) v);
        tv.setText(subjects.get(position).getName());
        tv.setTextColor(Color.BLACK);
        return v;
    }
}
