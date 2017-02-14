package com.trainingsapp.chrisals.dyel20;

import junit.framework.TestCase;

import java.util.ArrayList;

/**
 * Created by chris.als on 09.02.17.
 */
public class ExerciseRegistryTest extends TestCase {
    ExerciseRegistry registry;

    public void setUp() throws Exception{
        super.setUp();
        this.registry = new ExerciseRegistry();
        this.registry.createExercise("squat",3,5,100);
        this.registry.createExercise("Deadlift",3,5,100);
        this.registry.createExercise("Bench",3,5,100);
        this.registry.createExercise("OHP",3,5,100);

    }

    public void testCreateExercise() throws Exception {
        int oldsize = this.registry.getAll().size();
        this.registry.createExercise("testEx",1,1,1);
        assertTrue(oldsize<this.registry.getAll().size());
    }

    public void testRemove() throws Exception {
        int oldsize = this.registry.getAll().size();
        this.registry.remove(0);
        assertTrue(oldsize>this.registry.getAll().size());
    }

    public void testGetItem() throws Exception {
        assertTrue(this.registry.getItem(0) instanceof Exercise);
    }

    public void testGetAll() throws Exception {
        assertTrue(this.registry.getAll() instanceof ArrayList);
    }
}