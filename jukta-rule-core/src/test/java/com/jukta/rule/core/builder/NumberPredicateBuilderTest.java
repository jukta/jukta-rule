package com.jukta.rule.core.builder;


import com.jukta.rule.core.predicate.*;
import org.junit.Test;

import java.math.BigDecimal;
import java.math.BigInteger;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class NumberPredicateBuilderTest {

    @Test
    public void anyPredicate(){
        assertEquals(AnyPredicate.class, new NumberPredicateBuilder<>(Double.class).predicate("*").getClass());
    }

    @Test
    public void doublePredicate() {
        assertTrue(new NumberPredicateBuilder<>(Double.class).predicate("1").eval(1d));
    }

    @Test
    public void bigDecimalPredicate() {
        assertTrue(new NumberPredicateBuilder<>(BigDecimal.class).predicate("1").eval(BigDecimal.ONE));
    }

    @Test
    public void bigIntegerPredicate() {
        assertTrue(new NumberPredicateBuilder<>(BigInteger.class).predicate("1").eval(BigInteger.ONE));
    }

    @Test
    public void longPredicate() {
        assertTrue(new NumberPredicateBuilder<>(Long.class).predicate("1").eval(1L));
    }

    @Test
    public void integerPredicate() {
        assertTrue(new NumberPredicateBuilder<>(Integer.class).predicate("1").eval(1));
    }

    @Test(expected = IllegalArgumentException.class)
    public void exception() {
        new NumberPredicateBuilder<>(Double.class).predicate("!");
    }
}
