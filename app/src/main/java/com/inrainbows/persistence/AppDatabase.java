package com.inrainbows.persistence;

import android.arch.persistence.db.SupportSQLiteDatabase;
import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.arch.persistence.room.TypeConverters;
import android.arch.persistence.room.migration.Migration;
import android.content.Context;
import android.support.annotation.NonNull;

import com.inrainbows.persistence.daos.SemesterDao;
import com.inrainbows.persistence.daos.SubjectDao;
import com.inrainbows.persistence.daos.SubjectTaskDao;
import com.inrainbows.persistence.entities.SemesterEntity;
import com.inrainbows.persistence.entities.SubjectEntity;
import com.inrainbows.persistence.entities.SubjectTaskEntity;

/**
 * @author diego on 12/07/2018.
 */
@Database(entities = {SemesterEntity.class, SubjectEntity.class, SubjectTaskEntity.class}, version = 2)
@TypeConverters(DateTimeConverter.class)
public abstract class AppDatabase extends RoomDatabase {

    private static AppDatabase INSTANCE;

    public abstract SemesterDao semesterDao();

    public abstract SubjectDao subjectDao();

    public abstract SubjectTaskDao subjectTaskDao();

    public static AppDatabase getDatabase(Context context) {
        if (INSTANCE == null) {
            INSTANCE = Room.databaseBuilder(context.getApplicationContext(), AppDatabase.class, "in_rainbows")
                    .addMigrations(MIGRATION_1_2)
                    .allowMainThreadQueries() //Eventually remove and  do asynchronously.
                    .build();
        }
        return INSTANCE;
    }

    public static AppDatabase getMemoryDatabase(Context context) {
        if (INSTANCE == null) {
            INSTANCE = Room.inMemoryDatabaseBuilder(context.getApplicationContext(), AppDatabase.class)
                    .addMigrations(MIGRATION_1_2)
                    .allowMainThreadQueries()
                    .build();
        }
        return INSTANCE;
    }

    public static void destroyInstance() {
        INSTANCE = null;
    }

    static final Migration MIGRATION_1_2 = new Migration(1, 2) {
        @Override
        public void migrate(@NonNull SupportSQLiteDatabase database) {
            database.execSQL("ALTER TABLE SEMESTERS ADD COLUMN CURRENT_SEMESTER INTEGER NOT NULL DEFAULT 0");
        }
    };
}
