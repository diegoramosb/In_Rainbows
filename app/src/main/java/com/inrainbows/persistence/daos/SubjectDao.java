package com.inrainbows.persistence.daos;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;

import com.inrainbows.mvp.model.Subject;
import com.inrainbows.persistence.entities.SubjectEntity;

import java.util.List;

/**
 * @author diego on 12/07/2018.
 */
@Dao
public interface SubjectDao {

    @Query("SELECT * FROM SUBJECTS")
    LiveData<List<SubjectEntity>> getAllSubjects();

    @Query("SELECT * FROM SUBJECTS WHERE ID = :id")
    SubjectEntity getSubject(long id);

    @Insert
    void addSubject(SubjectEntity subjectEntity);

    @Delete
    void deleteSubject(SubjectEntity subjectEntity);
}
