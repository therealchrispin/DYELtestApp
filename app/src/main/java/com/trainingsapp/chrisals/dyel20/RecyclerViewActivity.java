package com.trainingsapp.chrisals.dyel20;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
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
        exerciseRegistry = ExerciseRegistry.getInstance(this);

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());

        recyclerViewAdapter = new ExerciseRecyclerViewAdapter(exerciseRegistry.getAll());

        recyclerView.setAdapter(recyclerViewAdapter);

    }

    public void callExerciseCreator(View view){
        exerciseRegistry.saveExercises();
        startActivity(new Intent(this, ExerciseCreatorActivity.class));
    }

}
