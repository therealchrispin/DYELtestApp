package com.trainingsapp.chrisals.dyel20;

import java.util.ArrayList;

/**
 * Created by chris.als on 08.02.17.
 */
public abstract class AbstractExerciseCollection {
    private ArrayList<Exercise> exercises = new ArrayList<Exercise>();

    public void remove(int index){
        this.exercises.remove(index);
    }

    public Exercise getItem(int exerciseIndex){
        return this.exercises.get(exerciseIndex);
    }

    public ArrayList getAll(){
        return this.exercises;
    }
}
