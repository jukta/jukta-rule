package com.jukta.rule.core;

/**
 * @since 1.0
 */
public interface ResultFactory<I, O> {

    O create(I i, O o);

}
