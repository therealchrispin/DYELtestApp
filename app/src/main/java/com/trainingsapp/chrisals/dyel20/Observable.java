package com.trainingsapp.chrisals.dyel20;

/**
 * Created by chris.als on 07.02.17.
 */
public interface Observable {

    void registerObserver(Observer observer);

    void removeObserver(Observer observer);

    void notifyObserver();
}

