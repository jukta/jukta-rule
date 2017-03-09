package com.jukta.rule.core.impl;

import com.jukta.rule.core.Predicate;
import com.jukta.rule.core.ResultFactory;
import com.jukta.rule.core.RuleSet;
import com.jukta.rule.core.ValueExtractor;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * @since 1.0
 */
public class DefaultRuleSet<I, O> implements RuleSet<I, O> {

    private List<ValueExtractor<I>> extractors;
    private List<Rule<I, O>> rules;
    private Class<I> inType;
    private Class<O> outType;
    private ResultFactory<I, O> initialFactory;
    private List<Integer> ranks;

    public DefaultRuleSet(List<ValueExtractor<I>> extractors, List<Integer> ranks, Class<I> inType, Class<O> outType) {
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

    public void addRule(Rule<I, O> rule) {
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
    public O eval(I i) throws ParseException {
        List<Rule<I, O>> res = filter(i);
        res = filterByRank(res);
        if (res.size() > 1) {
            throw new RuntimeException("Ambiguous rules");
        }
        if (res.size() != 1) {
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

    protected List<Rule<I, O>> filter(I i) throws ParseException {
        int size = extractors.size();
        List<Rule<I, O>> r = new ArrayList<>(rules);
        for (int j = 0; j < size; j++) {
            ValueExtractor<I> extractor = extractors.get(j);
            Object o = extractor.extract(i);

            for (Iterator<Rule<I, O>> it = r.iterator(); it.hasNext(); ) {
                Rule<I, O> rule = it.next();
                List<Predicate> p = rule.getPredicates();
                if (!p.get(j).eval(o)) {
                    it.remove();
                }
            }
        }
        return r;
    }


}
