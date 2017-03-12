package com.jukta.rule.core.builder;


import com.jukta.rule.core.Predicate;
import com.jukta.rule.core.predicate.AnyPredicate;
import com.jukta.rule.core.predicate.ComparatorPredicate;
import com.jukta.rule.core.predicate.NumberComparator;
import com.jukta.rule.core.predicate.Operator;

import java.text.NumberFormat;
import java.text.ParseException;

public class NumberPredicateBuilder implements PredicateBuilder<Number> {
    @Override
    public Predicate<Number> predicate(String exp) {
        exp = exp.trim();

        if ("*".equals(exp)) return new AnyPredicate();

        Operator operator = Operator.parse(exp);
        exp = exp.substring(operator.getOp().length()).trim();
        try {
            Number number = NumberFormat.getInstance().parse(exp);
            return new ComparatorPredicate<>(number, new NumberComparator(), operator);
        } catch (ParseException e) {
            throw new IllegalArgumentException("Number parse error for expression " + exp, e);
        }
    }
}
