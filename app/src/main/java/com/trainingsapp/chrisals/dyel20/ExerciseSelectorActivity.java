package com.trainingsapp.chrisals.dyel20;

import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.transition.Fade;
import android.widget.EditText;

public class ExerciseSelectorActivity extends FragmentActivity
        implements ExerciseTitleListFragment.onExerciseTitleSelectedListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_exercise_selector);


        if(findViewById(R.id.exercise_title_container) != null){


            if(savedInstanceState != null){
                return;
            }

            ExerciseTitleListFragment firstFragment = new ExerciseTitleListFragment();

            firstFragment.setArguments(getIntent().getExtras());

            getSupportFragmentManager().beginTransaction().add(R.id.exercise_title_container, firstFragment).commit();
        }
    }

    @Override
    public void onExerciseSelected(int postion) {

        ExerciseDetailViewFragment exerciseDetailViewFragment = new ExerciseDetailViewFragment();

        Bundle args = new Bundle();

        args.putInt(exerciseDetailViewFragment.EXERCISE_POSITION, postion);
        exerciseDetailViewFragment.setArguments(args);

        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();


        transaction.replace(R.id.exercise_title_container, exerciseDetailViewFragment);
        //transaction.addToBackStack(null);

        transaction.commit();


    }

    @Override
    public void onPause(){
        super.onPause();
        finish();
    }
}
