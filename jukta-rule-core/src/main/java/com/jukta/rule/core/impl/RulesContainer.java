package com.jukta.rule.core.impl;

import java.util.List;

/**
 * @since 1.0
 */
public interface RulesContainer<T> {

    void eval(EvalContext context);

}
