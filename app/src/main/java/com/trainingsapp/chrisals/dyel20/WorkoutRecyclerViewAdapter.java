package com.trainingsapp.chrisals.dyel20;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by chris.als on 23.02.17.
 */
public class WorkoutRecyclerViewAdapter extends RecyclerView.Adapter<WorkoutViewHolder>{

    private WorkoutViewHolder viewHolder;
    private Context context;
    private WorkoutRegistry workoutRegistry;
    private WorkoutExerciseRegistry workoutExerciseRegistry;

    public WorkoutRecyclerViewAdapter(Context context) {
        workoutRegistry = new WorkoutRegistry(context);
        workoutExerciseRegistry = new WorkoutExerciseRegistry(context);
        this.context = context;
    }


    @Override
    public WorkoutViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.workout_row,parent,false);
        this.viewHolder = new WorkoutViewHolder(itemView);
        return this.viewHolder;
    }

    @Override
    public void onBindViewHolder(WorkoutViewHolder holder,final int position) {
        this.setUpWorkoutRow(workoutRegistry.getAllItems().get(position));


        this.viewHolder.detailButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, WorkoutDetailViewActivity.class);
                intent.putExtra(GlobalConstants.WORKOUT_ID, workoutRegistry.getAllItems().get(position).getId());
                context.startActivity(intent);
            }
        });

    }


    public void setUpWorkoutRow(Workout workout){

        this.viewHolder.workoutName.setText(workout.getName());
        this.viewHolder.weekdays.setText(this.buildString(workout.getWeekDay()));
    }

    public String buildString(ArrayList<WeekDay> weekDays){
        String weekdayString = "";

        for (int i=0;i<weekDays.size();i++){
            if(i!=weekDays.size()-1){
                weekdayString = weekdayString + String.valueOf(weekDays.get(i)) + ", ";
            }else {
                weekdayString = weekdayString + String.valueOf(weekDays.get(i));
            }
        }

        return weekdayString;
    }


    @Override
    public int getItemCount() {
        return workoutRegistry.getAllItems().size();
    }
}

