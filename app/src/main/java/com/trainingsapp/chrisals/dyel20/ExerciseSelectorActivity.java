package com.trainingsapp.chrisals.dyel20;

import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentTransaction;
import android.os.Bundle;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class ExerciseSelectorActivity extends FragmentActivity
        implements ExerciseTitleListFragment.onExerciseTitleSelectedListener,
                    ExerciseDetailViewFragment.onSaveExerciseListener{

    private String workoutID;
    protected Workout workout;
    protected ExerciseRegistry registry;
    protected WorkoutRegistry workoutRegistry;
    protected WorkoutExerciseRegistry workoutExerciseRegistry;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_exercise_selector);

        this.workoutID = getIntent().getStringExtra(GlobalConstants.WORKOUT_ID);

        if(findViewById(R.id.exercise_title_container) != null){

            this.registry = new ExerciseRegistry(this);
            this.workoutRegistry = new WorkoutRegistry(this);

            ExerciseTitleListFragment firstFragment = new ExerciseTitleListFragment();

            this.workout = workoutRegistry.getWorkoutById(workoutID);

            firstFragment.setArguments(getIntent().getExtras());

            getSupportFragmentManager().beginTransaction().add(R.id.exercise_title_container, firstFragment).commit();
        }
    }

    @Override
    public void onExerciseSelected(String id, int position) {
        ExerciseDetailViewFragment exerciseDetailViewFragment = new ExerciseDetailViewFragment();

        Bundle args = new Bundle();

        args.putInt(exerciseDetailViewFragment.EXERCISE_POSITION, position);
        args.putString(exerciseDetailViewFragment.EXERCISE_ID, id);
        args.putString(exerciseDetailViewFragment.WORKOUT_ID, this.workoutID);

        exerciseDetailViewFragment.setArguments(args);

        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();

        transaction.replace(R.id.exercise_title_container, exerciseDetailViewFragment);
        //transaction.addToBackStack(null);

        transaction.commit();


    }

    @Override
    public void addExerciseToWorkoutList(String exerciseId) {
        this.workoutExerciseRegistry = new WorkoutExerciseRegistry(this);
        this.workoutExerciseRegistry.addExerciseToWorkout(exerciseId, this.workoutID);
    }

    @Override
    public void setWorkoutId(String workoutId) {
        this.workoutID = workoutId;
    }
}
