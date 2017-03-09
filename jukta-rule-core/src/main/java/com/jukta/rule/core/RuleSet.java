package com.jukta.rule.core;

import java.text.ParseException;

/**
 * @since 1.0
 */
public interface RuleSet<I, O> {

    O eval(I i) throws ParseException;

}
