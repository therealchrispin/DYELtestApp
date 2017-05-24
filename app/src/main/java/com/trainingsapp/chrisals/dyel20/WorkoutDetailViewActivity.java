package com.trainingsapp.chrisals.dyel20;

import com.trainingsapp.chrisals.dyel20.View.TouchInterceptor;
import com.trainingsapp.chrisals.dyel20.View.TouchInterceptor.*;
import android.app.ListActivity;
import android.os.Bundle;
import android.widget.BaseAdapter;
import android.widget.TextView;

public class WorkoutDetailViewActivity extends ListActivity {
    private Workout workout;
    private Exercise[] exerciseList;
    private TouchInterceptor interceptorList;
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
        setContentView(R.layout.activity_workout_detail_view);

        WorkoutRegistry workoutRegistry = new WorkoutRegistry(this);

        this.workout = workoutRegistry.getWorkoutById(getIntent().getStringExtra(GlobalConstants.WORKOUT_ID));

        this.setUpView();

        interceptorList = (TouchInterceptor) getListView();
        interceptorList.setDropListener(dropListener);

        ExerciseArrayAdapter arrayAdapter = new ExerciseArrayAdapter(this, this.workout.getExercises());
        setListAdapter(arrayAdapter);
    }

    public void setUpView(){
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


}
