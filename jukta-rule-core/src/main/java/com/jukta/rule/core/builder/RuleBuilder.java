package com.jukta.rule.core.builder;

import com.jukta.rule.core.Predicate;
import com.jukta.rule.core.ResultFactory;
import com.jukta.rule.core.impl.DefaultRuleSet;
import com.jukta.rule.core.impl.Rule;

import java.util.ArrayList;
import java.util.List;

/**
 * @since 1.0
 */
public class RuleBuilder<I, O> {
    private String name;
    private List<Predicate> predicates;
    private ResultFactory<I, O> resultFactory;
    private Class<I> inType;
    private Class<O> outType;
    private DefaultRuleSet<I, O> ruleSet;
    private int predicateCount = 0;

    private RuleBuilder(String name, Class<I> inType, Class<O> outType) {
        this.name = name;
        predicates = new ArrayList<>();
        this.inType = inType;
        this.outType = outType;
    }

    private void setRuleSet(DefaultRuleSet<I, O> ruleSet) {
        this.ruleSet = ruleSet;
    }

    public RuleBuilder<I, O> addPredicate(String fieldName, Predicate predicate) {
        predicates.add(predicate);
        predicateCount++;
        return this;
    }

    public RuleBuilder<I, O> setResultFactory(ResultFactory<I, O> resultFactory) {
        this.resultFactory = resultFactory;
        return this;
    }

    public Rule<I, O> build() {
        return build(ruleSet);
    }

    public Rule<I, O> build(DefaultRuleSet<I, O> ruleSet) {
        Rule<I, O> r = new Rule<>(name, predicates, resultFactory);
        if (ruleSet != null) {
            ruleSet.addRule(r);
        }
        return r;
    }

    public static <I, O> RuleBuilder<I, O> rule(String name, Class<I> inType, Class<O> outType) {
        return new RuleBuilder<>(name, inType, outType);
    }

    public static <I, O> RuleBuilder<I, O> rule(String name, DefaultRuleSet<I, O> ruleSet) {
        RuleBuilder<I, O> ruleBuilder = new RuleBuilder<>(name, ruleSet.getInType(), ruleSet.getOutType());
        ruleBuilder.setRuleSet(ruleSet);
        return ruleBuilder;
    }

}
