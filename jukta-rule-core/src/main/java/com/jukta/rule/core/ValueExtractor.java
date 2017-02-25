package com.jukta.rule.core;

/**
 * @since 1.0
 */
public interface ValueExtractor<T> {

    Object extract(T t);

}
