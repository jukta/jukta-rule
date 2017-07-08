package com.jukta.rule.core;

/**
 * @since 1.0
 */
public interface ResultFactory<I, O> {

    /**
     * Creates or updates output object using input object
     *
     * @param i input object
     * @param o output object
     * @return enriched output object
     */
    O create(I i, O o);

}
