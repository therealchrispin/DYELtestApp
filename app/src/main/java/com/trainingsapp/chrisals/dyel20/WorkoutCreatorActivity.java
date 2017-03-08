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

public class WorkoutCreatorActivity extends AppCompatActivity {
    private WorkoutRegistry workoutRegistry;
    private WeekDay[] workoutWeekday;
    private Workout workout;
    private EditText nameInput;
    private ExerciseRegistry exerciseRegistry;
    private ListView lw;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_workout_creator);
        workoutRegistry = new WorkoutRegistry(this);
        exerciseRegistry = new ExerciseRegistry(this);
        workoutWeekday = new WeekDay[7];
        workout = new Workout();
        nameInput = (EditText) findViewById(R.id.workout_name_input);


    }



    public void createWokrout(View view){
        this.workout.setName(nameInput.getText().toString());
        this.workout.setWeekDay(this.workoutWeekday);
        workoutRegistry.addNewWorkout(this.workout);
    }

    public void onCheckBoxClick(View view){
        boolean checked = ((CheckBox)view).isChecked();

        switch (view.getId()){
            case R.id.checkbox_monday:
                if(checked){
                    workoutWeekday[0] = WeekDay.Monday;
                }
                break;
            case R.id.checkbox_tuesday:
                if(checked) {
                    workoutWeekday[1] = WeekDay.Tuesday;
                }
                break;
            case R.id.checkbox_wednesday:
                if(checked) {
                    workoutWeekday[2] = WeekDay.Wednesday;
                }
                break;
            case R.id.checkbox_thursday:
                if (checked) {
                    workoutWeekday[3] = WeekDay.Thursday;
                }
                break;
            case R.id.checkbox_friday:
                if(checked){
                    workoutWeekday[4] = WeekDay.Friday;
                }
                break;
            case R.id.checkbox_saturday:
                if(checked){
                    workoutWeekday[5] = WeekDay.Saturday;
                }
                break;
            case R.id.checkbox_sunday:
                if(checked){
                    workoutWeekday[6] = WeekDay.Sunday;
                }
                break;
            }
        }
    public void addExercises(View view){
        startActivity(new Intent(this, ExerciseSelectorActivity.class));
    }
}

