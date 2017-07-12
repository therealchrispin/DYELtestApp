package com.trainingsapp.chrisals.dyel20.Helper;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.trainingsapp.chrisals.dyel20.Activities.WorkoutDetailViewActivity;
import com.trainingsapp.chrisals.dyel20.DB.DBRegistryFacade;
import com.trainingsapp.chrisals.dyel20.core.GlobalConstants;
import com.trainingsapp.chrisals.dyel20.R;
import com.trainingsapp.chrisals.dyel20.core.Workout;

/**
 * Created by chris.als on 23.02.17.
 */
public class WorkoutRecyclerViewAdapter extends RecyclerView.Adapter<WorkoutViewHolder>{

    private Context context;
    private DBRegistryFacade registry;

    public WorkoutRecyclerViewAdapter(Context context) {
        registry = DBRegistryFacade.getInstance(context);
        this.context = context;
    }

    @Override
    public WorkoutViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.workout_row,parent,false);
        return new WorkoutViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(WorkoutViewHolder viewHolder, final int position) {

        Workout workout = registry.getAllWorkouts().get(position);

        viewHolder.workoutName.setText(workout.getName());
        viewHolder.weekdays.setText(workout.getWeekString());

        WorkoutViewholderListener listener = new WorkoutViewholderListener(this ,context, position);

        viewHolder.detailBtn.setOnClickListener(listener);
        viewHolder.deleteWorkoutBtn.setOnClickListener(listener);

    }

    @Override
    public int getItemCount() {
        return registry.getAllWorkouts().size();
    }

    private class WorkoutViewholderListener implements View.OnClickListener{
        private Context context;
        private Workout workout;
        private WorkoutRecyclerViewAdapter adapter;

        public WorkoutViewholderListener(WorkoutRecyclerViewAdapter adapter ,Context context, int viewPostion){
            this.adapter = adapter;
            this.context = context;
            this.workout = DBRegistryFacade.getInstance(context).getAllWorkouts().get(viewPostion);
        }

        @Override
        public void onClick(View view) {
            switch(view.getId()) {
                case R.id.delete_workout:
                    removeItem();
                    break;
                case R.id.workout_card_button:
                    this.callWorkoutDetailView();
                    break;
            }
        }


        private void callWorkoutDetailView(){
            Intent intent = new Intent(context, WorkoutDetailViewActivity.class);
            intent.putExtra(GlobalConstants.WORKOUT_ID, workout.getId());
            context.startActivity(intent);
        }

        private void removeItem(){
            registry.removeItem(workout);
            this.adapter.notifyDataSetChanged();
        }

    }




}

