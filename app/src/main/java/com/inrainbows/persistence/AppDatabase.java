package com.inrainbows.persistence;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.RoomDatabase;

import com.inrainbows.mvp.model.Semester;
import com.inrainbows.persistence.daos.SemesterDao;
import com.inrainbows.persistence.daos.SubjectDao;
import com.inrainbows.persistence.daos.SubjectTaskDao;

/**
 * @author diego on 12/07/2018.
 */
@Database(entities = {Semester.class}, version = 1)
public abstract class AppDatabase extends RoomDatabase {

    public abstract SemesterDao semesterDao();

    public abstract SubjectDao subjectDao();

    public abstract SubjectTaskDao subjectTaskDao();
}
