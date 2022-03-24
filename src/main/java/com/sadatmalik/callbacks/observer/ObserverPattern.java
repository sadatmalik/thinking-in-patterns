package com.sadatmalik.callbacks.observer;

import com.sadatmalik.unittesting.UnitTest;

/**
 * An example of the Observer pattern - now deprecated.
 *
 * Consider source-support-listener pattern as a more flexible alternative.
 *
 * @author sm@creativefusion.net
 */
public class ObserverPattern extends UnitTest { Flower f = new Flower();
    Bee
            ba = new Bee("A"),
            bb = new Bee("B");

    HummingBird
            ha = new HummingBird("A"),
            hb = new HummingBird("B");

    public static void main(String args[]) {
        new ObserverPattern().test();
    }

    public void test() {
        f.opening().addObserver(ha.openObserver());
        f.opening().addObserver(hb.openObserver());
        f.opening().addObserver(ba.openObserver());
        f.opening().addObserver(bb.openObserver());
        f.closing().addObserver(ha.closeObserver());
        f.closing().addObserver(hb.closeObserver());
        f.closing().addObserver(ba.closeObserver());
        f.closing().addObserver(bb.closeObserver());

        // Hummingbird B decides to sleep in:
        f.opening().deleteObserver(hb.openObserver());

        // A change that interests observers:
        f.open();
        f.open(); // It's already open, no change.

        // Bee A doesn't want to go to bed:
        f.closing().deleteObserver(ba.closeObserver());

        f.close();
        f.close(); // It's already closed; no change

        f.opening().deleteObservers();

        f.open();
        f.close();
    }

}