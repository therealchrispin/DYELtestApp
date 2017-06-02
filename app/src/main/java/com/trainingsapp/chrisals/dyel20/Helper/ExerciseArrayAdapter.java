package com.trainingsapp.chrisals.dyel20.Helper;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.trainingsapp.chrisals.dyel20.DB.DBRegistryFacade;
import com.trainingsapp.chrisals.dyel20.DB.ExerciseRegistry;
import com.trainingsapp.chrisals.dyel20.core.Exercise;
import com.trainingsapp.chrisals.dyel20.R;

import java.util.ArrayList;

/**
 * Created by chris.als on 24.05.17.
 */

public class ExerciseArrayAdapter extends ArrayAdapter<Exercise> {
    private Context context;
    private DBRegistryFacade registry;
    private Exercise exercise;
    private ArrayList<Exercise> exerciseArrayList;

    public ExerciseArrayAdapter(Context context, ArrayList<Exercise> exerciseArrayList) {
        super(context, R.layout.list_group, exerciseArrayList);
        this.context = context;
        this.exerciseArrayList = exerciseArrayList;

    }


    public ExerciseArrayAdapter(Context context) {
        super(context, R.layout.list_group);

        this.context = context;
        this.registry = DBRegistryFacade.getInstance(context);
        this.exerciseArrayList = this.registry.getAllExercises();
    }

    @Override
    public View getView(int position, View mView, ViewGroup parent){

        this.exercise = exerciseArrayList.get(position);

        if(mView == null) {
            mView = LayoutInflater.from(this.context).inflate(R.layout.list_group, parent, false);
        }
        ViewHolder viewHolder = new ViewHolder();

        /*viewHolder.linearLayout = (LinearLayout) mView.findViewById(R.id.list_lin_layout);

        viewHolder.linearLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(v.isSelected()){
                    v.setSelected(false);
                }else {
                    v.setSelected(true);
                }

            }
        });*/

        viewHolder.exerciseTitle = (TextView) mView.findViewById(R.id.exercise_title);
        viewHolder.exerciseWeight = (TextView) mView.findViewById(R.id.exercise_weight);

        viewHolder.exerciseTitle.setText(exercise.getName());
        viewHolder.exerciseWeight.setText(String.valueOf(exercise.getWeight()));

        return mView;

    }

    @Override
    public int getCount() {
        return exerciseArrayList.size();
    }


    private static class ViewHolder{
        TextView exerciseTitle;
        TextView exerciseWeight;
        LinearLayout linearLayout;
    }


}
