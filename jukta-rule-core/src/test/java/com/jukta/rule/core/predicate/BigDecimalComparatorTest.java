package com.jukta.rule.core.predicate;


import org.junit.Test;

import java.math.BigDecimal;

import static junit.framework.TestCase.assertEquals;

public class BigDecimalComparatorTest {
    @Test
    public void compare() {
        assertEquals(0, new BigDecimalComparator().compare(new BigDecimal(1.1),new BigDecimal(1.1)));
        assertEquals(-1, new BigDecimalComparator().compare(new BigDecimal(1.0),new BigDecimal(1.2)));
        assertEquals(1, new BigDecimalComparator().compare(new BigDecimal(1.0),new BigDecimal(-1.2)));
        assertEquals(1, new BigDecimalComparator().compare(new BigDecimal(1.2),new BigDecimal(1.1)));
    }

    @Test
    public void compareWithEpsilon() {
        BigDecimal epsilon = BigDecimal.valueOf(0.01d);
        assertEquals(0, new BigDecimalComparator(epsilon).compare(new BigDecimal(1.101),new BigDecimal(1.102)));
        assertEquals(0, new BigDecimalComparator(epsilon).compare(new BigDecimal(1.102),new BigDecimal(1.101)));
        assertEquals(-1, new BigDecimalComparator(epsilon).compare(new BigDecimal(1.0),new BigDecimal(1.2)));
        assertEquals(1, new BigDecimalComparator(epsilon).compare(new BigDecimal(1.2),new BigDecimal(1.1)));
    }
}
