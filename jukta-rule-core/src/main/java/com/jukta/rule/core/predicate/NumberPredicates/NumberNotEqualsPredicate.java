package com.jukta.rule.core.predicate.NumberPredicates;

import com.jukta.rule.core.Predicate;

public class NumberNotEqualsPredicate implements Predicate<String> {

    private String value;

    public NumberNotEqualsPredicate(String value) {
        this.value = value;
    }

    @Override
    public boolean eval(String s) {
        return Integer.parseInt(value) != Integer.parseInt(s);
    }

    @Override
    public int getRank() {
        return 0;
    }
}