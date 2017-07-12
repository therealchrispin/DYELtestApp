package com.trainingsapp.chrisals.dyel20.Activities;

import com.trainingsapp.chrisals.dyel20.DB.DBRegistryFacade;
import com.trainingsapp.chrisals.dyel20.DB.WorkoutRegistry;
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
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;


public class WorkoutDetailViewActivity extends AppCompatActivity implements AdapterView.OnItemClickListener, AdapterView.OnItemLongClickListener {

    private Workout workout;
    private Exercise[] exerciseList;
    private TouchInterceptor interceptorList;
    private FloatingActionButton fab;
    private ExerciseArrayAdapter arrayAdapter;
    private ListView listView;
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

        this.setUpView();

    }

    @Override
    protected void onRestart() {
        super.onRestart();

        this.setUpView();
    }

    public void setUpView(){
        //interceptorList = (TouchInterceptor) listView;
        //interceptorList.setDropListener(dropListener);


        DBRegistryFacade registry = DBRegistryFacade.getInstance(this);
        this.workout = registry.getWorkoutByID(getIntent().getStringExtra(GlobalConstants.WORKOUT_ID));
        this.workout.setExercises(registry.getExercisesByWorkoutID(workout));

        this.fab = (FloatingActionButton) findViewById(R.id.fab);


        listView = (ListView) findViewById(R.id.touchinterceptor);

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addExercises(v);
            }
        });

        this.arrayAdapter = new ExerciseArrayAdapter(this, this.workout.getExercises());
        //listView.setChoiceMode(ListView.CHOICE_MODE_MULTIPLE);
        listView.setAdapter(arrayAdapter);

        listView.setOnItemClickListener(this);
        listView.setOnItemLongClickListener(this);

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


    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Intent intent = new Intent(this, ExerciseDetailActivity.class);
        intent.putExtra(GlobalConstants.EX_ID, this.workout.getExercises().get(position).getId());
        startActivity(intent);
    }

    @Override
    public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
        Exercise exercise = workout.getExercises().get(position);
        workout.removeExercise(exercise);
        DBRegistryFacade.getInstance(this).removeExerciseFromWorkout(workout, exercise);
        this.arrayAdapter.notifyDataSetChanged();

        return true;
    }
}
