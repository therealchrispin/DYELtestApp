package com.trainingsapp.chrisals.dyel20;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.trainingsapp.chrisals.dyel20.R;

public class ExerciseDetailActivity extends AppCompatActivity {
    private Exercise exercise;
    private ExerciseRegistry exerciseRegistry;
    private EditText exerciseName;
    private EditText exerciseSets;
    private EditText exerciseReps;
    private EditText exerciseWeight;
    private Button btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_exercise_detail);

        this.btn = (Button) findViewById(R.id.exercise_dateil_save_button);

        this.btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                saveExercise();
            }
        });

        this.exerciseRegistry = new ExerciseRegistry(this);
        this.exercise = exerciseRegistry.getExerciseById( getIntent().getStringExtra(GlobalConstants.EX_ID));

        this.setUpView();

    }

    public void setUpView(){

        this.exerciseName = (EditText) findViewById(R.id.edit_exercise_name);
        this.exerciseSets = (EditText) findViewById(R.id.edit_exercise_sets);
        this.exerciseReps = (EditText) findViewById(R.id.edit_exercise_reps);
        this.exerciseWeight = (EditText) findViewById(R.id.edit_exercise_weight);


        this.exerciseName.setText(this.exercise.getName());
        this.exerciseSets.setText(String.valueOf(this.exercise.getSets()));
        this.exerciseReps.setText(String.valueOf(this.exercise.getReps()));
        this.exerciseWeight.setText(String.valueOf(this.exercise.getWeight()));

    }

    public void saveExercise(){
        this.exercise.setName(exerciseName.getText().toString());
        this.exercise.setSets(Integer.valueOf(exerciseSets.getText().toString()));
        this.exercise.setReps(Integer.valueOf(exerciseReps.getText().toString()));
        this.exercise.setWeight(Double.valueOf(exerciseWeight.getText().toString()));

        exerciseRegistry.updateDBItem(this.exercise);


        Intent intent = new Intent(this,NavigationDrawerViewActivity.class);
        startActivity(intent);
    }

    public void onPause(){
        super.onPause();
        finish();
    }
}
