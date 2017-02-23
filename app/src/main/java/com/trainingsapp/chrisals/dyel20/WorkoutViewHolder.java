package com.trainingsapp.chrisals.dyel20;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

/**
 * Created by chris.als on 23.02.17.
 */
public class WorkoutViewHolder extends RecyclerView.ViewHolder{
    public TextView workoutName;
    public TextView weekday;

    public WorkoutViewHolder(View itemView) {
        super(itemView);
        this.workoutName = (TextView) itemView.findViewById(R.id.workout_name);
        this.weekday = (TextView) itemView.findViewById(R.id.wo_weekday);
    }
}
