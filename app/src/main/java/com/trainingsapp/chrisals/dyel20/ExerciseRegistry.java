package com.trainingsapp.chrisals.dyel20;

import android.content.Context;

import java.util.ArrayList;

/**
 * Created by chris.als on 07.02.17.
 */
public class ExerciseRegistry extends AbstractExerciseCollection {

    private ArrayList<Exercise> exercises =  new ArrayList<Exercise>();
    private static ExerciseRegistry exerciseRegistry;
    private Context context;

    public static ExerciseRegistry getInstance(Context context){
        if(exerciseRegistry == null){
            exerciseRegistry = new ExerciseRegistry(context);
        }
        return exerciseRegistry;
    }

    private ExerciseRegistry(Context context) {
        this.context = context;
    }

    public void createExercise(String name, int sets, int reps, double weight){
        Exercise ex = new Exercise(name, sets, reps, weight);
        this.exercises.add(ex);
    }

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
