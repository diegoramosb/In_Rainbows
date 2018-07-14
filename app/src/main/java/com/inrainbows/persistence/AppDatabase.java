package com.inrainbows.persistence;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.arch.persistence.room.TypeConverters;
import android.content.Context;

import com.inrainbows.mvp.model.Semester;
import com.inrainbows.mvp.model.Subject;
import com.inrainbows.persistence.daos.SemesterDao;
import com.inrainbows.persistence.daos.SubjectDao;
import com.inrainbows.persistence.daos.SubjectTaskDao;
import com.inrainbows.persistence.entities.SemesterEntity;
import com.inrainbows.persistence.entities.SubjectEntity;
import com.inrainbows.persistence.entities.SubjectTaskEntity;

/**
 * @author diego on 12/07/2018.
 */
@Database(entities = {SemesterEntity.class, SubjectEntity.class, SubjectTaskEntity.class}, version = 1)
@TypeConverters(DateConverter.class)
public abstract class AppDatabase extends RoomDatabase {

    private static AppDatabase INSTANCE;

    public abstract SemesterDao semesterDao();

    public abstract SubjectDao subjectDao();

    public abstract SubjectTaskDao subjectTaskDao();

    public static AppDatabase getInMemoryDatabase(Context context){
        if(INSTANCE == null){
            INSTANCE = Room.inMemoryDatabaseBuilder(context.getApplicationContext(), AppDatabase.class).build();
        }
        return INSTANCE;
    }

    public void destroyInstance(){
        INSTANCE = null;
    }
}
