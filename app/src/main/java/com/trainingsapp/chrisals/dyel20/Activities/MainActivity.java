package com.trainingsapp.chrisals.dyel20.Activities;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.FragmentActivity;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import com.trainingsapp.chrisals.dyel20.R;
import com.trainingsapp.chrisals.dyel20.core.GlobalConstants;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        this.setUpView();
    }

    public void setUpView(){
        if(findViewById(R.id.main_frame) != null){
            if(getIntent().getStringExtra(GlobalConstants.EXTRA_VIEW) != null){
                chooseView();
            }else {
                this.setMainViewFragment();
            };
        }

    }

    public void chooseView(){
        String view = getIntent().getStringExtra(GlobalConstants.EXTRA_VIEW);

        switch (view){
            case GlobalConstants.EXERCISE_VIEW:
                this.setAllExercisesFragment();
                break;
            case GlobalConstants.WORKOUT_VIEW:
                this.setAllWorkoutsFragment();
                break;
            case GlobalConstants.MAIN_VIEW:
                this.setMainViewFragment();
                break;
            default:
                this.setMainViewFragment();
        }
    }


    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.show_all_exercises_menu) {
            this.setAllExercisesFragment();
        } else if (id == R.id.show_all_workouts_menu) {
            this.setAllWorkoutsFragment();
        } else if (id == R.id.main_workout){
            this.setMainViewFragment();
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    private void setMainViewFragment() {
        MainViewFragment fragment = new MainViewFragment();
        getSupportFragmentManager().beginTransaction().replace(R.id.main_frame, fragment).commit();
    }

    public void setAllExercisesFragment(){
        AllExercisesFragment fragment = new AllExercisesFragment();
        getSupportFragmentManager().beginTransaction().replace(R.id.main_frame, fragment).commit();
    }


    private void setAllWorkoutsFragment(){
        AllWorkoutsFragment fragment = new AllWorkoutsFragment();
        getSupportFragmentManager().beginTransaction().replace(R.id.main_frame, fragment).commit();
    }



    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

}
