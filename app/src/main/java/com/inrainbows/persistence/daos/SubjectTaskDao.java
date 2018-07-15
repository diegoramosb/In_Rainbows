package com.inrainbows.persistence.daos;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import com.inrainbows.mvp.model.SubjectTask;
import com.inrainbows.persistence.entities.SubjectEntity;
import com.inrainbows.persistence.entities.SubjectTaskEntity;

import java.util.List;

/**
 * @author diego on 12/07/2018.
 */
@Dao
public interface SubjectTaskDao {


    @Query("SELECT * FROM SUBJECT_TASKS")
    List<SubjectTaskEntity> getAllList();

    @Query("SELECT * FROM SUBJECT_TASKS")
    LiveData<List<SubjectTaskEntity>> getAllLD();

    @Query("SELECT * FROM SUBJECT_TASKS WHERE ID = :id")
    SubjectTaskEntity get(long id);

    @Insert
    void insert(SubjectTaskEntity entity);

    @Update
    void update(SubjectEntity entity);

    @Delete
    void delete(SubjectTaskEntity entity);
}
