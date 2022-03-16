package com.sadatmalik.functionobjects.chainofresponsibility;

import com.sadatmalik.unittesting.UnitTest;

import java.util.Arrays;

/**
 * Chain of Responsibility might be thought of as a dynamic generalization
 * of recursion using Strategy objects.
 *
 * You make a call, and each Strategy in a linked sequence tries to satisfy the
 * call. The process ends when one of the strategies is successful or the chain
 * ends.
 *
 * In recursion, one method calls itself over and over until a termination
 * condition is reached; with Chain of Responsibility, a method calls the same
 * base-class method (with different implementations) which calls another
 * implementation of the base-class method, etc., until a termination condition
 * is reached.
 *
 * Instead of calling a single method to satisfy a request, multiple methods in
 * the chain have a chance to satisfy the request, so it has the flavor of an
 * expert system.
 *
 * Since the chain is effectively a linked list, it can be dynamically created,
 * so you could also think of it as a more general, dynamically-built "switch"
 * statement.
 *
 * @author sm@creativefusion.net
 */
class FindMinima {
    private FindMinima successor = null;

    public void add(FindMinima succ) {
        FindMinima end = this;
        while(end.successor != null)
            end = end.successor; // Traverse list
        end.successor = succ;
    }

    public double[] nextAlgorithm(double[] line) {
        if(successor != null)
            // Try the next one in the chain:
            return successor.algorithm(line);
        else
            return new double[] {}; // Nothing found
    }

    public double[] algorithm(double[] line) {
        // FindMinima algorithm() is only the
        // start of the chain; doesn't actually try
        // to solve the problem:
        return nextAlgorithm(line);
    }
}

class LeastSquares extends FindMinima {
    public double[] algorithm(double[] line) {
        System.out.println("LeastSquares.algorithm");

        boolean weSucceed = false;

        if(weSucceed) // Actual test/calculation here
            return new double[] { 1.1, 2.2 }; // Dummy
        else // Try the next one in the chain:
            return nextAlgorithm(line);
    }
}

class Perturbation extends FindMinima {
    public double[] algorithm(double[] line) {
        System.out.println("Perturbation.algorithm");

        boolean weSucceed = false;

        if(weSucceed) // Actual test/calculation here
            return new double[] { 3.3, 4.4 }; // Dummy
        else // Try the next one in the chain:
            return nextAlgorithm(line);
    }
}

class Bisection extends FindMinima {
    public double[] algorithm(double[] line) {
        System.out.println("Bisection.algorithm");

        boolean weSucceed = true;

        if(weSucceed) // Actual test/calculation here
            return new double[] { 5.5, 6.6 }; // Dummy
        else
            return nextAlgorithm(line);
    }
}

// The "Handler" proxies to the first functor:
class MinimaSolver {
    private FindMinima chain = new FindMinima();

    void add(FindMinima newAlgorithm) {
        chain.add(newAlgorithm);
    }
    // Make the call to the top of the chain:
    double[] minima(double[] line) {
        return chain.algorithm(line);
    }
}

public class ChainOfResponsibility extends UnitTest {
    MinimaSolver solver = new MinimaSolver();

    double[] line = {
            1.0, 2.0, 1.0, 2.0, -1.0,
            3.0, 4.0, 5.0, 4.0
    };

    public void test() {
        solver.add(new LeastSquares());
        solver.add(new Perturbation());
        solver.add(new Bisection());
        System.out.println(Arrays.toString(solver.minima(line)));
    }

    public static void main(String args[]) {
        new ChainOfResponsibility().test();
    }
}