package com.mycompany.app;

public class Sqrt {
    private static final double DEFAULT_DELTA = 0.00000001;
    private double delta;
    private double arg;

    public Sqrt(double arg) {
        this(arg, DEFAULT_DELTA);
    }

    public Sqrt(double arg, double delta) {
        this.arg = arg;
        this.delta = delta;
    }

    public double average(double x, double y) {
        return (x + y) / 2.0;
    }

    public boolean good(double guess, double x) {
        return Math.abs(guess * guess - x) < delta;
    }

    public double improve(double guess, double x) {
        return average(guess, x / guess);
    }

    public double iter(double guess, double x) {
        while (!good(guess, x)) {
            guess = improve(guess, x);
        }
        return guess;
    }

    public double calc() {
        if (arg < 0)
            throw new IllegalArgumentException("Argument must be non-negative: " + arg);
        if (arg == 0.0)
            return 0.0;
        double initialGuess = (arg >= 1.0) ? arg / 2.0 : 1.0;
        return iter(initialGuess, arg);
    }

    public double getArg() {
        return arg;
    }

    public double getDelta() {
        return delta;
    }

    public void setDelta(double delta) {
        this.delta = delta;
    }
}
