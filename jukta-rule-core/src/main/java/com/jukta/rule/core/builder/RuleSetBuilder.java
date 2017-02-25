package com.jukta.rule.core.builder;

import com.jukta.rule.core.ResultFactory;
import com.jukta.rule.core.ValueExtractor;
import com.jukta.rule.core.impl.DefaultRuleSet;
import com.jukta.rule.core.impl.PluralRuleSet;

import java.util.ArrayList;
import java.util.List;

/**
 * @since 1.0
 */
public class RuleSetBuilder<I, O> {
    private String name;
    private List<ValueExtractor<I>> extractors;
    private Class<I> inType;
    private Class<O> outType;
    private ResultFactory<I, O> initialFactory;
    private boolean plural;
    private List<Integer> ranks;

    private RuleSetBuilder(String name, boolean plural, Class<I> inType, Class<O> outType) {
        this.name = name;
        extractors = new ArrayList<>();
        ranks = new ArrayList<>();
        this.plural = plural;
        this.inType = inType;
        this.outType = outType;
    }

    public RuleSetBuilder<I, O> addField(String fieldName, ValueExtractor<I> valueExtractor, int rank) {
        extractors.add(valueExtractor);
        ranks.add(rank);
        return this;
    }

    public RuleSetBuilder<I, O> setInitialFactory(ResultFactory<I, O> initialFactory) {
        this.initialFactory = initialFactory;
        return this;
    }

    public DefaultRuleSet<I, O> build() {
        DefaultRuleSet<I, O> result;
        if (plural) {
            if (initialFactory == null) {
                throw new IllegalStateException("Initial factory is required for plural rule set");
            }
            result = new PluralRuleSet<>(extractors, ranks, initialFactory, inType, outType);
        } else {
            result = new DefaultRuleSet<>(extractors, ranks, inType, outType);
            result.setInitialFactory(initialFactory);
        }
        return result;
    }

    public static <I, O> RuleSetBuilder<I, O> singleRuleSet(String name, Class<I> inType, Class<O> outType) {
        return new RuleSetBuilder<>(name, false, inType, outType);
    }

    public static <I, O> RuleSetBuilder<I, O> pluralRuleSet(String name, Class<I> inType, Class<O> outType) {
        return new RuleSetBuilder<>(name, true, inType, outType);
    }

}
