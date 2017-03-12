package com.jukta.rule.core.predicate;

import org.junit.Test;

import java.math.BigDecimal;

import static junit.framework.TestCase.assertEquals;

public class DoubleComparatorTest {
    @Test
    public void compare() {
        assertEquals(0, new DoubleComparator().compare(10e-2, 10e-2));
        assertEquals(-1, new DoubleComparator().compare(10e-3, 10e-2));
        assertEquals(1, new DoubleComparator().compare(10e-2, 10e-3));
    }

    @Test
    public void compareWithEpsilon() {
        Double epsilon = 0.01d;
        assertEquals(0, new DoubleComparator(epsilon).compare(1.101d, 1.102d));
        assertEquals(0, new DoubleComparator(epsilon).compare(1.102, 1.101d));
        assertEquals(-1, new DoubleComparator(epsilon).compare(1.0, 1.2d));
        assertEquals(1, new DoubleComparator(epsilon).compare(1.2, 1.1d));
    }
}
