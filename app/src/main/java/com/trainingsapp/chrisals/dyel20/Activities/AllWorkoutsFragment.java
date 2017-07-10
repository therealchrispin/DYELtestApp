package com.trainingsapp.chrisals.dyel20.Activities;


import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.trainingsapp.chrisals.dyel20.Helper.WorkoutRecyclerViewAdapter;
import com.trainingsapp.chrisals.dyel20.Helper.WorkoutViewHolder;
import com.trainingsapp.chrisals.dyel20.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class AllWorkoutsFragment extends Fragment {
    private RecyclerView recyclerView;
    private FloatingActionButton fab;


    public AllWorkoutsFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View root = inflater.inflate(R.layout.fragment_all_workouts, container, false);

        WorkoutRecyclerViewAdapter adapter = new WorkoutRecyclerViewAdapter(getActivity());


        recyclerView = (RecyclerView) root.findViewById(R.id.all_workouts);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerView.setAdapter(adapter);

        this.setFloatActionButton();

        return root;
    }

    public void setFloatActionButton(){
        fab = (FloatingActionButton) getActivity().findViewById(R.id.floatingactionbutton);

        if(fab.getVisibility() == View.INVISIBLE){
            fab.setVisibility(View.VISIBLE);
        }

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(), WorkoutCreatorActivity.class);
                startActivity(intent);
            }
        });



    }



}
