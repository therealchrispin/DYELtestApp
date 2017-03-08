package com.trainingsapp.chrisals.dyel20;


import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.ListFragment;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;

/**
 * Created by Alsberg on 05.03.17.
 */

public class ExerciseTitleListFragment extends ListFragment {
    private ArrayList<String> exerciseTitles;


    onExerciseTitleSelectedListener onExerciseTitleSelectedListener;


    public interface onExerciseTitleSelectedListener {
        public void onExerciseSelected(int postion);
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setExerciseTitles();

        setListAdapter(new ArrayAdapter<String>(getActivity(), android.R.layout.simple_list_item_activated_1, exerciseTitles));
    }

    public void setExerciseTitles(){
        exerciseTitles = new ArrayList<>();
        ExerciseRegistry exerciseRegistry = new ExerciseRegistry(getContext());
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
        onExerciseTitleSelectedListener.onExerciseSelected(position);
    }




}
