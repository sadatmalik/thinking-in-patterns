package com.sadatmalik.functionobjects;

import com.sadatmalik.unittesting.UnitTest;

import java.util.Arrays;

/**
 * Strategy appears to be a family of Command classes, all inherited
 * from the same base.
 *
 * Strategy also adds a “Context” which can be a surrogate class that
 * controls the selection and use of the particular strategy object
 * — just like State.
 *
 * @author sm@creativefusion.net
 */
interface FindMinima { // The strategy interface
    // Line is a sequence of points:
    double[] algorithm(double[] line);
}

// The various strategies:
class LeastSquares implements FindMinima {
    public double[] algorithm(double[] line) {
        return new double[] { 1.1, 2.2 }; // Dummy
    }
}

class Perturbation implements FindMinima {
    public double[] algorithm(double[] line) {
        return new double[] { 3.3, 4.4 }; // Dummy
    }
}

class Bisection implements FindMinima {
    public double[] algorithm(double[] line) {
        return new double[] { 5.5, 6.6 }; // Dummy
    }
}

// The "Context" controls the strategy:
class MinimaSolver {
    private FindMinima strategy;
    public MinimaSolver(FindMinima strat) {
        strategy = strat;
    }

    double[] minima(double[] line) {
        return strategy.algorithm(line);
    }

    void changeAlgorithm(FindMinima newAlgorithm) {
        strategy = newAlgorithm;
    }
}

public class StrategyPattern extends UnitTest {

    MinimaSolver solver = new MinimaSolver(new LeastSquares());

    double[] line = {
            1.0, 2.0, 1.0, 2.0, -1.0,
            3.0, 4.0, 5.0, 4.0
    };

    public void test() {
        System.out.println(Arrays.toString(solver.minima(line)));

        solver.changeAlgorithm(new Bisection());
        System.out.println(Arrays.toString(solver.minima(line)));
    }

    public static void main(String args[]) {
        new StrategyPattern().test();
    }
}
