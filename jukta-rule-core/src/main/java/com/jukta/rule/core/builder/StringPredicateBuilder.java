package com.jukta.rule.core.builder;

import com.jukta.rule.core.Predicate;
import com.jukta.rule.core.predicate.*;

/**
 * @since 1.0
 */
public class StringPredicateBuilder implements PredicateBuilder<String> {

    public Predicate<String> predicate(String exp) {
        exp = exp.trim();
        if (exp.equals("*")) {
            return new AnyPredicate();
        } else if (exp.contains("*")) {
            return new StringWildcardPredicate(exp);
        } else {
            return new StringPredicate(exp);
        }
    }

    public static StringPredicateBuilder builder() {
        return new StringPredicateBuilder();
    }

}
