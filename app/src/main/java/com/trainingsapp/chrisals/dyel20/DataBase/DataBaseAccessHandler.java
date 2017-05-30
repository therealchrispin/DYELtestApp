package com.trainingsapp.chrisals.dyel20.DataBase;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.trainingsapp.chrisals.dyel20.DataBase.DataBaseContract.*;
import java.util.ArrayList;

/**
 * Created by chris.als on 21.02.17.
 */
public abstract class DataBaseAccessHandler {
    SQLiteDatabase db;

    public abstract ArrayList getAllItems();

    public abstract Cursor getCursor();

    public ContentValues getValues(String name, int sets, int reps, double weight, String id){
        ContentValues contentValues = new ContentValues();

        contentValues.put(ExerciseEntry._ID, id);
        contentValues.put(ExerciseEntry.COLUMN_EXERCISE_NAME, name);
        contentValues.put(ExerciseEntry.COLUMN_EXERCISE_SETS, String.valueOf(sets));
        contentValues.put(ExerciseEntry.COLUMN_EXERCISE_REPS, String.valueOf(reps));
        contentValues.put(ExerciseEntry.COLUMN_EXERCISE_WEIGHT, String.valueOf(weight));

        return contentValues;

    }

    public ContentValues getValues(String name, String weekDay, boolean isactive, String id){
        ContentValues contentValues = new ContentValues();

        contentValues.put(WorkoutEntry._ID, id);
        contentValues.put(WorkoutEntry.COLUMN_WORKOUT_NAME, name);
        contentValues.put(WorkoutEntry.COLUMN_WEEKDAY, String.valueOf(weekDay));
        contentValues.put(WorkoutEntry.COLUMN_ACTIVE, isactive);

        return contentValues;
    }
}
