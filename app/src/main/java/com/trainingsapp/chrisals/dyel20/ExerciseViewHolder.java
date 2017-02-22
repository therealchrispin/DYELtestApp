package com.trainingsapp.chrisals.dyel20;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

/**
 * Created by chris.als on 17.02.17.
 */
public class ExerciseViewHolder extends RecyclerView.ViewHolder {
    public TextView exerciseName;
    public TextView exerciseSet;
    public TextView exerciseRep;
    public TextView exerciseWeight;
    public TextView exID;
    public Button button;

    public ExerciseViewHolder(View view){
        super(view);
        this.exerciseName = (TextView) view.findViewById(R.id.exerciseName);
        this.exerciseSet = (TextView) view.findViewById(R.id.sets);
        this.exerciseRep = (TextView) view.findViewById(R.id.reps);
        this.exerciseWeight = (TextView) view.findViewById(R.id.weight);
        this.exID = (TextView) view.findViewById(R.id.ex_id);
        this.button = (Button) view.findViewById(R.id.btn_delete);
    }

}