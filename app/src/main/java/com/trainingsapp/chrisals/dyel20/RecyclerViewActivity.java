package com.trainingsapp.chrisals.dyel20;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Toast;

public class RecyclerViewActivity extends AppCompatActivity {
    private RecyclerView recyclerView;
    private FloatingActionButton fab;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_exercise_registry_view);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        this.setUpRecyclerView();

        fab = (FloatingActionButton) findViewById(R.id.fab);

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                callExerciseCreator(view);
            }
        });
    }

    public void setUpRecyclerView(){
        recyclerView = (RecyclerView) findViewById(R.id.recyclerView);

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());

        ExerciseRecyclerViewAdapter exerciseRecyclerViewAdapter = new ExerciseRecyclerViewAdapter(this);

        recyclerView.setAdapter(exerciseRecyclerViewAdapter);
    }


    public void callExerciseCreator(View view){
        startActivity(new Intent(this, ExerciseCreatorActivity.class));
    }

    public void callWorkoutCreator(View view){
        //
    }

    public void setExerciseAdapter(View view){
        ExerciseRecyclerViewAdapter exerciseRecyclerViewAdapter = new ExerciseRecyclerViewAdapter(this);
        View.OnClickListener onClickListener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setWorkoutAdapter(v);
            }
        };

        recyclerView.setAdapter(exerciseRecyclerViewAdapter);
        fab.setOnClickListener(onClickListener);
    }

    public void setWorkoutAdapter(View view){
        WorkoutRecyclerViewAdapter workoutRecyclerViewAdapter = new WorkoutRecyclerViewAdapter(this);

        View.OnClickListener onClickListener = new View.OnClickListener() {
            @Override
            public void onClick(View view){
                setExerciseAdapter(view);
            }
        };

        recyclerView.setAdapter(workoutRecyclerViewAdapter);
        fab.setOnClickListener(onClickListener);

        }
}

