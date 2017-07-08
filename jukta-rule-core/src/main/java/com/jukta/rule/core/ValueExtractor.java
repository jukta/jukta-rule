package com.jukta.rule.core;

/**
 * @since 1.0
 */
public interface ValueExtractor<T, V> {

    /**
     * Extract column value from input object
     * @param t input object
     * @return extracted value
     */
    V extract(T t);

    /**
     * @return type of extracted value
     */
    Class<V> getValueType();

}
