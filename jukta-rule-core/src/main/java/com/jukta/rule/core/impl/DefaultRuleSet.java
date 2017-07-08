package com.jukta.rule.core.impl;

import com.jukta.rule.core.Predicate;
import com.jukta.rule.core.ResultFactory;
import com.jukta.rule.core.RuleSet;
import com.jukta.rule.core.ValueExtractor;

import java.util.ArrayList;
import java.util.List;
import java.util.StringJoiner;

/**
 * @since 1.0
 */
public class DefaultRuleSet<I, O> implements RuleSet<I, O> {

    private List<ValueExtractor<I, ?>> extractors;
    private List<Rule<I, O>> rules;
    private Class<I> inType;
    private Class<O> outType;
    private ResultFactory<I, O> initialFactory;
    private List<Integer> ranks;
    private DefaultRulesContainer<I> container = new DefaultRulesContainer<>();

    public DefaultRuleSet(List<ValueExtractor<I, ?>> extractors, List<Integer> ranks, Class<I> inType, Class<O> outType) {
        if (extractors.size() != ranks.size()) {
            throw new IllegalStateException("Number of extractors must be the same as number of ranks");
        }
        this.extractors = extractors;
        rules = new ArrayList<>();
        this.inType = inType;
        this.outType = outType;
        this.ranks = ranks;
    }

    public Class<I> getInType() {
        return inType;
    }

    public Class<O> getOutType() {
        return outType;
    }

    public List<ValueExtractor<I, ?>> getExtractors() {
        return extractors;
    }

    public void addRule(Rule<I, O> rule) {
        List<Predicate> p = rule.getPredicates();
        if (extractors.size() != p.size()) {
            throw new IllegalStateException("Unexpected number of predicates, expected " + extractors.size() + " found " + p.size());
        }
        for (int i = 0; i < extractors.size(); i++) {
            Predicate pr = rule.getPredicates().get(i);
            if (pr.getType() != null && !extractors.get(i).getValueType().equals(pr.getType())) {
                throw new IllegalArgumentException("Unexpected predicate type, expected " + extractors.get(i).getValueType() + " found " + pr.getType());
            }
        }
        rules.add(rule);
    }

    public void setInitialFactory(ResultFactory<I, O> initialFactory) {
        this.initialFactory = initialFactory;
    }

    public ResultFactory<I, O> getInitialFactory() {
        return initialFactory;
    }

    public List<Integer> getRanks() {
        return ranks;
    }

    @Override
    public O eval(I i) {
        List<Rule<I, O>> res = filter(i);
        res = filterByRank(res);
        if (res.size() > 1) {
            StringJoiner joiner = new StringJoiner(",");
            for (Rule<I, O> r : res) {
                joiner.add(r.toString());
            }
            throw new AmbiguousRulesException("Ambiguous rules: " + joiner.toString());
        } else if (res.size() != 1) {
            return null;
        }
        Rule<I, O> rule = res.get(0);
        O result = null;
        if (initialFactory != null) {
            result = initialFactory.create(i, null);
        }
        return rule.getResultFactory().create(i, result);
    }

    protected List<Rule<I, O>> filterByRank(List<Rule<I, O>> rules) {
        if (rules == null || rules.size() <= 1) return rules;
        int gRank = 0;
        List<Rule<I, O>> res = new ArrayList<>();
        for (Rule<I, O> r : rules) {
            int rank = 0;
            for (int i = 0; i < r.getPredicates().size(); i++) {
                rank += Math.pow(2, r.getPredicates().get(i).getRank() * ranks.get(i));
            }
            if (rank > gRank) {
                res.clear();
                res.add(r);
                gRank = rank;
            } else if (rank == gRank) {
                res.add(r);
            }
        }
        return res;
    }

    protected List<Rule<I, O>> filter(I i) {
        List<Object> values = new ArrayList<>(extractors.size());
        for (ValueExtractor<I, ?> extractor : extractors) {
            values.add(extractor.extract(i));
        }
        EvalContext<I, O> context = new EvalContext<>(rules, values, i);

        container.eval(context);
        return context.getResult();
    }


}
