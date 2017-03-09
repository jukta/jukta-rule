package com.jukta.rule.core.predicate.NumberPredicates;

import com.jukta.rule.core.Predicate;

/**
 * Created by JSLO on 09.03.2017.
 */
public abstract class NumberPredicate implements Predicate<Number> {
    protected int compare(Number n1, Number n2) {
        long l1 = n1.longValue();
        long l2 = n2.longValue();
        if (l1 != l2) {
            return (l1 < l2 ? -1 : 1);
        }
        return Double.compare(n1.doubleValue(), n2.doubleValue());
    }
}
