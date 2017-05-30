package com.trainingsapp.chrisals.dyel20.Activities;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.trainingsapp.chrisals.dyel20.DataBase.ExerciseRegistry;
import com.trainingsapp.chrisals.dyel20.core.Exercise;
import com.trainingsapp.chrisals.dyel20.core.GlobalConstants;
import com.trainingsapp.chrisals.dyel20.R;

/**
 * Created by Alsberg on 05.03.17.
 */

public class ExerciseDetailViewFragment extends Fragment {
    public final static String EXERCISE_POSITION = "position";
    public final static String EXERCISE_ID = "id";
    public final static String WORKOUT_ID = "workout id";
    public String workoutId;
    public int currentPosition = -1;
    private Exercise exercise;
    private Button btn;
    private EditText exerciseName;
    private EditText exerciseSets;
    private EditText exerciseReps;
    private EditText exerciseWeight;
    private ExerciseRegistry exReg;

    onSaveExerciseListener onSaveExerciseListener;

    public interface onSaveExerciseListener {
        public void addExerciseToWorkoutList(String id);
        public void setWorkoutId(String workoutId);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
        if(savedInstanceState != null){
            currentPosition = savedInstanceState.getInt(EXERCISE_POSITION);
        }

        //Transition slideTransition = TransitionInflater.from(getActivity()).inflateTransition(R.transition.exercise_detail_enter);
        //slideTransition.setInterpolator(AnimationUtils.loadInterpolator(getActivity(), android.R.interpolator.linear_out_slow_in));
        //setEnterTransition(slideTransition);

        //Slide slide = new Slide(Gravity.BOTTOM);
        //slide.addTarget(R.id.exercise_detail_view);
        //slide.setInterpolator(AnimationUtils.loadInterpolator(getActivity(), android.R.interpolator.linear_out_slow_in));
        //slide.setDuration(5000);
        //setEnterTransition(slide);
        //
              return inflater.inflate(R.layout.activity_exercise_detail, container, false);
    }

    @Override
    public void onStart() {
        super.onStart();

        // During startup, check if there are arguments passed to the fragment.
        // onStart is a good place to do this because the layout has already been
        // applied to the fragment at this point so we can safely call the method
        // below that sets the article text.
        Bundle args = getArguments();
        if (args != null) {
            // Set article based on argument passed in
            updateExerciseDetailView(args.getString(EXERCISE_ID), args.getInt(EXERCISE_POSITION),args.getString(WORKOUT_ID));
        } else if (currentPosition != -1) {
            // Set article based on saved instance state defined during onCreateView
            updateExerciseDetailView(exercise.getId(),currentPosition, workoutId);
        }
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);

        // This makes sure that the container activity has implemented
        // the callback interface. If not, it throws an exception.
        try {
            onSaveExerciseListener = (onSaveExerciseListener) activity;
        } catch (ClassCastException e) {
            throw new ClassCastException(activity.toString()
                    + " must implement onSaveExerciseListener");
        }
    }

    public void updateExerciseDetailView(String id, final int position, String workoutId) {
        this.btn = (Button) getActivity().findViewById(R.id.exercise_dateil_save_button);

        this.workoutId = workoutId;

        this.btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                saveExercise();
            }

        });
        exReg = new ExerciseRegistry(getActivity());

        this.exercise = exReg.getExerciseById(id);

        exerciseName = (EditText) getActivity().findViewById(R.id.edit_exercise_name);
        exerciseSets = (EditText) getActivity().findViewById(R.id.edit_exercise_sets);
        exerciseReps = (EditText) getActivity().findViewById(R.id.edit_exercise_reps);
        exerciseWeight = (EditText) getActivity().findViewById(R.id.edit_exercise_weight);


        exerciseName.setText(this.exercise.getName());
        exerciseSets.setText(String.valueOf(this.exercise.getSets()));
        exerciseReps.setText(String.valueOf(this.exercise.getReps()));
        exerciseWeight.setText(String.valueOf(this.exercise.getWeight()));

        currentPosition = position;

    }


    private void saveExercise() {
        this.exercise.setName(exerciseName.getText().toString());
        this.exercise.setSets(Integer.valueOf(exerciseSets.getText().toString()));
        this.exercise.setReps(Integer.valueOf(exerciseReps.getText().toString()));
        this.exercise.setWeight(Double.valueOf(exerciseWeight.getText().toString()));

        exReg.updateDBItem(this.exercise);


        addExerciseToWorkout(this.exercise.getId());

        Intent intent = new Intent(getActivity(),ExerciseSelectorActivity.class);
        intent.putExtra(GlobalConstants.WORKOUT_ID, workoutId);
        startActivity(intent);

    }


    public void addExerciseToWorkout(String id){
        onSaveExerciseListener.addExerciseToWorkoutList(id);
    }

    public void setWorkoutId(String workoutId){
        onSaveExerciseListener.setWorkoutId(workoutId);
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);

        // Save the current article selection in case we need to recreate the fragment
        outState.putInt(EXERCISE_POSITION, currentPosition);
    }



}
