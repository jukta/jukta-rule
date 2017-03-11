package com.jukta.rule.core.predicate;

import com.jukta.rule.core.Predicate;

/**
 * @since 1.0
 */
public class ComparablePredicate<T extends Comparable<T>> implements Predicate<T> {
    private T obj;
    private Operator operator;

    public ComparablePredicate(T obj, Operator operator) {
        this.obj = obj;
        if (operator == null) {
            throw new IllegalArgumentException("Operator cannot be null");
        }
        this.operator = operator;
    }

    @Override
    public boolean eval(T obj) {
        return operator.calc(obj.compareTo(this.obj));
    }

    @Override
    public int getRank() {
        return 1;
    }
}
