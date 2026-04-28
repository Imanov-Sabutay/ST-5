package com.mycompany.app;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

class SqrtTest {
    @Test
    void constructorStoresArgumentAndCalcUsesIt() {
        Sqrt sqrt = new Sqrt(9.0);
        assertEquals(3.0, sqrt.calc(), 1e-7);
    }

    @Test
    void averageReturnsMidpoint() {
        Sqrt sqrt = new Sqrt(2.0);
        assertEquals(5.0, sqrt.average(4.0, 6.0), 1e-12);
    }

    @Test
    void averageWorksForNegativeAndPositiveValues() {
        Sqrt sqrt = new Sqrt(2.0);
        assertEquals(1.0, sqrt.average(-2.0, 4.0), 1e-12);
    }

    @Test
    void goodReturnsTrueWhenGuessIsAccurateEnough() {
        Sqrt sqrt = new Sqrt(2.0);
        assertTrue(sqrt.good(Math.sqrt(2.0), 2.0));
    }

    @Test
    void goodReturnsFalseWhenGuessIsNotAccurate() {
        Sqrt sqrt = new Sqrt(2.0);
        assertFalse(sqrt.good(1.0, 2.0));
    }

    @Test
    void improvePerformsNewtonStep() {
        Sqrt sqrt = new Sqrt(2.0);
        assertEquals(1.5, sqrt.improve(1.0, 2.0), 1e-12);
    }

    @Test
    void iterReturnsSameValueWhenGuessAlreadyGood() {
        Sqrt sqrt = new Sqrt(16.0);
        assertEquals(4.0, sqrt.iter(4.0, 16.0), 1e-12);
    }

    @Test
    void iterConvergesFromRoughGuess() {
        Sqrt sqrt = new Sqrt(16.0);
        assertEquals(4.0, sqrt.iter(1.0, 16.0), 1e-7);
    }

    @Test
    void calcForTwoIsAccurate() {
        Sqrt sqrt = new Sqrt(2.0);
        assertEquals(Math.sqrt(2.0), sqrt.calc(), 1e-7);
    }

    @Test
    void calcForLargeNumberIsAccurate() {
        Sqrt sqrt = new Sqrt(12345.0);
        assertEquals(Math.sqrt(12345.0), sqrt.calc(), 1e-7);
    }

    @Test
    void calcForFractionIsAccurate() {
        Sqrt sqrt = new Sqrt(0.25);
        assertEquals(0.5, sqrt.calc(), 1e-7);
    }
}
