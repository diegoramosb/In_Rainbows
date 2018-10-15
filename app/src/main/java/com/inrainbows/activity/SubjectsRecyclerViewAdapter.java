package com.inrainbows.activity;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
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
        View view = inflater.inflate(R.layout.subject_recyclerview_row, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Subject subject = subjects.get(position);
        String subjectName = subject.getName();
        String grade = subject.currentGrade()+"";
        String studiedHoursDay = new StringBuilder().append(subject.studiedHoursDay()).append(" h/")
                .append(subject.dailyHoursString()).append(" h")
                .toString();
        String studiedHoursWeek = new StringBuilder().append(subject.studiedHoursWeek()).append(" h/")
                .append(subject.getWeeklyExtraHours()).append(" h")
                .toString();
        String studiedHoursSemester = new StringBuilder().append(subject.studiedHoursSemester()).append(" h/")
                .append(subject.getSemesterExtraHours()).append(" h")
                .toString();

        holder.tvSubjectName.setText(subjectName);
        holder.tvGrade.setText(grade);
        holder.tvProgressToday.setText(studiedHoursDay);
        holder.tvProgressWeek.setText(studiedHoursWeek);
        holder.tvProgressSemester.setText(studiedHoursSemester);

        holder.pbToday.setProgress(75/*subject.dailyStudiedPercentage()*/);
        holder.pbWeek.setProgress(subject.weeklyStudiedPercentage());
        holder.pbSemester.setProgress(subject.semesterStudiedPercentage());
    }

    @Override
    public int getItemCount() {
        return subjects.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        TextView tvSubjectName;

        TextView tvGrade;

        TextView tvProgressToday;

        TextView tvProgressWeek;

        TextView tvProgressSemester;

        ProgressBar pbToday;

        ProgressBar pbWeek;

        ProgressBar pbSemester;

        public ViewHolder(View itemView) {
            super(itemView);
            tvSubjectName = itemView.findViewById(R.id.tv_subject_rv_name);
            tvGrade = itemView.findViewById(R.id.tv_subject_rv_grade);
            tvProgressToday = itemView.findViewById(R.id.tv_subject_rv_progress_today);
            tvProgressWeek = itemView.findViewById(R.id.tv_subject_rv_progress_week);
            tvProgressSemester = itemView.findViewById(R.id.tv_subject_rv_progress_semester);

            pbToday = itemView.findViewById(R.id.pb_subject_rv_today);
            pbWeek = itemView.findViewById(R.id.pb_subject_rv_week);
            pbSemester = itemView.findViewById(R.id.pb_subject_rv_semester);


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
