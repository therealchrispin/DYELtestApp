package com.trainingsapp.chrisals.dyel20.DB;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.trainingsapp.chrisals.dyel20.DB.DataBaseContract.*;
import com.trainingsapp.chrisals.dyel20.core.Exercise;
import com.trainingsapp.chrisals.dyel20.core.Workout;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

/**
 * Created by chris.als on 10.03.17.
 */
public class WorkoutExerciseRegistry {
    private ExerciseRegistry exerciseRegistry;
    private SQLiteDatabase db;
    private String[] projection = {
            ExerciseWorkoutEntry._ID,
            ExerciseWorkoutEntry.EXERCISE_ID,
            ExerciseWorkoutEntry.WORKOUT_ID,
            ExerciseWorkoutEntry.COLUMN_EXERCISE_ORDER
    };

    private String workoutID;

    public WorkoutExerciseRegistry(Context context, String workoutId) {
        DataBase dataBase = new DataBase(context);
        this.exerciseRegistry = new ExerciseRegistry(context);
        this.db = dataBase.getWritableDatabase();
        this.workoutID = workoutId;
    }

    public WorkoutExerciseRegistry(Context context) {
        DataBase dataBase = new DataBase(context);
        this.exerciseRegistry = new ExerciseRegistry(context);
        this.db = dataBase.getWritableDatabase();
    }

    public void saveExerciseList(ArrayList<Exercise> exercises){
        for(int i=0;i<exercises.size();i++){
            this.addExerciseToWorkout(exercises.get(i).getId(),i);
        }
    }

    public void addExerciseToWorkout(String exerciseID, int exerciseOrder){
        ContentValues values = new ContentValues();

        values.put(ExerciseWorkoutEntry.EXERCISE_ID, exerciseID);
        values.put(ExerciseWorkoutEntry.WORKOUT_ID, this.workoutID);
        values.put(ExerciseWorkoutEntry.COLUMN_EXERCISE_ORDER, exerciseOrder);

        long row = db.insert(ExerciseWorkoutEntry.TABLE_NAME, null, values);
    }


    public void removeRow(Workout workout, Exercise exercise){
            String selection = ExerciseWorkoutEntry.WORKOUT_ID + " LIKE ? AND " + ExerciseWorkoutEntry.EXERCISE_ID + " LIKE ?" ;

            String[] selectionArgs = {workout.getId(), exercise.getId()};

            db.delete(ExerciseWorkoutEntry.TABLE_NAME, selection, selectionArgs);
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

    public ArrayList<Exercise> getExerciseListByWorkoutId(String  workoutId){
        Cursor cursor = getCursorByWorkoutId(workoutId);


        ArrayList<Exercise> exerciseList = new ArrayList<>();


        while(cursor.moveToNext()){
            String exId = cursor.getString(cursor.getColumnIndexOrThrow(ExerciseWorkoutEntry.EXERCISE_ID));
            int exOrd = cursor.getInt(cursor.getColumnIndexOrThrow(ExerciseWorkoutEntry.COLUMN_EXERCISE_ORDER));

            Exercise exercise = exerciseRegistry.getExerciseById(exId);
            exercise.setOrder(exOrd);

            exerciseList.add(exercise);
            this.sorteExerciseListByOrder(exerciseList);


        }
        return exerciseList;
    }

    public void sorteExerciseListByOrder(ArrayList<Exercise> exercises){
        Collections.sort(exercises, new Comparator<Exercise>() {
            @Override
            public int compare(Exercise lhs, Exercise rhs) {
                return lhs.getOrder() - rhs.getOrder();
            }
        });
    }
}
