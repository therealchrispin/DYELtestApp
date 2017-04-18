package com.trainingsapp.chrisals.dyel20;


import android.app.Activity;
import android.os.Bundle;
import android.os.Trace;
import android.support.v4.app.ListFragment;
import android.transition.Transition;
import android.transition.TransitionInflater;
import android.view.View;
import android.view.animation.AnimationUtils;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;

/**
 * Created by Alsberg on 05.03.17.
 */

public class ExerciseTitleListFragment extends ListFragment {
    private ArrayList<String> exerciseTitles;
    protected ExerciseRegistry exerciseRegistry;

    onExerciseTitleSelectedListener onExerciseTitleSelectedListener;


    public interface onExerciseTitleSelectedListener {
        public void onExerciseSelected(String id, int position);
        public void returnToWorkoutView();
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setExerciseTitles();

        //Transition explodeTransition = TransitionInflater.from(getActivity()).inflateTransition(R.transition.fragment_title_exit);
        //explodeTransition.setInterpolator(AnimationUtils.loadInterpolator(getActivity(),android.R.interpolator.linear_out_slow_in));
        //explodeTransition.setDuration(5000);
        //setExitTransition(explodeTransition);
        // TODO: 10.03.17 make Custom ArrayAdapter
        setListAdapter(new ArrayAdapter<String>(getActivity(), android.R.layout.simple_list_item_activated_1, exerciseTitles));
    }

    public void setExerciseTitles(){
        exerciseTitles = new ArrayList<>();
        exerciseRegistry = new ExerciseRegistry(getContext());
        ArrayList<Exercise> exercises = exerciseRegistry.getAllItems();

        for(int i=0;i<exercises.size();i++){
            String name = exercises.get(i).getName();
            this.exerciseTitles.add(name);
        }
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);

        // This makes sure that the container activity has implemented
        // the callback interface. If not, it throws an exception.
        try {
            onExerciseTitleSelectedListener = (onExerciseTitleSelectedListener) activity;
        } catch (ClassCastException e) {
            throw new ClassCastException(activity.toString()
                    + " must implement onExerciseTitleSelectedListener");
        }
    }

    @Override
    public void onListItemClick(ListView listView, View view, int position, long id){
        String Exid = exerciseRegistry.getAllItems().get(position).getId();

        onExerciseTitleSelectedListener.onExerciseSelected(Exid, position);
    }

    @Override
    public void onPause(){
        super.onPause();

    }
}
