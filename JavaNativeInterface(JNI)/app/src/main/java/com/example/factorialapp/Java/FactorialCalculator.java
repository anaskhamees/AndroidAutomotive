// FactorialCalculator.java
package com.example.factorialapp;

public class FactorialCalculator {
    // Load the native library
    static {
        System.loadLibrary("factorialapp");
    }

    // Start factorial calculation without returning a result
    public static void startFactoriafactorialJNIlCalculation(int n, MainActivity activity) {
        factorialJNI(n, activity);
    }

    // Native method that starts the calculation in C++
    private static native void factorialJNI(int n, MainActivity activity);

    // Method for C++ to call with the factorial result
    public static void sendResultToKotlin(long result, MainActivity activity) {
        // Call the Kotlin method to display the result
        activity.displayFactorialResult(result);
    }
}
