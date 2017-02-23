package com.trainingsapp.chrisals.dyel20;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import com.trainingsapp.chrisals.dyel20.DataBaseContract.*;

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

        this.addExercise(new Exercise("d",1,1,1));
        this.addExercise(new Exercise("d",1,1,1));

    }

    public void addExercise(Exercise exercise){
        long row = db.insert(ExerciseEntry.TABLE_NAME,
                null,
                this.getValues(exercise.getName(),
                        exercise.getSets(),
                        exercise.getReps(),
                        exercise.getWeight()));
    }

    public void removeItem(int position){
        String selection = ExerciseEntry._ID + " LIKE ?";
        String[] selectionArgs = {String.valueOf(getAllItems().get(position).getId())};
        db.delete(ExerciseEntry.TABLE_NAME, selection, selectionArgs);
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
            ex.setId(cursor.getInt(cursor.getColumnIndexOrThrow(ExerciseEntry._ID)));
            exercises.add(ex);

        }
        return exercises;
    }


}
