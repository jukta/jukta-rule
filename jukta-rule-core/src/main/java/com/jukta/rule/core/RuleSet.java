package com.jukta.rule.core;

/**
 * @since 1.0
 */
public interface RuleSet<I, O> {

    O eval(I i);

    public Class<I> getInType();

    public Class<O> getOutType();

}
