package com.mycompany.app;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;
import java.util.List;

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

    @Test
    void calcForZeroReturnsZero() {
        Sqrt sqrt = new Sqrt(0.0);
        assertEquals(0.0, sqrt.calc(), 0.0);
    }

    @Test
    void calcForNegativeArgumentThrows() {
        Sqrt sqrt = new Sqrt(-4.0);
        assertThrows(IllegalArgumentException.class, () -> sqrt.calc());
    }

    @Test
    void calcForScientificNotationValues() {
        List<Double> values = List.of(
            6.02214076e23,
            9.10938356e-31,
            1.602176634e-19,
            2.99792458e8,
            1.380649e-23
        );
        assertAll("scientific values",
            values.stream()
                .map(value -> () -> {
                    Sqrt sqrt = new Sqrt(value);
                    assertEquals(Math.sqrt(value), sqrt.calc(), Math.max(1e-12, Math.sqrt(value) * 1e-10));
                })
        );
    }

    @Test
    void calcForExtremeTinyAndHugeValues() {
        List<Double> values = List.of(
            1e-300,
            1e-200,
            1e-100,
            1e100,
            1e200,
            1e300
        );
        assertAll("extreme values",
            values.stream()
                .map(value -> () -> {
                    Sqrt sqrt = new Sqrt(value);
                    double expected = Math.sqrt(value);
                    assertEquals(expected, sqrt.calc(), Math.max(1e-10, Math.abs(expected) * 1e-9));
                })
        );
    }

    @Test
    void calcForIrrationalAndUniqueConstants() {
        List<Double> values = List.of(
            Math.PI,
            Math.E,
            (1.0 + Math.sqrt(5.0)) / 2.0,
            Math.PI * Math.E,
            Math.log(2.0)
        );
        assertAll("irrational constants",
            values.stream()
                .map(value -> () -> {
                    Sqrt sqrt = new Sqrt(value * value);
                    assertEquals(value, sqrt.calc(), 1e-7);
                })
        );
    }

    @Test
    void calcForCustomPrimeBasedSequence() {
        List<Double> primes = List.of(
            2.0, 3.0, 5.0, 7.0, 11.0, 13.0, 17.0, 19.0, 23.0, 29.0,
            31.0, 37.0, 41.0, 43.0, 47.0
        );
        List<Double> values = new ArrayList<>();
        for (int i = 0; i < primes.size(); i++) {
            double prime = primes.get(i);
            values.add(prime * prime + i * 0.12345);
            values.add(prime * 1000.0 + i * 0.314159);
        }
        assertAll("prime-based unique sequence",
            values.stream()
                .map(value -> () -> {
                    Sqrt sqrt = new Sqrt(value);
                    assertEquals(Math.sqrt(value), sqrt.calc(), Math.max(1e-12, Math.sqrt(value) * 1e-10));
                })
        );
    }
}
