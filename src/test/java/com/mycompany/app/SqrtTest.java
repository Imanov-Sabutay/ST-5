package com.mycompany.app;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class SqrtTest {

    private static final double EPS = 1e-8;

    @Test
    void averageOfThreeAndSeven() {
        Sqrt sqrt = new Sqrt(1.0);
        assertEquals(5.0, sqrt.average(3.0, 7.0), EPS);
    }

    @Test
    void averageOfOppositeValues() {
        Sqrt sqrt = new Sqrt(1.0);
        assertEquals(0.0, sqrt.average(-4.0, 4.0), EPS);
        assertNotEquals(0.0, sqrt.average(-4.0, 3.0), EPS);
    }

    @Test
    void goodForPerfectSquareRoot() {
        Sqrt sqrt = new Sqrt(1.0);
        assertTrue(sqrt.good(3.0, 9.0));
        assertTrue(sqrt.good(7.0, 49.0));
    }

    @Test
    void goodRejectsInaccurateGuess() {
        Sqrt sqrt = new Sqrt(1.0);
        assertFalse(sqrt.good(2.0, 9.0));
        assertFalse(sqrt.good(4.0, 20.0));
    }

    @Test
    void improveMovesGuessCloser() {
        Sqrt sqrt = new Sqrt(1.0);
        assertEquals(1.5, sqrt.improve(1.0, 2.0), EPS);
        assertEquals(5.0, sqrt.improve(2.0, 16.0), EPS);
    }

    @Test
    void iterStopsOnGoodGuess() {
        Sqrt sqrt = new Sqrt(1.0);
        assertEquals(5.0, sqrt.iter(5.0, 25.0), EPS);
    }

    @Test
    void iterFindsRootFromRoughStart() {
        Sqrt sqrt = new Sqrt(1.0);
        assertEquals(3.0, sqrt.iter(1.0, 9.0), 1e-6);
    }

    @Test
    void calcForPerfectSquare() {
        Sqrt sqrt = new Sqrt(144.0);
        assertEquals(12.0, sqrt.calc(), EPS);
    }

    @Test
    void calcForTwo() {
        Sqrt sqrt = new Sqrt(2.0);
        assertEquals(Math.sqrt(2.0), sqrt.calc(), 1e-6);
    }

    @Test
    void calcForSmallPositive() {
        Sqrt sqrt = new Sqrt(0.01);
        assertEquals(0.1, sqrt.calc(), 1e-6);
    }

    @Test
    void calcForLargeValue() {
        Sqrt sqrt = new Sqrt(50625.0);
        assertEquals(225.0, sqrt.calc(), 1e-6);
    }
}
