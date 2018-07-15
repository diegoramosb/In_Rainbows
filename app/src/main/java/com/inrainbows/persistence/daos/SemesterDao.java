package com.inrainbows.persistence.daos;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;
import android.util.Log;

import com.inrainbows.mvp.model.Semester;
import com.inrainbows.persistence.entities.SemesterEntity;

import java.util.List;

/**
 * @author diego on 12/07/2018.
 */
@Dao
public interface SemesterDao {

    @Query("SELECT * FROM SEMESTERS")
    List<SemesterEntity> getAllList();

    @Query("SELECT * FROM SEMESTERS")
    LiveData<List<SemesterEntity>> getAllLD();

    @Query("SELECT * FROM SEMESTERS WHERE ID = :id")
    SemesterEntity getById(long id);

    @Insert
    void insert(SemesterEntity entity);

    @Update
    void update(SemesterEntity entity);

    @Delete
    void delete(SemesterEntity entity);

}
