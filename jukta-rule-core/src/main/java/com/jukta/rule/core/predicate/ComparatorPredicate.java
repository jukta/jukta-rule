package com.jukta.rule.core.predicate;

import com.jukta.rule.core.Predicate;

import java.util.Comparator;

/**
 * @since 1.0
 */
public class ComparatorPredicate<T> implements Predicate<T> {
    private T obj;
    private Comparator<T> comparator;
    private Operator operator;

    public ComparatorPredicate(T obj, Comparator<T> comparator, Operator operator) {
        this.obj = obj;
        if (comparator == null) {
            throw new IllegalArgumentException("Comparator cannot be null");
        }
        if (operator == null) {
            throw new IllegalArgumentException("Operator cannot be null");
        }
        this.comparator = comparator;
        this.operator = operator;
    }

    @Override
    public boolean eval(T obj) {
        int c = comparator.compare(this.obj, obj);
        return operator.calc(c);
    }

    @Override
    public int getRank() {
        return 1;
    }
}
