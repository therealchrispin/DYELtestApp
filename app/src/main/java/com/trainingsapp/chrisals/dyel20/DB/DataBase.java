package com.trainingsapp.chrisals.dyel20.DB;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import com.trainingsapp.chrisals.dyel20.DB.DataBaseContract.*;


/**
 * Created by chris.als on 20.02.17.
 */
public class DataBase extends SQLiteOpenHelper {
    public static final int DATABASE_VERSION = 1;
    public static final String DATABASE_NAME = "DYEL.db";

    public DataBase(Context context){
        super(context,DATABASE_NAME,null,DATABASE_VERSION);
    }

    @Override
    public void onDowngrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        super.onDowngrade(db, oldVersion, newVersion);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(ExerciseEntry.CREATE_EXERCISE_ENTRY);
        db.execSQL(WorkoutEntry.CREATE_WORKOUT_ENTRY);
        db.execSQL(ExerciseWorkoutEntry.CREATE_EXERCISE_WORKOUT_ENTRY);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL(ExerciseEntry.SQL_DELETE_EXERCISE_ENTRY);
        db.execSQL(WorkoutEntry.SQL_DELETE_WORKOUT_ENTRY);
        db.execSQL(ExerciseWorkoutEntry.SQL_DELETE_Ex_Wo_Entry);
        onCreate(db);
    }
}
