package com.jukta.rule.core;

/**
 * @since 1.0
 */
public interface ValueExtractor<T, V> {

    V extract(T t);

    Class<V> getValueType();

}
