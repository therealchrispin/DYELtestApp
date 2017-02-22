package com.trainingsapp.chrisals.dyel20;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class ExerciseCreatorActivity extends AppCompatActivity {
    private ExerciseRegistry exerciseRegistry;
    private String exerciseName;
    private String exerciseReps;
    private String exerciseSets;
    private String exerciseWeight;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_exercise_creator);

        this.exerciseRegistry = new ExerciseRegistry(this);
    }



    public void createExercise(View view){
        this.getEditTextContent();

        Exercise exercise = new Exercise(this.exerciseName,
                                            Integer.valueOf(this.exerciseSets),
                                            Integer.valueOf(this.exerciseReps),
                                            Double.valueOf(this.exerciseWeight));

        exerciseRegistry.addExercise(exercise);

        Intent intent = new Intent(this, ExerciseRegistryActivity.class);
        startActivity(intent);
    }

    public void getEditTextContent(){

        EditText name = (EditText) findViewById(R.id.ex_name);
        EditText sets = (EditText) findViewById(R.id.ex_sets);
        EditText reps = (EditText) findViewById(R.id.ex_reps);
        EditText weight = (EditText) findViewById(R.id.ex_weight);

        this.exerciseName = name.getText().toString();
        this.exerciseSets = sets.getText().toString();
        this.exerciseReps = reps.getText().toString();
        this.exerciseWeight = weight.getText().toString();
    }

}
