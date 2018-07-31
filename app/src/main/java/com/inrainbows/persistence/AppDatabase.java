package com.inrainbows.persistence;

import android.arch.persistence.db.SupportSQLiteDatabase;
import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.arch.persistence.room.TypeConverters;
import android.arch.persistence.room.migration.Migration;
import android.content.Context;
import android.support.annotation.NonNull;

import com.inrainbows.persistence.daos.GradeDao;
import com.inrainbows.persistence.daos.SemesterDao;
import com.inrainbows.persistence.daos.SubjectDao;
import com.inrainbows.persistence.entities.GradeEntity;
import com.inrainbows.persistence.entities.SemesterEntity;
import com.inrainbows.persistence.entities.SubjectEntity;

/**
 * @author diego on 12/07/2018.
 */
@Database(entities = {SemesterEntity.class, SubjectEntity.class, GradeEntity.class}, version = 1)
@TypeConverters(DateTimeConverter.class)
public abstract class AppDatabase extends RoomDatabase {

    private static AppDatabase INSTANCE;

    public abstract SemesterDao semesterDao();

    public abstract SubjectDao subjectDao();

    public abstract GradeDao gradeDao();

    public static AppDatabase getDatabase(Context context) {
        if (INSTANCE == null) {
            INSTANCE = Room.databaseBuilder(context.getApplicationContext(), AppDatabase.class, "in_rainbows")
//                    .addMigrations(MIGRATION_4_5)
                    .allowMainThreadQueries() //Eventually remove and  do asynchronously.
                    .build();
        }
        return INSTANCE;
    }

    public static AppDatabase getMemoryDatabase(Context context) {
        if (INSTANCE == null) {
            INSTANCE = Room.inMemoryDatabaseBuilder(context.getApplicationContext(), AppDatabase.class)
//                    .addMigrations(MIGRATION_4_5)
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

    static final Migration MIGRATION_2_3 = new Migration(2, 3) {
        @Override
        public void migrate(@NonNull SupportSQLiteDatabase database) {
            database.execSQL("ALTER TABLE SUBJECT_TASKS ADD COLUMN DUE_DATE INTEGER NOT NULL DEFAULT 0");
        }
    };

    static final Migration MIGRATION_3_4 = new Migration(3, 4) {
        @Override
        public void migrate(@NonNull SupportSQLiteDatabase database) {
            database.execSQL("DROP TABLE SUBJECT_TASKS");
        }
    };

    static final Migration MIGRATION_4_5 = new Migration(4, 5) {
        @Override
        public void migrate(@NonNull SupportSQLiteDatabase database) {
            database.execSQL("DROP TABLE GRADES");
            database.execSQL("CREATE TABLE IF NOT EXISTS 'GRADES' (" +
                    "'ID' INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, " +
                    "'SUBJECT_ID' INTEGER NOT NULL, " +
                    "'NAME' TEXT NOT NULL, " +
                    "'GRADE' REAL, " +
                    "'PERCENTAGE' REAL NOT NULL, " +
                    "'GRADED' INTEGER NOT NULL, " +
                    "FOREIGN KEY('SUBJECT_ID') REFERENCES 'SUBJECTS'('ID') ON UPDATE NO ACTION ON DELETE CASCADE )");
        }
    };
}
