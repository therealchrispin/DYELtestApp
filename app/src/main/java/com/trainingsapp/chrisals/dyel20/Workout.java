package com.trainingsapp.chrisals.dyel20;

import java.util.ArrayList;

/**
 * Created by chris.als on 07.02.17.
 */
public class Workout extends AbstractExerciseCollection implements Observer {


    private int id;
    private ArrayList<Exercise> exercises;
    private String name;
    private WeekDay[] weekDays;
    private boolean active;

    public Workout(){
        //
    }



    public Workout(String name,WeekDay[] weekdays) {
        this.name = name;
        this.weekDays = weekdays;
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


    public WeekDay[] getWeekDay() {
        return weekDays;
    }

    public void setWeekDay(WeekDay[] weekDay) {
        this.weekDays = weekDay;
    }


    public void addExercise(Exercise ex){
        this.exercises.add(ex);
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void update(String weekday) {
        for(WeekDay w: this.weekDays) {
            if (weekday.equals(w)) {
                notifyUser();
            }
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
