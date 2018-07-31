package com.inrainbows.persistence.daos;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Query;

import com.inrainbows.mvp.model.Grade;
import com.inrainbows.persistence.entities.GradeEntity;

import java.util.List;

/**
 * @author diego on 31/07/2018.
 */
@Dao
public interface GradeDao extends BaseDao<GradeEntity> {

    @Query("SELECT * FROM GRADES WHERE ID = :id")
    GradeEntity getById(long id);

    @Query("SELECT * FROM GRADES WHERE SUBJECT_ID = :subjectId")
    List<GradeEntity> getAllWithSubjectId(long subjectId);

}
