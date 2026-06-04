package com.mycompany.app;

public class Sqrt {

    public double delta = 0.00000001;
    public double arg;

    public Sqrt(double arg) {
        this.arg = arg;
    }

    public double average(double x, double y) {
        return (x + y) / 2.0;
    }

    public boolean good(double guess, double x) {
        double diff = guess * guess - x;
        return diff < delta && diff > -delta;
    }

    public double improve(double guess, double x) {
        double next = x / guess;
        return average(guess, next);
    }

    public double iter(double guess, double x) {
        double approximation = guess;
        int steps = 0;
        while (!good(approximation, x) && steps < 10_000) {
            approximation = improve(approximation, x);
            steps++;
        }
        return approximation;
    }

    public double calc() {
        if (arg == 0.0) {
            return 0.0;
        }
        return iter(1.0, arg);
    }
}
