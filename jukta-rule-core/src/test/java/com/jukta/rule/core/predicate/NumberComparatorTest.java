package com.jukta.rule.core.predicate;


import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class NumberComparatorTest {

    @Test
    public void compare(){
        assertEquals(0, new NumberComparator().compare(1,1));
        assertEquals(1, new NumberComparator().compare(2,1));
        assertEquals(-1, new NumberComparator().compare(1,2));
    }
}
