package com.trainingsapp.chrisals.dyel20.DataBase;

import android.provider.BaseColumns;

/**
 * Created by chris.als on 21.02.17.
 */
public final class DataBaseContract {

    private DataBaseContract(){}


    public static class ExerciseEntry implements BaseColumns {
        public static final String TABLE_NAME = "exercises";
        public static final String COLUMN_EXERCISE_NAME = "name";
        public static final String COLUMN_EXERCISE_SETS = "sets";
        public static final String COLUMN_EXERCISE_REPS = "reps";
        public static final String COLUMN_EXERCISE_WEIGHT = "weight";

        public static final String CREATE_EXERCISE_ENTRY = "CREATE TABLE " +
                TABLE_NAME + " (" +
                _ID + " TEXT," +
                COLUMN_EXERCISE_NAME + " TEXT," +
                COLUMN_EXERCISE_SETS + " TEXT," +
                COLUMN_EXERCISE_REPS + " TEXT," +
                COLUMN_EXERCISE_WEIGHT + " TEXT)";

        public static final String SQL_DELETE_EXERCISE_ENTRY = "DROP TABLE IF EXISTS " + TABLE_NAME;

    }

    public static class WorkoutEntry implements BaseColumns{

        public static final String TABLE_NAME = "workouts";
        public static final String COLUMN_WORKOUT_NAME = "name";
        public static final String COLUMN_WEEKDAY= "weekday";
        public static final String COLUMN_ACTIVE = "active";

        public static final String CREATE_WORKOUT_ENTRY = "CREATE TABLE " +
                TABLE_NAME + " (" +
                _ID + " TEXT," +
                COLUMN_WORKOUT_NAME + " TEXT," +
                COLUMN_WEEKDAY + " TEXT," +
                COLUMN_ACTIVE + " INTEGER)";

        public static final String SQL_DELETE_WORKOUT_ENTRY = "DROP TABLE IF EXISTS " + TABLE_NAME;
    }

    public static class ExerciseWorkoutEntry implements BaseColumns {
        public static final String TABLE_NAME = "exercise_workout";
        public static final String EXERCISE_ID = "exericse_id";
        public static final String WORKOUT_ID = "workout_id";
        public static final String COLUMN_EXERCISE_ORDER = "sequence";


        public static final String CREATE_EXERCISE_WORKOUT_ENTRY = "CREATE TABLE " +
                TABLE_NAME + " (" +
                _ID + " TEXT," +
                COLUMN_EXERCISE_ORDER + " INTEGER, " +
                EXERCISE_ID + " TEXT, " +
                WORKOUT_ID + " TEXT, " +
                "FOREIGN KEY ("+EXERCISE_ID+") REFERENCES "+ExerciseEntry.TABLE_NAME+"("+ExerciseEntry._ID+"), " +
                "FOREIGN KEY ("+WORKOUT_ID+") REFERENCES "+WorkoutEntry.TABLE_NAME+"("+WorkoutEntry._ID+")); ";


        public static final String SQL_DELETE_Ex_Wo_Entry = "DROP TABLE IF EXISTS " + TABLE_NAME;
    }



}
