package com.trainingsapp.chrisals.dyel20.Activities;

import com.trainingsapp.chrisals.dyel20.DataBase.WorkoutRegistry;
import com.trainingsapp.chrisals.dyel20.core.Exercise;
import com.trainingsapp.chrisals.dyel20.Helper.ExerciseArrayAdapter;
import com.trainingsapp.chrisals.dyel20.core.GlobalConstants;
import com.trainingsapp.chrisals.dyel20.R;
import com.trainingsapp.chrisals.dyel20.Helper.TouchInterceptor;
import com.trainingsapp.chrisals.dyel20.Helper.TouchInterceptor.*;
import com.trainingsapp.chrisals.dyel20.core.Workout;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;

public class WorkoutDetailViewActivity extends AppCompatActivity {
    private Workout workout;
    private Exercise[] exerciseList;
    private TouchInterceptor interceptorList;
    private FloatingActionButton fab;
    private ExerciseArrayAdapter arrayAdapter;
    private DropListener dropListener = new DropListener() {
        @Override
        public void drop(int from, int to) {
            //Assuming that item is moved up the list

            int direction = -1;
            int loop_start = from;
            int loop_end = to;

            //For instance where the item is dragged down the list

            if (from < to) {
                direction = 1;
            }

            Exercise ExerciseTarget = exerciseList[from];

            for (int i = loop_start; i != loop_end; i = i + direction) {
                exerciseList[i] = exerciseList[i + direction];
            }

            exerciseList[to] = ExerciseTarget;

            ((BaseAdapter) interceptorList.getAdapter()).notifyDataSetChanged();
            setSortedExerciseList();
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_workout_detail);



        WorkoutRegistry workoutRegistry = new WorkoutRegistry(this);

        this.fab = (FloatingActionButton) findViewById(R.id.fab);

        this.workout = workoutRegistry.getWorkoutById(getIntent().getStringExtra(GlobalConstants.WORKOUT_ID));

        this.setUpView();

        ListView listView = (ListView) findViewById(R.id.touchinterceptor);

        interceptorList = (TouchInterceptor) listView;
        interceptorList.setDropListener(dropListener);

        this.arrayAdapter = new ExerciseArrayAdapter(this, this.workout.getExercises());
        listView.setAdapter(arrayAdapter);
    }


    public void setUpView(){
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addExercises(v);
            }
        });


        TextView workoutName = (TextView) findViewById(R.id.workout_name);
        workoutName.setText(this.workout.getName());

        if(this.workout.getExercises().size()>0){
            this.exerciseList = new Exercise[this.workout.getExercises().size()];
            for(int i=0;i<this.workout.getExercises().size();i++){
                this.exerciseList[i] = this.workout.getExercises().get(i);
            }
        }
    }

    public void setSortedExerciseList(){
        this.workout.getExercises().clear();
        for(Exercise ex: exerciseList){
            this.workout.addExercise(ex);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.edit_actionbar_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.edit_menu:
                Intent intent = new Intent(this,WorkoutCreatorActivity.class);
                intent.putExtra(GlobalConstants.WORKOUT_ID, this.workout.getId());
                startActivity(intent);
                return true;
            default:
                return true;
        }
    }


    public void addExercises(View view) {
        Intent intent = new Intent(this, ExerciseSelectorActivity.class);
        intent.putExtra(GlobalConstants.WORKOUT_ID, this.workout.getId());
        startActivity(intent);
    }
}
