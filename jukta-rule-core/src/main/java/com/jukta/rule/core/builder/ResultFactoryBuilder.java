package com.jukta.rule.core.builder;

import com.jukta.rule.core.ResultFactory;

/**
 * @since 1.0
 */
public interface ResultFactoryBuilder<I,O> {

    ResultFactory<I,O> factory(String exp);

}
