package com.trainingsapp.chrisals.dyel20;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;
import android.widget.AdapterView.*;

import java.util.ArrayList;

public class WorkoutCreatorActivity extends AppCompatActivity {
    private WorkoutRegistry workoutRegistry;
    private ArrayList<WeekDay> workoutWeekday;
    public  Workout workout;
    private EditText nameInput;
    protected ExerciseRegistry exerciseRegistry;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_workout_creator);
        workoutRegistry = new WorkoutRegistry(this);
        exerciseRegistry = new ExerciseRegistry(this);
        workoutWeekday = new ArrayList<>();
        workout = new Workout();
        nameInput = (EditText) findViewById(R.id.workout_name_input);
    }


    public void createWorkout(View view){
        this.workout.setName(nameInput.getText().toString());
        this.workout.setWeekDay(this.workoutWeekday);
        workoutRegistry.addNewWorkout(this.workout);
    }

    public void onCheckBoxClick(View view){
        boolean checked = ((CheckBox)view).isChecked();

        switch (view.getId()){
            case R.id.checkbox_monday:
                if(checked){
                    workoutWeekday.add(WeekDay.Monday);
                }
                break;
            case R.id.checkbox_tuesday:
                if(checked) {
                    workoutWeekday.add(WeekDay.Tuesday);
                }
                break;
            case R.id.checkbox_wednesday:
                if(checked) {
                    workoutWeekday.add(WeekDay.Wednesday);
                }
                break;
            case R.id.checkbox_thursday:
                if (checked) {
                    workoutWeekday.add(WeekDay.Thursday);
                }
                break;
            case R.id.checkbox_friday:
                if(checked){
                    workoutWeekday.add( WeekDay.Friday);
                }
                break;
            case R.id.checkbox_saturday:
                if(checked){
                    workoutWeekday.add(WeekDay.Saturday);
                }
                break;
            case R.id.checkbox_sunday:
                if(checked){
                    workoutWeekday.add(WeekDay.Sunday);
                }
                break;
            }
        }

    public void addExercises(View view){
        createWorkout(view);
        Intent intent = new Intent(this, ExerciseSelectorActivity.class);
        intent.putExtra(GlobalConstants.WORKOUT_ID, this.workout.getId());
        startActivity(intent);
    }

    @Override
    public void onPause(){
        super.onPause();
        finish();
    }

}

