package com.sadatmalik.changingtheinterface;

/**
 * If you have a rather confusing collection of classes and interactions
 * that the client programmer doesn’t really need to see, then you can
 * create an interface that is useful for the client programmer and that
 * only presents what’s necessary.
 *
 * Façade is often implemented as singleton abstract factory. Of course,
 * you can easily get this effect by creating a class containing static
 * factory methods.
 *
 * @author sm@creativefusion.net
 */
class A {
    public A(int x) {}
}

class B {
    public B(long x) {}
}
class C {
    public C(double x) {}
}

// Other classes that aren't exposed by the
// facade go here ...

public class Facade {
    static A makeA(int x) { return new A(x); }
    static B makeB(long x) { return new B(x); }
    static C makeC(double x) { return new C(x); }

    // The client programmer gets the objects
    // by calling the static methods:
    A a = Facade.makeA(1);
    B b = Facade.makeB(1);
    C c = Facade.makeC(1.0);

    public static void main(String args[]) {
        new Facade();
    }
}
