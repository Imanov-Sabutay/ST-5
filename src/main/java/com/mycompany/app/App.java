package com.mycompany.app;

public class App {

    public static void main(String[] args) {
        double number = (args.length > 0) ? Double.parseDouble(args[0]) : 27.0;
        Sqrt rootFinder = new Sqrt(number);
        double answer = rootFinder.calc();
        System.out.println("value=" + number + ", sqrt=" + answer);
    }
}
