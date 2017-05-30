package com.trainingsapp.chrisals.dyel20.Helper;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.trainingsapp.chrisals.dyel20.R;

/**
 * Created by chris.als on 23.02.17.
 */
public class WorkoutViewHolder extends RecyclerView.ViewHolder{
    public TextView workoutName;
    public TextView weekdays;
    public Button detailButton;

    public View workoutView;

    public WorkoutViewHolder(View itemView) {
        super(itemView);

        this.workoutView = itemView;
        this.detailButton = (Button) workoutView.findViewById(R.id.workout_card_button);
        this.workoutName = (TextView) workoutView.findViewById(R.id.workout_name);
        this.weekdays = (TextView) workoutView.findViewById(R.id.workout_weekdays);
    }

}
