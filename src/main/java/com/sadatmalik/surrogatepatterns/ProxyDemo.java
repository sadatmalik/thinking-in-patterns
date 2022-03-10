package com.sadatmalik.surrogatepatterns;

import com.sadatmalik.unittesting.UnitTest;

/**
 * A Proxy design pattern example.
 *
 * It isn’t necessary that Implementation have the same interface as Proxy;
 * as long as Proxy is somehow “speaking for” the class that it is referring
 * method calls to then the basic idea is satisfied
 *
 * However, it is convenient to have a common interface so that Implementation
 * is forced to fulfill all the methods that Proxy needs to call.
 *
 * @author sm@creativefusion.net
 */
interface ProxyBase {
    void f();
    void g();
    void h();
}

class Proxy implements ProxyBase {
    private ProxyBase implementation;

    public Proxy() {
        implementation = new Implementation();
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

class Implementation implements ProxyBase {
    public void f() {
        System.out.println("Implementation.f()");
    }

    public void g() {
        System.out.println("Implementation.g()");
    }

    public void h() {
        System.out.println("Implementation.h()");
    }
}

public class ProxyDemo extends UnitTest {
    Proxy p = new Proxy();

    public void test() {
        // This just makes sure it will complete
        // without throwing an exception.
        p.f();
        p.g();
        p.h();
    }

    public static void main(String args[]) {
        new ProxyDemo().test();
    }
} ///:~