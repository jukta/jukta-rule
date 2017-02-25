package com.jukta.rule.core.impl;

import com.jukta.rule.core.Predicate;
import com.jukta.rule.core.ResultFactory;

import java.util.List;

/**
 * @since 1.0
 */
public class Rule<I, O> {
    private List<Predicate> predicates;
    private ResultFactory<I, O> resultFactory;

    public Rule(List<Predicate> predicates, ResultFactory<I, O> resultFactory) {
        this.predicates = predicates;
        this.resultFactory = resultFactory;
    }

    public List<Predicate> getPredicates() {
        return predicates;
    }

    public ResultFactory<I, O> getResultFactory() {
        return resultFactory;
    }
}
