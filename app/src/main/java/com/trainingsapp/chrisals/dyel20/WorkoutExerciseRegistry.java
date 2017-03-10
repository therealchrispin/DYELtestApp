package com.trainingsapp.chrisals.dyel20;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import com.trainingsapp.chrisals.dyel20.DataBaseContract.*;

import java.util.ArrayList;

/**
 * Created by chris.als on 10.03.17.
 */
// TODO: 10.03.17 Rename class
public class WorkoutExerciseRegistry {
    private ExerciseRegistry exerciseRegistry;
    private SQLiteDatabase db;
    private String[] projection = {
            ExerciseWorkoutEntry._ID,
            ExerciseWorkoutEntry.EXERCISE_ID,
            ExerciseWorkoutEntry.WORKOUT_ID
    };

    public WorkoutExerciseRegistry(Context context) {
        DataBase dataBase = new DataBase(context);
        this.exerciseRegistry = new ExerciseRegistry(context);
        this.db = dataBase.getWritableDatabase();
    }

    public void addExerciseToWorkout(String exerciseID, String workoutId){
        ContentValues values = new ContentValues();

        values.put(ExerciseWorkoutEntry.EXERCISE_ID, exerciseID);
        values.put(ExerciseWorkoutEntry.WORKOUT_ID, workoutId);

        long row = db.insert(ExerciseWorkoutEntry.TABLE_NAME, null, values);
    }

    private Cursor getCursorByWorkoutId(String workoutId){
        String selection = ExerciseWorkoutEntry.WORKOUT_ID + " LIKE ?";
        String selectionArgs[] = {workoutId};

        return db.query(ExerciseWorkoutEntry.TABLE_NAME,
                projection,
                selection,
                selectionArgs,
                null,
                null,
                null);
    }

    public ArrayList<Exercise> getExerciseByWorkoutId(String  workoutId){
        Cursor cursor = getCursorByWorkoutId(workoutId);

        ArrayList<Exercise> exercises = new ArrayList<>();

        while(cursor.moveToNext()){
            String exId = cursor.getString(cursor.getColumnIndexOrThrow(ExerciseWorkoutEntry.EXERCISE_ID));
            exercises.add(exerciseRegistry.getExerciseById(exId));
        }
        return exercises;
    }
}
