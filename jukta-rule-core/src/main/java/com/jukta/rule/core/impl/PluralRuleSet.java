package com.jukta.rule.core.impl;

import com.jukta.rule.core.ResultFactory;
import com.jukta.rule.core.ValueExtractor;

import java.text.ParseException;
import java.util.List;

/**
 * @since 1.0
 */
public class PluralRuleSet<I, O> extends DefaultRuleSet<I, O> {

    public PluralRuleSet(List<ValueExtractor<I>> extractors, List<Integer> ranks, ResultFactory<I, O> initialFactory, Class<I> inType, Class<O> outType) {
        super(extractors, ranks, inType, outType);
        setInitialFactory(initialFactory);
    }

    @Override
    public O eval(I i) throws ParseException {
        List<Rule<I, O>> res = filter(i);
        O result =  getInitialFactory().create(i, null);
        for (Rule<I, O> rule : res) {
            result = rule.getResultFactory().create(i, result);
        }
        return result;
    }

}
