package com.trainingsapp.chrisals.dyel20;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


import java.util.ArrayList;


/**
 * Created by chris.als on 14.02.17.
 */
public class ExerciseRecyclerViewAdapter extends RecyclerView.Adapter<ExerciseViewHolder>{

    private ArrayList<Exercise> exerciseList;
    private ExerciseViewHolder viewHolder;


    public ExerciseRecyclerViewAdapter(ArrayList<Exercise> exerciseList) {
        this.exerciseList = exerciseList;
    }

    public ExerciseViewHolder onCreateViewHolder(ViewGroup parent , int viewType){
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.exercise_row,parent,false);
        this.viewHolder = new ExerciseViewHolder(itemView);
        return this.viewHolder;
    }


    public void onBindViewHolder(ExerciseViewHolder viewHolder, int position){
        Exercise exercise = exerciseList.get(position);
        this.viewHolder.exerciseName.setText(exercise.getName());
        this.viewHolder.exerciseSet.setText(String.valueOf(exercise.getSets()));
        this.viewHolder.exerciseRep.setText(String.valueOf(exercise.getReps()));
        this.viewHolder.exerciseWeight.setText(String.valueOf(exercise.getWeight()));
    }

    @Override
    public int getItemCount() {
        return exerciseList.size();
    }
}
