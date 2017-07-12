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
    public Button detailBtn;
    public Button deleteWorkoutBtn;
    public View workoutView;

    public WorkoutViewHolder(View itemView) {
        super(itemView);

        this.workoutView = itemView;
        this.detailBtn = (Button) workoutView.findViewById(R.id.workout_card_button);
        this.deleteWorkoutBtn =(Button) workoutView.findViewById(R.id.delete_workout);
        this.workoutName = (TextView) workoutView.findViewById(R.id.workout_name);
        this.weekdays = (TextView) workoutView.findViewById(R.id.workout_weekdays);
    }

}
