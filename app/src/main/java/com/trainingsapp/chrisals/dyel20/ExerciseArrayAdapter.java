package com.trainingsapp.chrisals.dyel20;

import android.app.Activity;
import android.content.Context;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by chris.als on 24.05.17.
 */

public class ExerciseArrayAdapter extends ArrayAdapter<Exercise> {
    private Context context;
    private ExerciseRegistry exerciseRegistry;
    private ArrayList<Exercise> exerciseArrayList;

    public ExerciseArrayAdapter(Context context, ArrayList<Exercise> exerciseArrayList) {
        super(context, R.layout.list_group, exerciseArrayList);
        this.context = context;
        this.exerciseArrayList = exerciseArrayList;

    }


    public ExerciseArrayAdapter(Context context) {
        super(context, R.layout.list_group);

        this.context = context;
        this.exerciseRegistry = new ExerciseRegistry(context);
        this.exerciseArrayList = this.exerciseRegistry.getAllItems();
    }

    @Override
    public View getView(int position, View mView, ViewGroup parent){

        Exercise exercise = exerciseArrayList.get(position);

        if(mView == null) {
            mView = LayoutInflater.from(this.context).inflate(R.layout.list_group, parent, false);
        }
        ViewHolder viewHolder = new ViewHolder();

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
    }
}
