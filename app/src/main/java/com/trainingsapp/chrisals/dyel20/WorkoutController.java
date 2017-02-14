package com.trainingsapp.chrisals.dyel20;

import java.util.ArrayList;

/**
* Created by chris.als on 08.02.17.
        */
public class WorkoutController {
    private ArrayList<Workout> workouts;
    private ActiveWorkouts activeWorkouts;

    public Workout createWorkout(String name, WeekDay day){
        Workout workout = new Workout(name,day);
        this.activeWorkouts = new ActiveWorkouts();
        this.workouts.add(workout);
        this.activeWorkouts.registerObserver(workout);
        return workout;
    }

    public WorkoutController() {
        this.workouts = new ArrayList<Workout>();
    }



    public ArrayList<Workout> getAllWorkouts(){
        return this.workouts;
    }

    public Workout getWorkout(int index){
        return this.workouts.get(index);
    }

    public void removeWorkout(Workout workout){
        this.workouts.remove(workout);
    }

    public void changeWorkoutStatus(Workout workout){
        if(workout.isActive()){
            workout.setActive(false);
            this.activeWorkouts.removeObserver(workout);
        }else {
            workout.setActive(true);
            this.activeWorkouts.registerObserver(workout);
        }
    }

    public ArrayList<Workout> getAllActiveWorkouts(){
        ArrayList<Workout> activeWorkouts = new ArrayList<Workout>();
        for(Workout w:this.workouts){
            if(w.isActive()){
                activeWorkouts.add(w);
            }
        }
        return activeWorkouts;
    }


}
