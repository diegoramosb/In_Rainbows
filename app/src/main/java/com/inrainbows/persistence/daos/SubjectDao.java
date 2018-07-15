package com.inrainbows.persistence.daos;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import com.inrainbows.mvp.model.Subject;
import com.inrainbows.persistence.entities.SubjectEntity;

import java.util.List;

/**
 * @author diego on 12/07/2018.
 */
@Dao
public interface SubjectDao {

    @Query("SELECT * FROM SUBJECTS")
    List<SubjectEntity> getAllList();

    @Query("SELECT * FROM SUBJECTS")
    LiveData<List<SubjectEntity>> getAllLD();
//
//    Query("SELECT * FROM SUBJECTS WHERE SEMESTER_ID = :semesterId")
//    List<SubjectEntity> getAllS

    @Query("SELECT * FROM SUBJECTS WHERE ID = :id")
    SubjectEntity get(long id);

    @Insert
    void insert(SubjectEntity subjectEntity);

    @Update
    void update(SubjectEntity subjectEntity);

    @Delete
    void delete(SubjectEntity subjectEntity);
}
