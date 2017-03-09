package com.jukta.rule.core.predicate.NumberPredicates;

import com.jukta.rule.core.Predicate;

public class NumberGreaterOrEqualsPredicate extends NumberPredicate {

    private Number value;

    public NumberGreaterOrEqualsPredicate(Number value) {
        this.value = value;
    }

    @Override
    public boolean eval(Number s)
    {
        return compare(value, s) >= 0 ;
    }

    @Override
    public int getRank() {
        return 0;
    }
}