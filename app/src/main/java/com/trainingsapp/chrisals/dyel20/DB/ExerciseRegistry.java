package com.trainingsapp.chrisals.dyel20.DB;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.trainingsapp.chrisals.dyel20.DB.DataBaseContract.*;
import com.trainingsapp.chrisals.dyel20.core.Exercise;
import com.trainingsapp.chrisals.dyel20.core.ExerciseNames;

import java.util.ArrayList;

/**
 * Created by chris.als on 21.02.17.
 */
public class ExerciseRegistry extends DataBaseAccessHandler {
    private SQLiteDatabase db;
    private String[] projection = {
            ExerciseEntry._ID,
            ExerciseEntry.COLUMN_EXERCISE_NAME,
            ExerciseEntry.COLUMN_EXERCISE_SETS,
            ExerciseEntry.COLUMN_EXERCISE_REPS,
            ExerciseEntry.COLUMN_EXERCISE_WEIGHT
    };

    public ExerciseRegistry(Context context){
        DataBase dataBase = new DataBase(context);
        this.db = dataBase.getWritableDatabase();
        if(this.getAllItems().size() == 0){
            this.createDefaultExercises();
        }

    }

    private void createDefaultExercises() {
        for(String name: ExerciseNames.EXERCISE_NAMES){
            Exercise ex = new Exercise(name, 0, 0,0);
            this.addExercise(ex);
        }
    }


    public void addExercise(Exercise exercise){
        long row = db.insert(ExerciseEntry.TABLE_NAME,
                null,
                this.getValues(exercise.getName(),
                        exercise.getSets(),
                        exercise.getReps(),
                        exercise.getWeight(),
                        exercise.getId()));
    }


    public void updateDBItem(Exercise exercise){
        ContentValues values = getValues(exercise.getName(),exercise.getReps(),exercise.getSets(),exercise.getWeight(),exercise.getId());
        String selection = ExerciseEntry._ID + " LIKE ?";
        String[] selectionsArgs = {exercise.getId()};
        db.update(ExerciseEntry.TABLE_NAME, values, selection, selectionsArgs);
    }

    public void removeItem(Exercise exercise){
        String selection = ExerciseEntry._ID + " LIKE ?";
        String exId = "";

        for(Exercise ex: getAllItems()){
            if(ex.getId().equals(exercise.getId())) {
                    exId = ex.getId();
                }
            }

        String[] selectionArgs = {exId};

        db.delete(ExerciseEntry.TABLE_NAME, selection, selectionArgs);
    }

    public Exercise getExerciseById(String id){
        Exercise exercise = null;
        for(Exercise ex:getAllItems()) {
            if (ex.getId().equals(id)) {
                exercise = ex;
            }
        }
        return exercise;
    }

    @Override
    public Cursor getCursor() {
        return db.query(ExerciseEntry.TABLE_NAME, projection, null, null, null, null, null, null);
    }

    @Override
    public ArrayList<Exercise> getAllItems(){
        Exercise ex;
        ArrayList<Exercise> exercises =  new ArrayList<>();
        Cursor cursor = this.getCursor();

        while(cursor.moveToNext()){
            ex = new Exercise(cursor.getString(cursor.getColumnIndexOrThrow(ExerciseEntry.COLUMN_EXERCISE_NAME)),
                            cursor.getInt(cursor.getColumnIndexOrThrow(ExerciseEntry.COLUMN_EXERCISE_SETS)),
                            cursor.getInt(cursor.getColumnIndexOrThrow(ExerciseEntry.COLUMN_EXERCISE_REPS)),
                            cursor.getDouble(cursor.getColumnIndexOrThrow(ExerciseEntry.COLUMN_EXERCISE_WEIGHT)));
            ex.setId(cursor.getString(cursor.getColumnIndexOrThrow(ExerciseEntry._ID)));
            exercises.add(ex);

        }
        return exercises;
    }




}
