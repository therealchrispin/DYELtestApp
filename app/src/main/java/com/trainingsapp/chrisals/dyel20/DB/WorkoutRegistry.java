package com.trainingsapp.chrisals.dyel20.DB;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.trainingsapp.chrisals.dyel20.DB.DataBaseContract.WorkoutEntry;
import com.trainingsapp.chrisals.dyel20.core.Exercise;
import com.trainingsapp.chrisals.dyel20.core.WeekDay;
import com.trainingsapp.chrisals.dyel20.core.Workout;

import java.util.ArrayList;

/**
 * Created by chris.als on 21.02.17.
 */
public class WorkoutRegistry extends DataBaseAccessHandler {
    // TODO Remove active Workouts ?om
    private WorkoutExerciseRegistry workoutExerciseRegistry;
    private SQLiteDatabase db;
    private String[] projection = {
            WorkoutEntry._ID,
            WorkoutEntry.COLUMN_WORKOUT_NAME,
            WorkoutEntry.COLUMN_WEEKDAY,
            WorkoutEntry.COLUMN_ACTIVE,
    };

    public WorkoutRegistry(Context context) {
        DataBase dataBase = new DataBase(context);
        this.db = dataBase.getWritableDatabase();
        this.workoutExerciseRegistry = new WorkoutExerciseRegistry(context);
    }


    public void addNewWorkout(Workout workout) {
        this.addWorkoutToDB(workout);
    }

    private void addWorkoutToDB(Workout workout) {
        long row = db.insert(WorkoutEntry.TABLE_NAME,
                null,
                this.getValues(workout.getName(),
                        workout.getWeekString(),
                        workout.isActive(),
                        workout.getId()));
    }

    public void updateWorkout(Workout workout) {
        ContentValues contentValues = this.getValues(workout.getName(),
                workout.getWeekString(),
                workout.isActive(),
                workout.getId());
        String selection = WorkoutEntry._ID + " LIKE ?";
        String[] selectionArgs = {workout.getId()};

        int count = db.update(WorkoutEntry.TABLE_NAME, contentValues, selection, selectionArgs);

    }


    public Workout getWorkoutById(String id) {
        Workout workout = null;
        for (Workout wo : getAllItems()) {
            if (wo.getId().equals(id)) {
                workout = wo;
            }
        }
        return workout;
    }


    @Override
    public Cursor getCursor() {
        return db.query(WorkoutEntry.TABLE_NAME, projection, null, null, null, null, null);
    }

    @Override
    public ArrayList<Workout> getAllItems() {
        Workout wo;
        ArrayList<Workout> workouts = new ArrayList<>();
        Cursor cursor = this.getCursor();

        while (cursor.moveToNext()) {
            String ws = cursor.getString(cursor.getColumnIndexOrThrow(WorkoutEntry.COLUMN_WEEKDAY));

            wo = new Workout(cursor.getString(cursor.getColumnIndexOrThrow(WorkoutEntry.COLUMN_WORKOUT_NAME)),
                    this.stringToWeekdayArrayList(ws));

            wo.setId(cursor.getString(cursor.getColumnIndexOrThrow(WorkoutEntry._ID)));
            wo.setActive(cursor.getInt(cursor.getColumnIndexOrThrow(WorkoutEntry.COLUMN_ACTIVE)) != 0);

            workouts.add(wo);

        }
        return workouts;
    }

    // TODO remove Exercise from Workout
    private void removeExerciseFromWorkout(Exercise exercise){
        /////////
    }


    private ArrayList<WeekDay> stringToWeekdayArrayList(String weekString) {
        String[] stringweek = weekString.split(",");

        ArrayList<WeekDay> week = new ArrayList<>();

        if(!stringweek[0].equals("")){
            for (int i = 0; i < stringweek.length; i++) {
                week.add(WeekDay.valueOf(stringweek[i]));
            }
        }

        return week;

    }

    private String arrayListToString(ArrayList<WeekDay> list) {
        String weekdays = "";
        for (WeekDay day : list) {
            weekdays = weekdays + day + ",";
        }
        return weekdays;
    }

    public void removeItem(Workout workout) {
        String selection = WorkoutEntry._ID + " LIKE ?";
        String[] selectionArgs = {workout.getId()};
        db.delete(WorkoutEntry.TABLE_NAME, selection, selectionArgs);
    }

}
