package com.sadatmalik.unittesting;

import java.util.ArrayList;

public class UnitTest {
    static String testID;
    static ArrayList errors = new ArrayList();

    // Override cleanup() if test object creation allocates non-memory
    // resources that must be cleaned up:
    protected void cleanup() {}

    // Verify the truth of a condition:
    protected final void assertion(boolean condition){
        if(!condition)
            errors.add("failed: " + testID);
    }

}