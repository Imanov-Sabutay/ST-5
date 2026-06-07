package com.mycompany.app;

import org.junit.Test;
import org.junit.Before;
import static org.junit.Assert.*;

public class AppTest {
    private Sqrt sqrt;
    private static final double EPSILON = 1e-6;

    @Before
    public void setUp() {
        sqrt = new Sqrt(2.0);
    }

    @Test
    public void testConstructorDefaultDelta() {
        Sqrt s = new Sqrt(4.0);
        assertEquals(4.0, s.getArg(), EPSILON);
        assertEquals(0.00000001, s.getDelta(), EPSILON);
    }

    @Test
    public void testConstructorCustomDelta() {
        Sqrt s = new Sqrt(9.0, 0.0001);
        assertEquals(9.0, s.getArg(), EPSILON);
        assertEquals(0.0001, s.getDelta(), EPSILON);
    }

    @Test
    public void testAveragePositive() {
        double result = sqrt.average(4.0, 6.0);
        assertEquals(5.0, result, EPSILON);
    }

    @Test
    public void testAverageNegative() {
        double result = sqrt.average(-2.0, -4.0);
        assertEquals(-3.0, result, EPSILON);
    }

    @Test
    public void testAverageWithZero() {
        double result = sqrt.average(0.0, 10.0);
        assertEquals(5.0, result, EPSILON);
    }

    @Test
    public void testGoodReturnsTrueWhenCloseEnough() {
        Sqrt s = new Sqrt(4.0);
        boolean result = s.good(2.0, 4.0);
        assertTrue(result);
    }

    @Test
    public void testGoodReturnsFalseWhenNotCloseEnough() {
        Sqrt s = new Sqrt(4.0);
        boolean result = s.good(1.5, 4.0);
        assertFalse(result);
    }

    @Test
    public void testImprove() {
        Sqrt s = new Sqrt(2.0);
        double initialGuess = 1.5;
        double improved = s.improve(initialGuess, 2.0);
        assertEquals(1.416666666, improved, EPSILON);
    }

    @Test
    public void testIter() {
        Sqrt s = new Sqrt(4.0);
        double result = s.iter(1.0, 4.0);
        assertEquals(2.0, result, EPSILON);
    }

    @Test
    public void testCalcPerfectSquare4() {
        Sqrt s = new Sqrt(4.0);
        double result = s.calc();
        assertEquals(2.0, result, EPSILON);
    }

    @Test
    public void testCalcPerfectSquare9() {
        Sqrt s = new Sqrt(9.0);
        double result = s.calc();
        assertEquals(3.0, result, EPSILON);
    }

    @Test
    public void testCalcNonPerfectSquare2() {
        Sqrt s = new Sqrt(2.0);
        double result = s.calc();
        assertEquals(Math.sqrt(2.0), result, EPSILON);
        assertTrue(Math.abs(result - 1.414213562) < 0.0001);
    }

    @Test
    public void testCalcOne() {
        Sqrt s = new Sqrt(1.0);
        double result = s.calc();
        assertEquals(1.0, result, EPSILON);
    }

    @Test
    public void testCalcSmallDecimal() {
        Sqrt s = new Sqrt(0.25);
        double result = s.calc();
        assertEquals(0.5, result, EPSILON);
    }

    @Test
    public void testCalcLargeNumber() {
        Sqrt s = new Sqrt(100.0);
        double result = s.calc();
        assertEquals(10.0, result, EPSILON);
    }

    @Test
    public void testCalcVeryLargeNumber() {
        Sqrt s = new Sqrt(10000.0);
        double result = s.calc();
        assertEquals(100.0, result, EPSILON);
    }

    @Test
    public void testGetDelta() {
        Sqrt s = new Sqrt(5.0, 0.001);
        assertEquals(0.001, s.getDelta(), EPSILON);
    }

    @Test
    public void testSetDelta() {
        Sqrt s = new Sqrt(5.0);
        s.setDelta(0.01);
        assertEquals(0.01, s.getDelta(), EPSILON);
    }

    @Test
    public void testGetArg() {
        Sqrt s = new Sqrt(7.5);
        assertEquals(7.5, s.getArg(), EPSILON);
    }

    @Test
    public void testHigherPrecision() {
        Sqrt s = new Sqrt(2.0, 0.00000001);
        double result = s.calc();
        double expectedSqrt2 = 1.41421356237;
        assertEquals(expectedSqrt2, result, 0.00000001);
    }

    @Test
    public void testCalcFractional() {
        Sqrt s = new Sqrt(3.0);
        double result = s.calc();
        assertEquals(Math.sqrt(3.0), result, EPSILON);
        assertTrue(Math.abs(result - 1.732050808) < 0.0001);
    }

    @Test
    public void testCalcGoldenRatio() {
        double phi = (1.0 + Math.sqrt(5.0)) / 2.0;
        Sqrt s = new Sqrt(phi * phi);
        double result = s.calc();
        assertEquals(phi, result, EPSILON);
    }

    @Test
    public void testMultipleCalcsWithSameObject() {
        Sqrt s1 = new Sqrt(4.0);
        double result1 = s1.calc();
        assertEquals(2.0, result1, EPSILON);

        Sqrt s2 = new Sqrt(9.0);
        double result2 = s2.calc();
        assertEquals(3.0, result2, EPSILON);
    }

    @Test
    public void testAverageSameNumbers() {
        double result = sqrt.average(5.0, 5.0);
        assertEquals(5.0, result, EPSILON);
    }

    @Test
    public void testGoodWithExactMatch() {
        Sqrt s = new Sqrt(1.0, 0.00000001);
        boolean result = s.good(1.0, 1.0);
        assertTrue(result);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testNegativeArgThrows() {
        Sqrt s = new Sqrt(-4.0);
        s.calc();
    }

    @Test
    public void testUniqueRandomCases() {
        long seed = System.currentTimeMillis() ^ System.getProperty("user.name").hashCode();
        java.util.Random rnd = new java.util.Random(seed);
        for (int i = 0; i < 50; i++) {
            double val = 1e-6 + rnd.nextDouble() * 1e6; // range [1e-6, 1e6+1e-6]
            Sqrt s = new Sqrt(val, 1e-7);
            double result = s.calc();
            assertEquals(Math.sqrt(val), result, 1e-6);
        }
    }
}
