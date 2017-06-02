package com.trainingsapp.chrisals.dyel20.Helper;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.trainingsapp.chrisals.dyel20.Activities.ExerciseDetailActivity;
import com.trainingsapp.chrisals.dyel20.DB.DBRegistryFacade;
import com.trainingsapp.chrisals.dyel20.DB.ExerciseRegistry;
import com.trainingsapp.chrisals.dyel20.core.Exercise;
import com.trainingsapp.chrisals.dyel20.core.GlobalConstants;
import com.trainingsapp.chrisals.dyel20.R;


/**
 * Created by chris.als on 14.02.17.
 */
public class ExerciseRecyclerViewAdapter extends RecyclerView.Adapter<ExerciseViewHolder>{

    private ExerciseViewHolder viewHolder;
    private Context context;
    private DBRegistryFacade registry;
    private Exercise exercise;

    public ExerciseRecyclerViewAdapter(Context context) {
        registry = DBRegistryFacade.getInstance(context);
        this.context = context;
    }

    public ExerciseViewHolder onCreateViewHolder(ViewGroup parent , int viewType){
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.exercise_card_row,parent,false);
        this.viewHolder = new ExerciseViewHolder(itemView);
        return this.viewHolder;
    }


    public void onBindViewHolder(ExerciseViewHolder viewHolder, final int position){
        exercise = registry.getAllExercises().get(position);

        this.viewHolder.exerciseName.setText(exercise.getName());
        this.viewHolder.exerciseSet.setText(String.valueOf(exercise.getSets()));
        this.viewHolder.exerciseRep.setText(String.valueOf(exercise.getReps()));
        this.viewHolder.exerciseWeight.setText(String.valueOf(exercise.getWeight()));


        this.viewHolder.nView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, ExerciseDetailActivity.class);
                intent.putExtra(GlobalConstants.EX_ID, registry.getAllExercises().get(position).getId());
                context.startActivity(intent);
            }
        });
    }

    private void removeItem(Exercise exercise){
        registry.removeItem(exercise);
    }


    @Override
    public int getItemCount() {
        return registry.getAllExercises().size();
    }


    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public int getItemViewType(int position) {
        return position;
    }
}
