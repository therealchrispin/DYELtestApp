package com.trainingsapp.chrisals.dyel20;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import com.trainingsapp.chrisals.dyel20.DataBaseContract.*;

import java.util.ArrayList;

/**
 * Created by chris.als on 21.02.17.
 */
public class WorkoutRegistry extends DataBaseAccessHandler {

    private ActiveWorkouts activeWorkouts;
    private WorkoutExerciseRegistry workoutExerciseRegistry;
    private SQLiteDatabase db;
    private String[] projection = {
            WorkoutEntry._ID,
            WorkoutEntry.COLUMN_WORKOUT_NAME,
            WorkoutEntry.COLUMN_WEEKDAY,
            WorkoutEntry.COLUMN_ACTIVE,
    };

    public WorkoutRegistry(Context context){
        DataBase dataBase = new DataBase(context);
        this.db = dataBase.getWritableDatabase();
        this.workoutExerciseRegistry = new WorkoutExerciseRegistry(context);
        activeWorkouts = new ActiveWorkouts();
    }

    public void addNewWorkout(Workout workout){
        this.activeWorkouts.registerObserver(workout);
        this.addWorkoutToDB(workout);
    }

    public void removeItem(int position){
        String selection = WorkoutEntry._ID + " LIKE ?";
        String[] selectionArgs = {String.valueOf(getAllItems().get(position).getId())};
        db.delete(WorkoutEntry.TABLE_NAME, selection, selectionArgs);
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

    private void addWorkoutToDB(Workout workout){
        long row = db.insert(WorkoutEntry.TABLE_NAME,
                null,
                this.getValues(workout.getName(),
                                arrayListToString(workout.getWeekDay()),
                                workout.isActive(),
                                workout.getId()));
    }

    public ArrayList<Workout> getAllActiveWorkouts(){
        ArrayList<Workout> activeWorkouts = new ArrayList<Workout>();
        for(Workout w:this.getAllItems()){
            if(w.isActive()){
                activeWorkouts.add(w);
            }
        }
        return activeWorkouts;
    }

    @Override
    public Cursor getCursor() {
        return db.query(WorkoutEntry.TABLE_NAME, projection, null, null, null, null, null);
    }

    @Override
    public ArrayList<Workout> getAllItems() {
        Workout wo;
        ArrayList<Workout> workouts =  new ArrayList<>();
        Cursor cursor = this.getCursor();

        while(cursor.moveToNext()){
            String ws = cursor.getString(cursor.getColumnIndexOrThrow(WorkoutEntry.COLUMN_WEEKDAY));

            wo = new Workout(cursor.getString(cursor.getColumnIndexOrThrow(WorkoutEntry.COLUMN_WORKOUT_NAME)),
                            this.stringToWeekdayArrayList(ws));

            wo.setId(cursor.getString(cursor.getColumnIndexOrThrow(WorkoutEntry._ID)));
            wo.setExercises(getExerciseListFromWorkoutId(wo.getId()));
            workouts.add(wo);

        }
        return workouts;
    }

    private ArrayList<Exercise> getExerciseListFromWorkoutId(String id){
        return workoutExerciseRegistry.getExerciseListByWorkoutId(id);
    }



    private ArrayList<WeekDay> stringToWeekdayArrayList(String weekString) {
        String[] stringweek =  weekString.split(",");

        ArrayList<WeekDay> week = new ArrayList<>();

        for (int i=0;i<stringweek.length;i++){
            week.add(WeekDay.valueOf(stringweek[i]));
        }

        return week;

    }

    private String arrayListToString(ArrayList<WeekDay> list){
        String weekdays = "";
        for(WeekDay day:list){
            weekdays = weekdays + day + ",";
        }
        return weekdays;
    }

 }
