package com.trainingsapp.chrisals.dyel20;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Toast;

public class ExerciseRegistryViewActivity extends AppCompatActivity {
    private ExerciseRegistry exerciseRegistry;
    private RecyclerView recyclerView;
    private ExerciseRecyclerViewAdapter recyclerViewAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_exercise_registry_view);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        exerciseRegistry = new ExerciseRegistry(this);

        setUpRecyclerView();

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);

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

        recyclerViewAdapter = new ExerciseRecyclerViewAdapter(this);

        recyclerView.setAdapter(recyclerViewAdapter);
    }

    private void callToast() {
        Toast.makeText(this,String.valueOf(9),Toast.LENGTH_SHORT).show();
    }

    public void callExerciseCreator(View view){
        startActivity(new Intent(this, ExerciseCreatorActivity.class));
    }

}
