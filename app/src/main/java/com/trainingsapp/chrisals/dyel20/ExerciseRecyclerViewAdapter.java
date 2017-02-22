package com.trainingsapp.chrisals.dyel20;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;


import java.util.ArrayList;


/**
 * Created by chris.als on 14.02.17.
 */
public class ExerciseRecyclerViewAdapter extends RecyclerView.Adapter<ExerciseViewHolder>{

    private ExerciseViewHolder viewHolder;
    private Context context;
    private ExerciseRegistry exerciseRegistry;

    public ExerciseRecyclerViewAdapter(Context context) {
        exerciseRegistry = new ExerciseRegistry(context);
        this.context = context;
    }

    public ExerciseViewHolder onCreateViewHolder(ViewGroup parent , int viewType){
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.exercise_row,parent,false);
        this.viewHolder = new ExerciseViewHolder(itemView);
        return this.viewHolder;
    }


    public void onBindViewHolder(ExerciseViewHolder viewHolder, final int position){
        Exercise exercise = exerciseRegistry.getAllItems().get(position);

        this.viewHolder.exerciseName.setText(exercise.getName());
        this.viewHolder.exerciseSet.setText(String.valueOf(exercise.getSets()));
        this.viewHolder.exerciseRep.setText(String.valueOf(exercise.getReps()));
        this.viewHolder.exerciseWeight.setText(String.valueOf(exercise.getWeight()));
        this.viewHolder.exID.setText(String.valueOf(exercise.getId()));

        this.viewHolder.exerciseName.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(context, String.valueOf(position), Toast.LENGTH_SHORT).show();
                removeItem(position);
                notifyDataSetChanged();
            }
        });
    }

    private void removeItem(int position){
        exerciseRegistry.removeItem(position);
    }


    @Override
    public int getItemCount() {
        return exerciseRegistry.getAllItems().size();
    }
}
