package com.trainingsapp.chrisals.dyel20;

import java.util.ArrayList;

/**
 * Created by chris.als on 07.02.17.
 */
public class ExerciseRegistry extends AbstractExerciseCollection {
    private ArrayList<Exercise> exercises =  new ArrayList<Exercise>();;
    private static ExerciseRegistry exerciseRegistry;

    public static ExerciseRegistry getInstance(){
        if(exerciseRegistry == null){
            exerciseRegistry = new ExerciseRegistry();
        }
        return exerciseRegistry;
    }

    private ExerciseRegistry() {
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
