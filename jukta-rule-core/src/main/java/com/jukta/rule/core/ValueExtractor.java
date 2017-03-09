package com.jukta.rule.core;

import java.text.ParseException;

/**
 * @since 1.0
 */
public interface ValueExtractor<T> {

    Object extract(T t) throws ParseException;

}
