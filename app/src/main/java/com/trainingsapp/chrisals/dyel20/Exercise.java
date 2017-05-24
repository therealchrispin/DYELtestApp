package com.trainingsapp.chrisals.dyel20;

import android.provider.ContactsContract;

import java.util.Calendar;
import java.util.Date;

/**
 * Created by chris.als on 07.02.17.
 */
public class Exercise {
    private String id;
    private String name;
    private int sets;
    private int reps;
    private double weight;
    private int order;

    public Exercise(String name, int sets, int reps, double weight) {
        Calendar c = Calendar.getInstance();
        Date date = c.getTime();


        this.name = name;
        this.sets = sets;
        this.reps = reps;
        this.weight = weight;


        this.id = this.getName() + "_" + date;

    }

    public int getOrder() {
        return order;
    }

    public void setOrder(int order) {
        this.order = order;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getReps() {
        return reps;
    }

    public void setReps(int reps) {
        this.reps = reps;
    }

    public double getWeight() {
        return weight;
    }

    public void setWeight(double weight) {
        this.weight = weight;
    }

    public int getSets() {
        return sets;
    }

    public void setSets(int sets) {
        this.sets = sets;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

}
