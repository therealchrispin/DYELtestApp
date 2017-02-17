package com.trainingsapp.chrisals.dyel20;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.LinearLayout;

import java.util.List;

public class RecyclerViewActivity extends AppCompatActivity {
    private ExerciseRegistry exerciseRegistry;
    private RecyclerView recyclerView;
    private ExerciseRecyclerViewAdapter recyclerViewAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.recycler_view_activityy);

        recyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        exerciseRegistry = ExerciseRegistry.getInstance();

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());

        recyclerViewAdapter = new ExerciseRecyclerViewAdapter(exerciseRegistry.getAll());

        recyclerView.setAdapter(recyclerViewAdapter);

        this.createExercise("Squat",3,5,100);
        this.createExercise("Deadlift", 3, 5, 100);
        this.createExercise("Overheadpress",3,5,100);
        this.createExercise("Bench Press",3,5,100);
        this.createExercise("Barbell Row",3,5,100);
        this.createExercise("Dumbell Bench press",3,5,50);
        this.createExercise("Dumbell Bench press",3,5,50);
        this.createExercise("Dumbell Bench press",3,5,50);
        this.createExercise("Dumbell Bench press",3,5,50);
        this.createExercise("Dumbell Bench press",3,5,50);
        this.createExercise("Dumbell Bench press",3,5,50);
        this.createExercise("Dumbell Bench press",3,5,50);
        this.createExercise("Dumbell Bench press",3,5,50);
        this.createExercise("Dumbell Bench press",3,5,50);
        this.createExercise("Dumbell Bench press",3,5,50);
        this.createExercise("Dumbell Bench press",3,5,50);
        this.createExercise("Dumbell Bench press",3,5,50);
        this.createExercise("Dumbell Bench press",3,5,50);
        this.createExercise("Dumbell Bench press",3,5,50);
        this.createExercise("Dumbell Bench press",3,5,50);
        this.createExercise("Dumbell Bench press",3,5,50);
        this.createExercise("Dumbell Bench press",3,5,50);
        this.createExercise("Dumbell Bench press",3,5,50);
    }

    public void createExercise(String name, int sets, int reps, double weight){
        exerciseRegistry.createExercise(name,sets,reps,weight);
    }
}
