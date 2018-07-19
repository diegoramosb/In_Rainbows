package com.inrainbows.persistence.daos;

import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Update;

import com.inrainbows.persistence.entities.SemesterEntity;

/**
 * @author diego on 19/07/2018.
 */
public interface BaseDao<T> {

    @Insert
    void insert(T entity);

    @Update
    void update(T entity);

    @Delete
    void delete(T entity);

}
