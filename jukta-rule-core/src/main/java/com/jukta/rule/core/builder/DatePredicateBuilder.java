package com.jukta.rule.core.builder;

import com.jukta.rule.core.Predicate;
import com.jukta.rule.core.predicate.AnyPredicate;
import com.jukta.rule.core.predicate.ComparablePredicate;
import com.jukta.rule.core.predicate.Operator;

import java.text.DateFormat;
import java.text.ParseException;
import java.util.Date;

/**
 * @since 1.0
 */
public class DatePredicateBuilder implements PredicateBuilder<Date> {
    private DateFormat dateFormat;

    private DatePredicateBuilder(DateFormat dateFormat) {
        this.dateFormat = dateFormat;
    }

    public Predicate<Date> predicate(String exp) {
        exp = exp.trim();

        if ("*".equals(exp)) return new AnyPredicate();

        Operator operator = Operator.parse(exp);
        exp = exp.substring(operator.getOp().length()).trim();
        try {
            Date date = dateFormat.parse(exp);
            return new ComparablePredicate<>(date, operator);
        } catch (ParseException e) {
            throw new IllegalArgumentException("Date parse error for expression " + exp, e);
        }
    }

    public static DatePredicateBuilder builder(DateFormat dateFormat) {
        return new DatePredicateBuilder(dateFormat);
    }

}
