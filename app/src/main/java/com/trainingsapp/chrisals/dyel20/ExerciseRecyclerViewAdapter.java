package com.trainingsapp.chrisals.dyel20;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.LinkedHashMap;
import java.util.List;

/**
 * Created by chris.als on 14.02.17.
 */
public class ExerciseRecyclerViewAdapter extends RecyclerView.Adapter<ExerciseRecyclerViewAdapter.ExerciseViewHolder>{

    private List<Exercise> exerciseList;
    private ExerciseViewHolder viewHolder;


    public void setExerciseList(List<Exercise> exerciseList) {
        this.exerciseList = exerciseList;
    }

    public ExerciseViewHolder onCreateViewHolder(ViewGroup parent , int viewType){
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.exercise_row,parent,false);
        return new ExerciseViewHolder(itemView);
    }


    public void onBindViewHolder(ExerciseViewHolder viewHolder, int position){
        Exercise exercise = exerciseList.get(position);
        viewHolder.exerciseName.setText(exercise.getName());
        viewHolder.exerciseSet.setText(exercise.getSets());
        viewHolder.exerciseRep.setText(exercise.getReps());
        viewHolder.exerciseWeight.setText(String.valueOf(exercise.getWeight()));
    }

    @Override
    public int getItemCount() {
        return exerciseList.size();
    }

    public class ExerciseViewHolder extends RecyclerView.ViewHolder {
        public TextView exerciseName;
        public TextView exerciseSet;
        public TextView exerciseRep;
        public TextView exerciseWeight;

        public ExerciseViewHolder(View view){
            super(view);
            this.exerciseName = (TextView) view.findViewById(R.id.exerciseName);
            this.exerciseSet = (TextView) view.findViewById(R.id.sets);
            this.exerciseRep = (TextView) view.findViewById(R.id.reps);
            this.exerciseWeight = (TextView) view.findViewById(R.id.weight);
        }

    }
}
