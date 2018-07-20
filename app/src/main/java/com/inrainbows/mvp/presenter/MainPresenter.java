package com.inrainbows.mvp.presenter;

import com.inrainbows.mvp.model.Semester;
import com.inrainbows.mvp.view.BaseView;
import com.inrainbows.mvp.view.MainContract;
import com.inrainbows.persistence.AppDatabase;
import com.inrainbows.persistence.daos.SemesterDao;
import com.inrainbows.persistence.entities.SemesterEntity;

import java.util.ArrayList;
import java.util.List;

/**
 * @author diego on 15/07/2018.
 */
public class MainPresenter implements MainContract.Presenter {

    MainContract.View view;

    AppDatabase db;

    public MainPresenter(MainContract.View view) {
        this.view = view;
        this.view.setPresenter(this);
        db = view.getDb();
    }

    @Override
    public void showAddSemesterView() {
        view.showAddSemester();
    }

    @Override
    public void setCurrentSemester(Semester semester) {
        Semester oldSemester = getCurrentSemester();
        if(oldSemester != null) {
            oldSemester.setCurrentSemester(false);
            db.semesterDao().update(oldSemester.toEntity());
        }

        semester.setCurrentSemester(true);
        db.semesterDao().update(semester.toEntity());

        updateCurrentSemesterName();
    }

    @Override
    public void updateCurrentSemesterName() {
        view.setCurrentSemesterName(getCurrentSemester() != null ? getCurrentSemester().getSemesterName() : "Tap here to add semester");
    }

    public Semester getCurrentSemester() {
        SemesterEntity entity = db.semesterDao().getCurrentSemester();
        if(entity != null){
            return new Semester(db.semesterDao().getCurrentSemester());
        }
        else {
            return null;
        }
    }

    @Override
    public List<Semester> getAllSemesters() {
        return semesterEntityListToSemester(db.semesterDao().getAllList());
    }


    @Override
    public List<String> getAllSemesterNames() {
        return semesterEntityListToNames(db.semesterDao().getAllList());
    }

    private List<Semester> semesterEntityListToSemester(List<SemesterEntity> semesterEntityList){
        List<Semester> ans = new ArrayList<>();
        for(SemesterEntity semesterEntity : semesterEntityList){
            ans.add(new Semester(semesterEntity));
        }
        return ans;
    }

    private List<String> semesterEntityListToNames(List<SemesterEntity> semesterEntityList){
        List<String> ans = new ArrayList<>();
        for(SemesterEntity semesterEntity : semesterEntityList){
            ans.add(semesterEntity.getSemesterName());
        }
        return ans;
    }

    @Override
    public void onCreate() {
    }

    @Override
    public void onStart() {

    }

    @Override
    public void onStop() {

    }

    @Override
    public void onPause() {

    }
}
