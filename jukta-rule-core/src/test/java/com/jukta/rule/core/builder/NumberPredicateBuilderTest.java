package com.jukta.rule.core.builder;


import com.jukta.rule.core.predicate.AnyPredicate;
import com.jukta.rule.core.predicate.ComparatorPredicate;
import com.jukta.rule.core.predicate.NumberComparator;
import com.jukta.rule.core.predicate.Operator;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class NumberPredicateBuilderTest {

    @Test
    public void anyPredicate(){
        assertEquals(
                new AnyPredicate().getType(),
                new NumberPredicateBuilder().predicate("*").getType()
        );
    }

    @Test
    public void comparatorPredicate() {
        assertEquals(
                new ComparatorPredicate<>(1, new NumberComparator(), Operator.EQUALS).eval(1),
                new NumberPredicateBuilder().predicate("1").eval(1)
        );
    }

    @Test(expected = IllegalArgumentException.class)
    public void exception() {
        new NumberPredicateBuilder().predicate("!");
    }
}
