package com.trainingsapp.chrisals.dyel20.Activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.LinearLayout;

import com.trainingsapp.chrisals.dyel20.DataBase.ExerciseRegistry;
import com.trainingsapp.chrisals.dyel20.DataBase.WorkoutRegistry;
import com.trainingsapp.chrisals.dyel20.core.GlobalConstants;
import com.trainingsapp.chrisals.dyel20.R;
import com.trainingsapp.chrisals.dyel20.core.WeekDay;
import com.trainingsapp.chrisals.dyel20.core.Workout;

import java.util.ArrayList;

public class WorkoutCreatorActivity extends AppCompatActivity {
    private WorkoutRegistry workoutRegistry;
    private ArrayList<WeekDay> workoutWeekday;
    public Workout workout;
    private EditText nameInput;
    protected ExerciseRegistry exerciseRegistry;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_workout_creator);

        this.workoutRegistry = new WorkoutRegistry(this);
        this.exerciseRegistry = new ExerciseRegistry(this);
        this.nameInput = (EditText) findViewById(R.id.workout_name_input);

        this.setUpWorkout();

    }

    public void setUpNewWorkout(){
        this.workoutWeekday = new ArrayList<>();
        this.workout = new Workout();
    }

    public void setUpExistingWorkout(){
        this.workout = workoutRegistry.getWorkoutById(getIntent().getStringExtra(GlobalConstants.WORKOUT_ID));
        this.workoutWeekday = this.workout.getWeekDay();

        this.setUpCheckedBoxes();
        this.nameInput.setText(this.workout.getName());

    }

    private void setUpCheckedBoxes() {
        LinearLayout layout = (LinearLayout) findViewById(R.id.activity_workout_creator);

        if (layout != null) {
            for(int i=0;i<layout.getChildCount();i++){
                if(layout.getChildAt(i) instanceof CheckBox ){
                    checkBoxIfInList((CheckBox) layout.getChildAt(i));
                }
            }
        }
    }

    public void checkBoxIfInList(CheckBox checkbox){
        for(WeekDay weekday: this.workoutWeekday){
            if(checkbox.getText().equals(String.valueOf(weekday))){
                checkbox.setChecked(true);
            }
        }
    }

    public void setUpWorkout(){
        if(getIntent().getStringExtra(GlobalConstants.WORKOUT_ID) == null){
            setUpNewWorkout();
        }else {
            setUpExistingWorkout();
        }
    }


    public void createWorkout(View view){
        this.workout.setName(nameInput.getText().toString());
        this.workout.setWeekDay(this.workoutWeekday);
        workoutRegistry.updateDB(this.workout);
    }

    public void onCheckBoxClick(View view){
        boolean checked = ((CheckBox)view).isChecked();

        switch (view.getId()){
            case R.id.checkbox_monday:
                if(checked){
                    workoutWeekday.add(WeekDay.Monday);
                } else {
                    workoutWeekday.remove(WeekDay.Monday);
                }
                break;
            case R.id.checkbox_tuesday:
                if(checked) {
                    workoutWeekday.add(WeekDay.Tuesday);
                } else {
                    workoutWeekday.remove(WeekDay.Tuesday);
                }
                break;
            case R.id.checkbox_wednesday:
                if(checked) {
                    workoutWeekday.add(WeekDay.Wednesday);
                } else {
                    workoutWeekday.remove(WeekDay.Wednesday);
                }
                break;
            case R.id.checkbox_thursday:
                if (checked) {
                    workoutWeekday.add(WeekDay.Thursday);
                } else {
                    workoutWeekday.remove(WeekDay.Thursday);
                }
                break;
            case R.id.checkbox_friday:
                if(checked){
                    workoutWeekday.add( WeekDay.Friday);
                } else {
                    workoutWeekday.remove(WeekDay.Friday);
                }
                break;
            case R.id.checkbox_saturday:
                if(checked){
                    workoutWeekday.add(WeekDay.Saturday);
                } else {
                    workoutWeekday.remove(WeekDay.Saturday);
                }
                break;
            case R.id.checkbox_sunday:
                if(checked){
                    workoutWeekday.add(WeekDay.Sunday);
                } else {
                    workoutWeekday.remove(WeekDay.Sunday);
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

