package com.trainingsapp.chrisals.dyel20.Activities;


import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.TextView;

import com.trainingsapp.chrisals.dyel20.DB.DBRegistryFacade;
import com.trainingsapp.chrisals.dyel20.Helper.ExerciseArrayAdapter;
import com.trainingsapp.chrisals.dyel20.R;
import com.trainingsapp.chrisals.dyel20.core.Workout;

import java.util.ArrayList;
import java.util.Calendar;

/**
 * A simple {@link Fragment} subclass.
 */
public class MainViewFragment extends Fragment {
    DBRegistryFacade registry;
    private Workout workout;

    public MainViewFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View root = inflater.inflate(R.layout.fragment_main_view, container, false);
        registry = DBRegistryFacade.getInstance(getContext());


        FloatingActionButton fab = (FloatingActionButton) getActivity().findViewById(R.id.floatingactionbutton);
        fab.setVisibility(View.INVISIBLE);

        this.getActiveWorkout();
        if(workout != null){
            setUpView(root);
        }

        return root;
    }

    private void getActiveWorkout(){
        for(Workout wo: registry.getAllWorkouts()) {
                if (wo.isActive()) {
                    this.workout = wo;
                    this.workout.setExercises(registry.getExercisesByWorkoutID(workout));

                }
        }
    }



    private void setUpView(View root){

        TextView workoutName = (TextView) root.findViewById(R.id.workout_name);
        ListView listView = (ListView) root.findViewById(R.id.exercise_list);

        String name = workout.getName();
        workoutName.setText(name);
        listView.setAdapter(new ExerciseArrayAdapter(getContext(), workout.getExercises()));

    }



}
