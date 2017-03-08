package com.trainingsapp.chrisals.dyel20;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by chris.als on 23.02.17.
 */
public class WorkoutRecyclerViewAdapter extends RecyclerView.Adapter<WorkoutViewHolder>{

    private WorkoutViewHolder viewHolder;
    private Context context;
    private WorkoutRegistry workoutRegistry;

    public WorkoutRecyclerViewAdapter(Context context) {
        workoutRegistry = new WorkoutRegistry(context);
        this.context = context;
    }


    @Override
    public WorkoutViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.workout_row,parent,false);
        this.viewHolder = new WorkoutViewHolder(itemView);
        return this.viewHolder;
    }

    @Override
    public void onBindViewHolder(WorkoutViewHolder holder, int position) {
        Workout workout = workoutRegistry.getAllItems().get(position);

        this.viewHolder.workoutName.setText(workout.getName());
        this.viewHolder.weekday.setText(String.valueOf(workout.getWeekDay().toString()));
    }

    public void removeItem(int position){
        workoutRegistry.removeItem(position);
    }

    @Override
    public int getItemCount() {
        return workoutRegistry.getAllItems().size();
    }
}

