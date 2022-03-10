package com.sadatmalik.surrogatepatterns.statemachine;

/**
 * A “state machine” using objects.
 *
 * @author sm@creativefusion.net
 */
interface State {
    void run();
}

abstract class StateMachine {
    protected State currentState;

    abstract protected boolean changeState(); // Template method:

    protected final void runAll() {
        while(changeState()) // Customizable
            currentState.run();
    }
}

// A different subclass for each state:
class Wash implements State {
    public void run() {
        System.out.println("Washing");
    }
}

class Spin implements State {
    public void run() {
        System.out.println("Spinning");
    }
}

class Rinse implements State {
    public void run() {
        System.out.println("Rinsing");
    }
}

class Washer extends StateMachine {
    private int i = 0;

    // The state table:
    private State states[] = {
            new Wash(), new Spin(),
            new Rinse(), new Spin(),
    };

    public Washer() {
        runAll();
    }

    public boolean changeState() {
        if(i < states.length) {
            currentState = states[i++];
            return true;
        } else
            return false;
    }
}

public class StateMachineDemo {
    Washer w = new Washer();

    public static void main(String args[]) {
        new StateMachineDemo();
    }
}