package com.sadatmalik.callbacks.observer;

import java.util.Observable;
import java.util.Observer;

/**
 * A bee observes Flower open and close.
 *
 * @author sm@creativefusion.net
 */
class Bee {
    private final String name;

    private final OpenObserver openObserver = new OpenObserver();
    private final CloseObserver closeObserver = new CloseObserver();

    public Bee(String name) {
        this.name = name;
    }

    // An inner class for observing openings:
    private class OpenObserver implements Observer{
        public void update(Observable ob, Object a) {
            System.out.println("Bee " + name
                    + "'s breakfast time!");
        }
    }

    private class CloseObserver implements Observer {
        public void update(Observable ob, Object a) {
            System.out.println("Bee " + name
                    + "'s bed time!");
        }
    }

    public Observer openObserver() {
        return openObserver;
    }

    public Observer closeObserver() {
        return closeObserver;
    }
}