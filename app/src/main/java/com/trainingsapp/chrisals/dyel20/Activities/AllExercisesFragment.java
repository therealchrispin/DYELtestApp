package com.trainingsapp.chrisals.dyel20.Activities;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.trainingsapp.chrisals.dyel20.Helper.ExerciseRecyclerViewAdapter;
import com.trainingsapp.chrisals.dyel20.R;

public class AllExercisesFragment extends Fragment {
    private RecyclerView recyclerView;

    public AllExercisesFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View root =  inflater.inflate(R.layout.fragment_all_exercises, container, false);

        ExerciseRecyclerViewAdapter adapter = new ExerciseRecyclerViewAdapter(getActivity());

        recyclerView = (RecyclerView) root.findViewById(R.id.all_exercises_recyclerview);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerView.setAdapter(adapter);
        recyclerView.setItemAnimator(new DefaultItemAnimator());

        return root;
    }






}
