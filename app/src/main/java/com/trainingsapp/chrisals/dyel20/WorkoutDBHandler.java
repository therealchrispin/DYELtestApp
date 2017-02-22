package com.trainingsapp.chrisals.dyel20;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import com.trainingsapp.chrisals.dyel20.DataBaseContract.*;

import java.util.ArrayList;

/**
 * Created by chris.als on 21.02.17.
 */
public class WorkoutDBHandler extends DataBaseAccessHandler {
    private SQLiteDatabase db;
    private String[] projection = {
            WorkoutEntry._ID,
            WorkoutEntry.COLUMN_WORKOUT_NAME,
            WorkoutEntry.COLUMN_WEEKDAY,
            WorkoutEntry.COLUMN_ACTIVE,
    };

    public WorkoutDBHandler(Context context){
        DataBase dataBase = new DataBase(context);
        this.db = dataBase.getWritableDatabase();
    }


    public void insertWorkoutsToDB(ArrayList<Workout> workouts) {
        Workout workout;
        for(int i=0;i < workouts.size();i++){
            workout = workouts.get(i);
            long row = db.insert(DataBaseContract.WorkoutEntry.TABLE_NAME,
                    null,
                    this.getValues(workout.getName(),
                            workout.getWeekDay(),
                            workout.isActive()));
        }
    }

    @Override
    public Cursor getCursor() {
        return db.query(WorkoutEntry.TABLE_NAME, projection, null, null, null, null, null);
    }

    @Override
    public ArrayList getAllItems() {
        Workout wo;
        ArrayList workouts =  new ArrayList();
        Cursor cursor = this.getCursor();

        while(cursor.moveToNext()){
            String ws = cursor.getString(cursor.getColumnIndexOrThrow(WorkoutEntry.COLUMN_WEEKDAY));

            wo = new Workout(cursor.getString(cursor.getColumnIndexOrThrow(WorkoutEntry.COLUMN_WORKOUT_NAME)),
                            WeekDay.valueOf(ws));
            workouts.add(wo);

        }
        return workouts;
    }
}
