package com.jukta.rule.core.predicate;

import com.jukta.rule.core.Predicate;

/**
 * @since 1.0
 */
public class StringPredicate implements Predicate<String> {
    private String value;

    public StringPredicate(String value) {
        this.value = value;
    }

    @Override
    public boolean eval(String s) {
        return value.equals(s);
    }

    @Override
    public int getRank() {
        return 1;
    }
}
