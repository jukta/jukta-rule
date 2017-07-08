package com.jukta.rule.core;

/**
 * @since 1.0
 */
public interface RuleSet<I, O> {

    /**
     * Applies rule set to input object and build output
     * @param i input object
     * @return output object
     */
    O eval(I i);

    /**
     * @return type of input object
     */
    Class<I> getInType();

    /**
     * @return type of output object
     */
    Class<O> getOutType();

}
