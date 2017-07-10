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

    public static synchronized DBRegistryFacade getInstance(Context context) {
        if(instance == null){
            instance = new DBRegistryFacade(context);
        }
        return instance;
    }

    private void startRegistries(){
        exerciseRegistry = new ExerciseRegistry(context);
        workoutRegistry = new WorkoutRegistry(context);
        workExRegistry = new WorkoutExerciseRegistry(context);
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


    public void changeWorkoutStatus(Workout workout ){
        workout.setActive(true);
        this.updateItem(workout);

        for(Workout wo: this.getAllWorkouts()){
            if(!wo.getId().equals(workout.getId())){
                wo.setActive(false);
                this.updateItem(wo);
            }
        }
    }


    public ArrayList<Exercise> getExercisesByWorkoutID(Workout workout){
        return this.workExRegistry.getExerciseListByWorkoutId(workout.getId());
    }
}
