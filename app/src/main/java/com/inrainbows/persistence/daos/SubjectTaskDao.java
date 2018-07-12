package com.inrainbows.persistence.daos;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;

import com.inrainbows.mvp.model.SubjectTask;
import com.inrainbows.persistence.entities.SubjectTaskEntity;

import java.util.List;

/**
 * @author diego on 12/07/2018.
 */
@Dao
public interface SubjectTaskDao {

    @Query("SELECT * FROM SUBJECT_TASKS")
    LiveData<List<SubjectTask>> getAllSubjectTasks();

    @Query("SELECT * FROM SUBJECT_TASKS WHERE ID = :id")
    SubjectTask getSubjectTask(long id);

    @Insert
    void insertSubjectTask(SubjectTaskEntity entity);

    @Delete
    void deleteSubjectTask(SubjectTaskEntity entity);
}
