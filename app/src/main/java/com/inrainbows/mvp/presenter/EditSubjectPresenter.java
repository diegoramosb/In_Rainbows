package com.inrainbows.mvp.presenter;

import com.inrainbows.mvp.model.Subject;
import com.inrainbows.mvp.view.EditSubjectContract;
import com.inrainbows.persistence.daos.SubjectDao;

/**
 * Presenter for editing subjects
 * @author diego on 22/07/2018.
 */
public class EditSubjectPresenter implements EditSubjectContract.Presenter {

    /**
     * Subject DAO
     */
    private SubjectDao dao;

    /**
     * Binded view
     */
    private EditSubjectContract.View view;

    /**
     * Creates a new presener binded to a view
     * @param view binded view
     */
    public EditSubjectPresenter(EditSubjectContract.View view) {
        this.view = view;
        this.view.setPresenter(this);
        this.dao = view.getDb().subjectDao();
    }

    /**
     * Inserts the given subject in the database
     * @param subject subject to insert
     */
    @Override
    public void insertSubject(Subject subject) {
        dao.insert(subject.toEntity());
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
