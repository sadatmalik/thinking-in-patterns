package com.sadatmalik.changingtheinterface;

import com.sadatmalik.unittesting.UnitTest;

/**
 * When you’ve got this, and you need that, Adapter solves the problem.
 * The only requirement is to produce a that, and there are a number of
 * ways you can accomplish this adaptation.
 *
 * See below for some variations on the Adapter pattern.
 *
 * @author sm@creativefusion.net
 */
class WhatIHave {
    public void g() {}
    public void h() {}
}

interface WhatIWant {
    void f();
}

class ProxyAdapter implements WhatIWant {
    WhatIHave whatIHave;
    public ProxyAdapter(WhatIHave wih) {
        whatIHave = wih;
    }
    public void f() {
        // Implement behavior using
        // methods in WhatIHave:
        whatIHave.g();
        whatIHave.h();
    }
}

class WhatIUse {
    public void op(WhatIWant wiw) {
        wiw.f();
    }
}

// Approach 2: build adapter use into op():
class WhatIUse2 extends WhatIUse {
    public void op(WhatIHave wih) {
        new ProxyAdapter(wih).f();
    }
}

// Approach 3: build adapter into WhatIHave:
class WhatIHave2 extends WhatIHave implements WhatIWant {
    public void f() {
        g();
        h();
    }
}

// Approach 4: use an inner class:
class WhatIHave3 extends WhatIHave {
    private class InnerAdapter implements WhatIWant{
        public void f() {
            g();
            h();
        }
    }
    public WhatIWant whatIWant() {
        return new InnerAdapter();
    }
}

public class AdapterPattern extends UnitTest {
    WhatIUse whatIUse = new WhatIUse();
    WhatIHave whatIHave = new WhatIHave();
    WhatIWant adapt = new ProxyAdapter(whatIHave);
    WhatIUse2 whatIUse2 = new WhatIUse2();
    WhatIHave2 whatIHave2 = new WhatIHave2();
    WhatIHave3 whatIHave3 = new WhatIHave3();

    public void test() {
        whatIUse.op(adapt);
        // Approach 2:
        whatIUse2.op(whatIHave);
        // Approach 3:
        whatIUse.op(whatIHave2);
        // Approach 4:
        whatIUse.op(whatIHave3.whatIWant());
    }

    public static void main(String args[]) {
        new AdapterPattern().test();
    }
}
