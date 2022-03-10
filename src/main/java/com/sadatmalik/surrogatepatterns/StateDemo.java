package com.sadatmalik.surrogatepatterns;

import com.sadatmalik.unittesting.UnitTest;

/**
 * The State pattern adds more implementations to Proxy, along with a way
 * to switch from one implementation to another during the lifetime of the
 * surrogate.
 *
 * @author sm@creativefusion.net
 */
interface StateBase {
    void f();
    void g();
    void h();
}

class State implements StateBase {
    private StateBase implementation;

    public State(StateBase imp) {
        implementation = imp;
    }

    public void changeImp(StateBase newImp) {
        implementation = newImp;
    }

    // Pass method calls to the implementation:
    public void f() {
        implementation.f();
    }

    public void g() {
        implementation.g();
    }

    public void h() {
        implementation.h();
    }
}

class Implementation1 implements StateBase {
    public void f() {
        System.out.println("Implementation1.f()");
    }

    public void g() {
        System.out.println("Implementation1.g()");
    }

    public void h() {
        System.out.println("Implementation1.h()");
    }
}

class Implementation2 implements StateBase {
    public void f() {
        System.out.println("Implementation2.f()");
    }

    public void g() {
        System.out.println("Implementation2.g()");
    }

    public void h() {
        System.out.println("Implementation2.h()");
    }
}

public class StateDemo extends UnitTest {

    static void run(State b) {
        b.f();
        b.g();
        b.h();
    }

    State b = new State(new Implementation1());

    public void test() {
        run(b);
        b.changeImp(new Implementation2());
        run(b);
    }

    public static void main(String args[]) {
        new StateDemo().test();
    }
}