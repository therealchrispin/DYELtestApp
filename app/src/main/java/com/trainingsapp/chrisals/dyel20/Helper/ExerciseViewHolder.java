package com.trainingsapp.chrisals.dyel20.Helper;

import android.support.v7.widget.RecyclerView;
import android.view.View;

import android.widget.TextView;

import com.trainingsapp.chrisals.dyel20.R;

/**
 * Created by chris.als on 17.02.17.
 */
public class ExerciseViewHolder extends RecyclerView.ViewHolder{
    public TextView exerciseName;
    public TextView exerciseSet;
    public TextView exerciseRep;
    public TextView exerciseWeight;
    public View nView;


    public ExerciseViewHolder(View view){
        super(view);

        this.nView = view;
        this.exerciseName = (TextView) view.findViewById(R.id.exerciseName);
        this.exerciseSet = (TextView) view.findViewById(R.id.sets);
        this.exerciseRep = (TextView) view.findViewById(R.id.reps);
        this.exerciseWeight = (TextView) view.findViewById(R.id.weight);
    }


}