package com.trainingsapp.chrisals.dyel20;

import java.util.ArrayList;

/**
 * Created by chris.als on 07.02.17.
 */
public class Workout extends AbstractExerciseCollection implements Observer {
    private int id;
    private ArrayList<Exercise> exercises;
    private String name;
    private WeekDay weekDay;
    private boolean active;

    public Workout(String name,WeekDay weekday) {
        this.name = name;
        this.weekDay = weekday;
        this.exercises = new ArrayList<Exercise>();
        this.active = true;

    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


    public WeekDay getWeekDay() {
        return weekDay;
    }

    public void setWeekDay(WeekDay weekDay) {
        this.weekDay = weekDay;
    }


    public void addExercise(Exercise ex){
        this.exercises.add(ex);
    }

    @Override
    public void update(String weekday) {
        if (weekday.equals(this.weekDay)) {
            notifyUser();
        }
    }


    public String notifyUser() {
    /*
        - Change return type
        - make Android notification

    */
        return "bro do you even lift";
    }

}
