package com.jukta.rule.core.builder;

import com.jukta.rule.core.Predicate;

/**
 * @since 1.0
 */
public interface PredicateBuilder<T> {

    Predicate<T> predicate(String exp);

}
