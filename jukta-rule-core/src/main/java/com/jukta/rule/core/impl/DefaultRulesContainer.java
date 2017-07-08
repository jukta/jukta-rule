package com.jukta.rule.core.impl;

import com.jukta.rule.core.Predicate;

import java.util.*;

/**
 * @since 1.0
 */
public class DefaultRulesContainer<T> implements RulesContainer<T> {

    @Override
    public void eval(EvalContext context) {
        int size = context.getValues().size();
        List<Rule<?, ?>> r = new LinkedList<>(context.getRules());

        for (int j = 0; j < size; j++) {
            Object o = context.getValues().get(j);

            for (Iterator<Rule<?, ?>> it = r.iterator(); it.hasNext(); ) {
                Rule<?, ?> rule = it.next();
                List<Predicate> pList = rule.getPredicates();
                Predicate p = pList.get(j);
                if (!check(p, o)) {
                    it.remove();
                }
            }
        }
        context.setResult(r);
    }

    private boolean check(Predicate p, Object o) {
        return p.eval(o);
    }

}
