package com.sadatmalik.singleton;

// The Singleton design pattern: you can never instantiate more than one.

// Since this isn't inherited from a Cloneable base class and cloneability isn't added,
// making it final prevents cloneability from being added through inheritance:
final class Singleton {
    //private static Singleton s = new Singleton(47);
    private static Singleton s;
    private int i;

    private Singleton(int x) {
        if (s == null) {
            s = new Singleton(47); // lazy initialize
        }
        i = x;
    }

    public static Singleton getReference() {
        return s;
    }

    public int getValue() {
        return i;
    }

    public void setValue(int x) {
        i = x;
    }
}

public class SingletonPattern {

    public static void main(String[] args) {
        Singleton s = Singleton.getReference();
        System.out.println(s.getValue());

        Singleton s2 = Singleton.getReference();
        s2.setValue(9);
        System.out.println(s.getValue());

        try {

            // Can't do this: compile-time error.
            // Singleton s3 = (Singleton)s2.clone();

        } catch(Exception e) {
            e.printStackTrace(System.err);
        }
    }
}