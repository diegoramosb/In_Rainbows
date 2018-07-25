package com.inrainbows.activity;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import com.inrainbows.R;
import com.inrainbows.mvp.model.Subject;

import java.util.List;

/**
 * @author diego on 23/07/2018.
 */
public class SubjectsRecyclerViewAdapter extends RecyclerView.Adapter<SubjectsRecyclerViewAdapter.ViewHolder> {

    private List<Subject> subjects;

    private LayoutInflater inflater;

    private ItemClickListener clickListener;


    public SubjectsRecyclerViewAdapter(Context context, List<Subject> subjects) {
        this.inflater = LayoutInflater.from(context);
        this.subjects = subjects;
    }


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.recyclerview_row, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        String subjectName = subjects.get(position).getName();
        String grade = subjects.get(position).currentGrade()+"";
        String studiedHoursWeek = subjects.get(position).getStudiedHoursWeek()+" H";
        holder.tvSubjectName.setText(subjectName);
        holder.tvGrade.setText(grade);
        holder.tvStudiedHoursWeek.setText(studiedHoursWeek);
    }

    @Override
    public int getItemCount() {
        return subjects.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        TextView tvSubjectName;

        TextView tvGrade;

        TextView tvStudiedHoursWeek;

        ImageButton ibAddTask;

        public ViewHolder(View itemView) {
            super(itemView);
            tvSubjectName = itemView.findViewById(R.id.tv_subject_name);
            tvGrade = itemView.findViewById(R.id.tv_subject_grade);
            tvStudiedHoursWeek = itemView.findViewById(R.id.tv_studied_hours_week);
            ibAddTask = itemView.findViewById(R.id.ib_add_task);

            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            if(clickListener != null){
                clickListener.onItemClick(view, getAdapterPosition());
            }
        }
    }

    public void setSubjects(List<Subject> subjects) {
        this.subjects = subjects;
    }

    Subject getItem(int id){
        return subjects.get(id);
    }

    public void setClickListener(ItemClickListener listener) {
        this.clickListener = listener;
    }

    public interface ItemClickListener {
        void onItemClick(View view, int position);
    }

}
