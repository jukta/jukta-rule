package com.jukta.rule.core.predicate;

import com.jukta.rule.core.Predicate;

/**
 * @since 1.0
 */
public class AnyPredicate implements Predicate<String> {

    @Override
    public boolean eval(String s) {
        return true;
    }

    @Override
    public int getRank() {
        return 0;
    }

}
