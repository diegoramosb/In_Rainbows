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
public interface SubjectDao extends BaseDao<SubjectEntity> {

    @Query("SELECT * FROM SUBJECTS")
    List<SubjectEntity> getAllList();

    @Query("SELECT * FROM SUBJECTS WHERE SEMESTER_ID = :semesterId")
    List<SubjectEntity> getAllSubjectsWithSemesterId(long semesterId);

    @Query("SELECT * FROM SUBJECTS WHERE ID = :id")
    SubjectEntity get(long id);
}
