package com.sadatmalik.callbacks.observer;

import java.util.Observable;
import java.util.Observer;

/**
 * A hummingbird observes Flower open and close.
 *
 * @author sm@creativefusion.net
 */
public class HummingBird {

    private final String name;

    private final OpenObserver openObserver = new OpenObserver();
    private final CloseObserver closeObserver = new CloseObserver();

    public HummingBird(String name) {
        this.name = name;
    }

    private class OpenObserver implements Observer {
        public void update(Observable ob, Object a) {
            System.out.println("Hummingbird " + name
                + "'s breakfast time!");
        }
    }

    private class CloseObserver implements Observer{
        public void update(Observable ob, Object a) {
            System.out.println("Hummingbird " + name
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