package com.sadatmalik.surrogatepatterns.enhancedstatemachine;

import com.sadatmalik.unittesting.UnitTest;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * Another approach is to allow the state objects themselves to decide
 * what state to move to next, typically based on some kind of input to the
 * system.
 *
 * This is a more flexible solution.
 *
 * @author sm@creativefusion.net
 */
class MapLoader {
    public static void load(Map m, Object[][] pairs) {
        for(int i = 0; i < pairs.length; i++)
            m.put(pairs[i][0], pairs[i][1]);
    }
}

interface State {
    void run(String input);
}

public class Washer {
    private State currentState;

    static HashMap<String, State> states = new HashMap();

    public Washer() {
        states.put("Wash", new Wash());
        states.put("Rinse", new Rinse());
        states.put("Spin", new Spin());
        currentState = states.get("Wash");
    }

    private void nextState(Map stateTable, String input) {
        currentState =
                states.get(stateTable.get(input));
    }

    class TState implements State {
        protected HashMap<String, State> stateTable = new HashMap();

        public void run(String input) {
            String name = getClass().toString();
            System.out.println(name.substring(name.lastIndexOf("$") + 1));
            nextState(stateTable, input);
        }
    }

    // A different subclass for each state:
    class Wash extends TState {
        {
            MapLoader.load(stateTable, new String[][] {
                    { "Wash", "Spin" },
                    { "Spin", "Spin" },
                    { "Rinse", "Rinse" }
            });
        }
    }

    class Spin extends TState {
        {
            MapLoader.load(stateTable, new String[][] {
                    { "Wash", "Wash" },
                    { "Spin", "Rinse" },
                    { "Rinse", "Rinse" }
            }); }
    }

    class Rinse extends TState {
        {
            MapLoader.load(stateTable, new String[][] {
                    { "Wash", "Wash" },
                    { "Spin", "Spin" },
                    { "Rinse", "Spin" }
            }); }
    }

    public void run() {
        try {
            BufferedReader inputStream =
                    new BufferedReader (
                            new FileReader("data/StateFile.txt"));

            while (inputStream.ready()) {
                // Get next state from file...
                String input = inputStream.readLine().trim();

                if (input != null)
                    currentState.run(input);
            }
            inputStream.close ();

        } catch (IOException e) {
            e.printStackTrace(System.err);
        }
    }

    public static class Test extends UnitTest {
        Washer w = new Washer();
        public void test() {
            w.run();
        }
    }

    public static void main(String args[]) {
        new Test().test();
    }
}