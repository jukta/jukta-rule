package com.jukta.rule.core.predicate;

import java.util.Comparator;


public class NumberComparator implements Comparator<Number> {
    @Override
    public int compare(Number n1, Number n2) {
        long l1 = n1.longValue();
        long l2 = n2.longValue();
        if (l1 != l2) {
            return (l1 < l2 ? -1 : 1);
        }
        return Double.compare(n1.doubleValue(), n2.doubleValue());
    }
}
