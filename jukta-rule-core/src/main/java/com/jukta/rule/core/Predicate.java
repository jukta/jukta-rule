package com.jukta.rule.core;

/**
 * @since 1.0
 */
public interface Predicate<T> {

    /**
     * Boolean function to evaluate single input value
     * @param t value to be evaluated
     * @return true if value matches the predicate
     */
    boolean eval(T t);

    /**
     * Rank used to calculate rule weight
     * @return rank value
     */
    int getRank();

    /**
     * @return type of evaluating value
     */
    default Class<T> getType() {
        return null;
    }

}
