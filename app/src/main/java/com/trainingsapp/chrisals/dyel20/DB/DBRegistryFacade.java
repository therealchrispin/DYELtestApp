package com.trainingsapp.chrisals.dyel20.DB;

import android.content.Context;
import android.provider.SyncStateContract;

import com.trainingsapp.chrisals.dyel20.core.Exercise;
import com.trainingsapp.chrisals.dyel20.core.Workout;

import java.util.ArrayList;

/**
 * Created by chris.als on 02.06.17.
 */

public class DBRegistryFacade {
    private static DBRegistryFacade instance = null;


    private Context context;
    private ExerciseRegistry exerciseRegistry;
    private WorkoutRegistry workoutRegistry;
    private WorkoutExerciseRegistry workExRegistry;

    private DBRegistryFacade(Context context){
        this.context = context;

        this.startRegistries();
    }

    synchronized public static DBRegistryFacade getInstance(Context context) {
        if(instance == null){
            instance = new DBRegistryFacade(context);
        }
        return instance;
    }

    private void startRegistries(){
        startExerciseRegistry();
        startWorkoutRegistry();
        startExWoRegistry();
    }

    private void startExerciseRegistry(){
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                exerciseRegistry = new ExerciseRegistry(context);
            }
        });

        thread.start();
    }

    private void startWorkoutRegistry(){
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                workoutRegistry = new WorkoutRegistry(context);
            }
        });
        thread.start();
    }

    private void startExWoRegistry(){
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                workExRegistry = new WorkoutExerciseRegistry(context);
            }
        });
        thread.start();
    }




    public void addItem(Exercise exercise){
        this.exerciseRegistry.addExercise(exercise);
    }

    public void addItem(Workout workout){
        this.workoutRegistry.addNewWorkout(workout);
    }

    public void updateItem(Exercise exercise){
        this.exerciseRegistry.updateDBItem(exercise);
    }

    public void updateItem(Workout workout){
        this.workoutRegistry.updateWorkout(workout);
    }

    public void removeItem(Exercise exercise){
        this.exerciseRegistry.removeItem(exercise);
    }

    public void removeItem(Workout workout){
        this.workoutRegistry.removeItem(workout);
    }

    public Exercise getExerciseByID(String id){
        return this.exerciseRegistry.getExerciseById(id);
    }

    public Workout getWorkoutByID(String id){
        return this.workoutRegistry.getWorkoutById(id);
    }

    public void removeExerciseFromWorkout(Workout workout,Exercise exercise){
        this.workExRegistry.removeRow(workout, exercise);
    }

    public ArrayList<Exercise> getAllExercises() {
        return this.exerciseRegistry.getAllItems();
    }

    public ArrayList<Workout> getAllWorkouts() {
        return this.workoutRegistry.getAllItems();
    }

    public Workout getActiveWorkout(){
        Workout workout = null;
        for(Workout wo: this.getAllWorkouts()){
            if(wo.isActive()){
                workout = wo;
            }
        }
        return workout;
    }

    public void setWorkoutActive(Workout workout){
        workout.setActive(true);
        this.updateItem(workout);

        for(Workout wo: this.getAllWorkouts()){
            if(!wo.getId().equals(workout.getId())){
                wo.setActive(false);
                this.updateItem(wo);
            }
        }
    }
}
