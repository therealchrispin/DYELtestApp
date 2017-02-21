package com.trainingsapp.chrisals.dyel20;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import com.trainingsapp.chrisals.dyel20.DataBaseContract.*;

import java.util.ArrayList;

/**
 * Created by chris.als on 21.02.17.
 */
public class ExerciseDBHandler extends DataBaseAccessHandler {
    private SQLiteDatabase db;
    private String[] projection = {
            ExerciseEntry._ID,
            ExerciseEntry.COLUMN_EXERCISE_NAME,
            ExerciseEntry.COLUMN_EXERCISE_SETS,
            ExerciseEntry.COLUMN_EXERCISE_REPS,
            ExerciseEntry.COLUMN_EXERCISE_WEIGHT
    };

    public ExerciseDBHandler(Context context){
        DataBase dataBase = new DataBase(context);
        this.db = dataBase.getWritableDatabase();
    }



    public void insertExerciseToDB(ArrayList<Exercise> exercises) {
        Exercise exercise;
        for(int i=0;i < exercises.size();i++) {
            exercise = exercises.get(i);
            long row = db.insert(ExerciseEntry.TABLE_NAME,
                    null,
                    this.getValues(exercise.getName(),
                                    exercise.getSets(),
                                    exercise.getReps(),
                                    exercise.getWeight()));
            System.out.println(exercise.getName());
        }
    }

    @Override
    public Cursor getCursor() {
        return db.query(ExerciseEntry.TABLE_NAME, projection, null, null, null, null, null, null);
    }

    @Override
    public ArrayList<Exercise> getAllItem(){
        Exercise ex;
        ArrayList<Exercise> exercises =  new ArrayList<Exercise>();
        Cursor cursor = this.getCursor();

        while(cursor.moveToNext()){
            ex = new Exercise(cursor.getString(cursor.getColumnIndexOrThrow(ExerciseEntry.COLUMN_EXERCISE_NAME)),
                            cursor.getInt(cursor.getColumnIndexOrThrow(ExerciseEntry.COLUMN_EXERCISE_SETS)),
                            cursor.getInt(cursor.getColumnIndexOrThrow(ExerciseEntry.COLUMN_EXERCISE_REPS)),
                            cursor.getDouble(cursor.getColumnIndexOrThrow(ExerciseEntry.COLUMN_EXERCISE_WEIGHT)));
            exercises.add(ex);

        }
        return exercises;
    }


}
