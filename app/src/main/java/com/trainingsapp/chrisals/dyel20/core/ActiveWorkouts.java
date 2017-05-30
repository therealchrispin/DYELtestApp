package com.trainingsapp.chrisals.dyel20.core;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

/**
 * Created by chris.als on 07.02.17.
 */
public class ActiveWorkouts implements Observable {


    private ArrayList<Observer> activeWorkouts = new ArrayList<Observer>();
    private String today;

    public ActiveWorkouts() {
    }


    @Override
    public void registerObserver(Observer observer) {
        this.activeWorkouts.add(observer);
    }

    @Override
    public void removeObserver(Observer observer) {
        this.activeWorkouts.remove(observer);
    }

    @Override
    public void notifyObserver() {
        for(Observer o:this.activeWorkouts) {
            o.update(today);
        }
    }

    public void getTodaysDate(){
        Calendar c = Calendar.getInstance();
        Date date = c.getTime();

        this.today = new SimpleDateFormat("EEEE", Locale.ENGLISH).format(date.getTime());
        notifyObserver();
    }




}
