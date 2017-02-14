package com.trainingsapp.chrisals.dyel20;

import junit.framework.TestCase;

/**
 * Created by chris.als on 09.02.17.
 */
public class ActiveWorkoutsTest extends TestCase {
    ActiveWorkouts activeWorkouts;
    Workout workout;

    public void setUp() throws Exception {
        super.setUp();
        this.activeWorkouts = new ActiveWorkouts();
        this.workout = new Workout("test",WeekDay.Monday);

    }

    public void testRegisterObserver() throws Exception {
        this.activeWorkouts.registerObserver(workout);
    }

    public void testRemoveObserver() throws Exception {
        this.activeWorkouts.removeObserver(workout);
    }

    public void testNotifyObserver() throws Exception {
        this.activeWorkouts.registerObserver(new Workout("test",WeekDay.Thursday));

    }

    public void testGetTodaysDate() throws Exception {
        //
    }
}