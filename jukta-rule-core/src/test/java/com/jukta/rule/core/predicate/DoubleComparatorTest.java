package com.jukta.rule.core.predicate;

import org.junit.Test;

import static junit.framework.TestCase.assertEquals;

public class DoubleComparatorTest {
    @Test
    public void compare() {
        assertEquals(0, new DoubleComparator().compare(10e-2, 10e-2));
        assertEquals(-1, new DoubleComparator().compare(10e-3, 10e-2));
        assertEquals(1, new DoubleComparator().compare(10e-2, 10e-3));
    }
}
