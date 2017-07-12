package com.trainingsapp.chrisals.dyel20.core;

import android.support.v7.app.NotificationCompat;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

/**
 * Created by chris.als on 07.02.17.
 */
public class Workout {

    private String id;
    private ArrayList<Exercise> exercises;
    private String name;
    private ArrayList<WeekDay> weekDays;
    private boolean active;

    public Workout(){
        Calendar c = Calendar.getInstance();
        Date date = c.getTime();

        this.id = this.getName() + "_" + date;
        this.exercises = new ArrayList<Exercise>();

    }

    public Workout(String name,ArrayList<WeekDay> weekdays) {
        Calendar c = Calendar.getInstance();
        Date date = c.getTime();

        this.name = name;
        this.weekDays = weekdays;
        this.exercises = new ArrayList<Exercise>();

        this.id = this.getName() + "_" + date;
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


    public ArrayList<WeekDay> getWeekDay() {
        return weekDays;
    }

    public void setWeekDay(ArrayList<WeekDay> weekDay) {
        this.weekDays = weekDay;
    }


    public void addExercise(Exercise ex){
        this.exercises.add(ex);
    }

    public void removeExercise(Exercise ex){
        this.exercises.remove(ex);
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public ArrayList<Exercise> getExercises() {
        return exercises;
    }

    public void setExercises(ArrayList<Exercise> exercises){
        this.exercises = exercises;
    }

    public String getWeekString(){
        String weekdayString = "";

        for (int i=0;i<weekDays.size();i++){
            if(i!=weekDays.size()-1){
                weekdayString = weekdayString + String.valueOf(weekDays.get(i)) + ",";
            }else {
                weekdayString = weekdayString + String.valueOf(weekDays.get(i));
            }
        }
        return weekdayString;
    }

}
