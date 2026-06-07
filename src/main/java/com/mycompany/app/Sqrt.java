package com.mycompany.app;

public class Sqrt {
    private static final double DEFAULT_DELTA = 1e-12;
    private double delta = DEFAULT_DELTA;
    private double arg;

    public Sqrt(double arg) {
        this.arg = arg;
    }

    public double average(double x, double y) {
        return (x + y) / 2.0;
    }

    public boolean good(double guess, double x) {
        if (x == 0.0) {
            return Math.abs(guess) <= delta;
        }
        double ratio = x / guess;
        return Math.abs(guess - ratio) <= delta * Math.max(1.0, Math.abs(guess));
    }

    public double improve(double guess, double x) {
        return average(guess, x / guess);
    }

    public double iter(double guess, double x) {
        for (int i = 0; i < 1000 && !good(guess, x); i++) {
            guess = improve(guess, x);
        }
        return guess;
    }

    public double calc() {
        if (arg < 0.0) {
            throw new IllegalArgumentException("Argument must be non-negative: " + arg);
        }
        if (arg == 0.0) {
            return 0.0;
        }
        double initialGuess = arg >= 1.0 ? arg / 2.0 : 1.0;
        return iter(initialGuess, arg);
    }
}
