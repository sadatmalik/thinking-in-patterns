package com.sadatmalik.callbacks.observer;

import java.util.Observable;

/**
 * A flower opens and closes notifying the corresponding registered
 * observers.
 *
 * OpenNotifier and CloseNotifier both inherit Observable, so they have
 * access to setChanged() and can be handed to anything that needs an
 * Observable.
 *
 * @author sm@creativefusion.net
 */
class Flower {
    private boolean isOpen;

    private final OpenNotifier openNotifier = new OpenNotifier();
    private final CloseNotifier closeNotifier = new CloseNotifier();

    public Flower() {
        isOpen = false;
    }

    public void open() { // Opens its petals
        isOpen = true;
        openNotifier.notifyObservers();
        closeNotifier.open();
    }

    public void close() { // Closes its petals
        isOpen = false;
        closeNotifier.notifyObservers();
        openNotifier.close();
    }

    public Observable opening() {
        return openNotifier;
    }

    public Observable closing() {
        return closeNotifier;
    }

    private class OpenNotifier extends Observable {
        private boolean alreadyOpen = false;

        public void notifyObservers() {
            if(isOpen && !alreadyOpen) {
                setChanged();
                super.notifyObservers();
                alreadyOpen = true;
            }
        }
        public void close() {
            alreadyOpen = false;
        }
    }

    private class CloseNotifier extends Observable {
        private boolean alreadyClosed = false;

        public void notifyObservers() {
            if(!isOpen && !alreadyClosed) {
                setChanged();
                super.notifyObservers();
                alreadyClosed = true;
            }
        }
        public void open() {
            alreadyClosed = false;
        }
    }
}