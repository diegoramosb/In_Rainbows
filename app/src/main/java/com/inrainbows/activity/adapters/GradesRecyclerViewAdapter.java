package com.inrainbows.activity;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import com.inrainbows.R;
import com.inrainbows.mvp.model.Grade;

import java.util.List;

import butterknife.OnClick;

/**
 * Adapter for the RecyclerView that shows the grades in the Subject view
 * @author diego on 4/08/2018.
 */
public class GradesRecyclerViewAdapter extends RecyclerView.Adapter<GradesRecyclerViewAdapter.ViewHolder> {

    private List<Grade> grades;

    private LayoutInflater inflater;

    public GradesRecyclerViewAdapter(Context context, List<Grade> grades) {
        this.inflater = LayoutInflater.from(context);
        this.grades = grades;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.grade_recyclerview_row, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Grade grade = grades.get(position);
        String gradeName = grade.getName();
        String gradeGrade = grade.isGraded() ? grade.getGrade()+"" : "Not graded yet";
        String percentage = grade.getPercentage()+"";

        holder.tvGradeName.setText(gradeName);
        holder.tvGradeGrade.setText(gradeGrade);
        holder.tvGradePercentage.setText(percentage);
    }

    @Override
    public int getItemCount() {
        return 0;
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        TextView tvGradeName;

        TextView tvGradeGrade;

        TextView tvGradePercentage;

        ImageButton ibEditGrade;

        public ViewHolder(View view){
            super(view);
            tvGradeName = view.findViewById(R.id.tv_grade_name);
            tvGradeGrade = view.findViewById(R.id.tv_grade_grade);
            tvGradePercentage = view.findViewById(R.id.tv_grade_percentage);

            ibEditGrade = view.findViewById(R.id.ib_edit_subject);
        }

        @Override
        public void onClick(View view) {
            //Does nothing
        }

        @OnClick(R.id.ib_edit_subject)
        public void onClickEditSubject(){
            //TODO: Show edit subject activity in edit mode instead of add mode.
        }
    }

    public void setGrades(List<Grade> grades) {
        this.grades = grades;
    }

    Grade getItem(int id){
        return grades.get(id);
    }
}
