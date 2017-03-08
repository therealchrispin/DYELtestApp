package com.trainingsapp.chrisals.dyel20;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.EditorInfo;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

/**
 * Created by Alsberg on 05.03.17.
 */

public class ExerciseDetailViewFragment extends Fragment {
    public final static String EXERCISE_POSITION = "position";
    public int currentPosition = -1;
    private Exercise exercise;
    private Button btn;
    private EditText exerciseName;
    private EditText exerciseSets;
    private EditText exerciseReps;
    private EditText exerciseWeight;
    private ExerciseRegistry exReg;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
        if(savedInstanceState != null){
            currentPosition = savedInstanceState.getInt(EXERCISE_POSITION);
        }

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
            updateExerciseDetailView(args.getInt(EXERCISE_POSITION));
        } else if (currentPosition != -1) {
            // Set article based on saved instance state defined during onCreateView
            updateExerciseDetailView(currentPosition);
        }
    }

    public void updateExerciseDetailView(final int position) {
        this.btn = (Button) getActivity().findViewById(R.id.exercise_dateil_save_button);

        this.btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                saveExercise(position);
            }

        });
        exReg = new ExerciseRegistry(getActivity());

        this.exercise = exReg.getAllItems().get(position);

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


    private void saveExercise(int position) {
        exReg.getAllItems().get(position).setName(exerciseName.getText().toString());
        exReg.getAllItems().get(position).setSets(Integer.valueOf(exerciseSets.getText().toString()));
        exReg.getAllItems().get(position).setReps(Integer.valueOf(exerciseReps.getText().toString()));
        exReg.getAllItems().get(position).setWeight(Double.valueOf(exerciseWeight.getText().toString()));
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);

        // Save the current article selection in case we need to recreate the fragment
        outState.putInt(EXERCISE_POSITION, currentPosition);
    }

}
