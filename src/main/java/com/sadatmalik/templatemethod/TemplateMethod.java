package com.sadatmalik.templatemethod;


abstract class ApplicationFramework {
    /**
     * The base-class constructor is responsible for performing the necessary
     * initialization and then starting the “engine” (the template method) that
     * runs the application (in a GUI application, this “engine” would be the main
     * event loop).
     */
    public ApplicationFramework() {
        templateMethod(); // Dangerous!
    }

    // "private" means automatically "final":
    private void templateMethod() {
        for(int i = 0; i < 5; i++) {
            customize1();
            customize2();
        }
    }

    /**
     *  The client programmer must simply provide definitions for customize1( ) and
     *  customize2( ) and the “application” is ready to run.
     */
    abstract void customize1();
    abstract void customize2();
}

// Create a new "application":
class MyApp extends ApplicationFramework {
    void customize1() {
        System.out.print("Hello ");
    }
    void customize2() {
        System.out.println("World!");
    }

    public static void main(String args[]) {
        new MyApp();
    }
}
