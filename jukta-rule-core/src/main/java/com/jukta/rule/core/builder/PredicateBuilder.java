package com.jukta.rule.core.builder;

import com.jukta.rule.core.Predicate;
import com.jukta.rule.core.predicate.AnyPredicate;
import com.jukta.rule.core.predicate.StringPredicate;
import com.jukta.rule.core.predicate.StringWildcardPredicate;

/**
 * @since 1.0
 */
public class PredicateBuilder {

    public static Predicate<String> stringPredicate(String exp) {
        exp = exp.trim();
        if (exp.equals("*")) {
            return new AnyPredicate();
        } else if (exp.contains("*")) {
            return new StringWildcardPredicate(exp);
        } else {
            return new StringPredicate(exp);
        }
    }

}
