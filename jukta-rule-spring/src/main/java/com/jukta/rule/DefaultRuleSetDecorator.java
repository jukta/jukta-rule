package com.jukta.rule;

import com.jukta.rule.core.Predicate;
import com.jukta.rule.core.ResultFactory;
import com.jukta.rule.core.RuleSet;
import com.jukta.rule.core.builder.PredicateBuilder;
import com.jukta.rule.core.builder.ResultFactoryBuilder;
import com.jukta.rule.core.builder.RuleBuilder;
import com.jukta.rule.core.impl.DefaultRuleSet;

import java.util.List;

/**
 * @since 1.0
 */
public class DefaultRuleSetDecorator<I,O> implements RuleSet<I,O> {

    private List<PredicateBuilder> predicateBuilderList;
    private ResultFactoryBuilder<I,O> resultFactoryBuilder;
    private RuleSet<I,O> ruleSet;

    public DefaultRuleSetDecorator(RuleSet<I,O> ruleSet) {
        this.ruleSet = ruleSet;
    }

    public void setPredicateBuilderList(List<PredicateBuilder> predicateBuilderList) {
        this.predicateBuilderList = predicateBuilderList;
    }

    public void setResultFactoryBuilder(ResultFactoryBuilder<I, O> resultFactoryBuilder) {
        this.resultFactoryBuilder = resultFactoryBuilder;
    }

    public void addRule(List<String> rule, String resultExpression) {
        RuleBuilder ruleBuilder = RuleBuilder.rule("", getInType(), getOutType());
        for (int i = 0; i < predicateBuilderList.size(); i++) {
            PredicateBuilder predicateBuilder = predicateBuilderList.get(i);
            Predicate predicate = predicateBuilder.predicate(rule.get(i));
            ruleBuilder.addPredicate("", predicate);
        }
        ResultFactory resultFactory = resultFactoryBuilder.factory(resultExpression);
        ruleBuilder.setResultFactory(resultFactory);
        ruleBuilder.build((DefaultRuleSet) ruleSet);
    }

    @Override
    public O eval(I i) {
        return ruleSet.eval(i);
    }

    @Override
    public Class<I> getInType() {
        return ruleSet.getInType();
    }

    @Override
    public Class<O> getOutType() {
        return ruleSet.getOutType();
    }
}
