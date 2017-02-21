package com.trainingsapp.chrisals.dyel20;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.trainingsapp.chrisals.dyel20.DataBaseContract.*;
import java.util.ArrayList;
import java.util.Currency;

/**
 * Created by chris.als on 21.02.17.
 */
public abstract class DataBaseAccessHandler {
    SQLiteDatabase db;

    public abstract ArrayList getAllItem();

    public abstract Cursor getCursor();

    public ContentValues getValues(String name, int sets, int reps, double weight){
        ContentValues contentValues = new ContentValues();

        contentValues.put(ExerciseEntry.COLUMN_EXERCISE_NAME, name);
        contentValues.put(ExerciseEntry.COLUMN_EXERCISE_SETS, String.valueOf(sets));
        contentValues.put(ExerciseEntry.COLUMN_EXERCISE_REPS, String.valueOf(reps));
        contentValues.put(ExerciseEntry.COLUMN_EXERCISE_WEIGHT, String.valueOf(weight));

        return contentValues;

    }

    public ContentValues getValues(String name, WeekDay weekDay, boolean isactive){
        ContentValues contentValues = new ContentValues();

        contentValues.put(WorkoutEntry.COLUMN_WORKOUT_NAME, name);
        contentValues.put(WorkoutEntry.COLUMN_WEEKDAY, String.valueOf(weekDay));
        contentValues.put(WorkoutEntry.COLUMN_ACTIVE, isactive);

        return contentValues;
    }
}
