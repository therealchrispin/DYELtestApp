package com.trainingsapp.chrisals.dyel20;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import com.trainingsapp.chrisals.dyel20.DataBaseContract.*;
import java.util.ArrayList;

/**
 * Created by chris.als on 21.02.17.
 */
public class DataBaseAccessController {
    SQLiteDatabase db;

    public DataBaseAccessController(Context context){
       DataBase dataBase = new DataBase(context);
        this.db = dataBase.getWritableDatabase();
    }

    public void insertExerciseToDB(ArrayList<Exercise> exercises) {
        Exercise exercise;
        for(int i=0;i < exercises.size();i++) {
            exercise = exercises.get(i);
            long row = db.insert(ExerciseEntry.TABLE_NAME,
                    null,
                    this.getExerciseValues(exercise.getName(),
                            exercise.getSets(),
                            exercise.getReps(),
                            exercise.getWeight()));
        }
    }

    public void insertWorkoutsToDB(ArrayList<Workout> workouts){
        Workout workout;
        for(int i=0;i < workouts.size();i++){
            workout = workouts.get(i);
            long row = db.insert(WorkoutEntry.TABLE_NAME,
                                null,
                                this.getWorkoutValues(workout.getName(),
                                                    workout.getWeekDay(),
                                                    workout.isActive()));
        }
    }

    public ContentValues getExerciseValues(String name, int sets, int reps, double weight){
        ContentValues contentValues = new ContentValues();

        contentValues.put(ExerciseEntry.COLUMN_EXERCISE_NAME, name);
        contentValues.put(ExerciseEntry.COLUMN_EXERCISE_SETS, String.valueOf(sets));
        contentValues.put(ExerciseEntry.COLUMN_EXERCISE_REPS, String.valueOf(reps));
        contentValues.put(ExerciseEntry.COLUMN_EXERCISE_WEIGHT, String.valueOf(weight));

        return contentValues;

    }

    public ContentValues getWorkoutValues(String name, WeekDay weekDay, boolean isactive){
        ContentValues contentValues = new ContentValues();

        contentValues.put(WorkoutEntry.COLUMN_WORKOUT_NAME, name);
        contentValues.put(WorkoutEntry.COLUMN_WEEKDAY, String.valueOf(weekDay));
        contentValues.put(WorkoutEntry.COLUMN_ACTIVE, isactive);

        return contentValues;
    }
}
