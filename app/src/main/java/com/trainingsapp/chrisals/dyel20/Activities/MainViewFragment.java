package com.trainingsapp.chrisals.dyel20.Activities;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.trainingsapp.chrisals.dyel20.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class MainViewFragment extends Fragment {


    public MainViewFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View root = inflater.inflate(R.layout.fragment_main_view, container, false);
        TextView textView = new TextView(getActivity());
        textView.setText("NO ACTIVE WORKOUT AVAILABLE");

        return root;
    }

}
