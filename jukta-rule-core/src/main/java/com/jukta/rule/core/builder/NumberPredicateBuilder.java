package com.jukta.rule.core.builder;


import com.jukta.rule.core.Predicate;
import com.jukta.rule.core.predicate.*;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.text.ParseException;

public class NumberPredicateBuilder<T extends Number> implements PredicateBuilder<T> {

    private Class<T> type;
    private T epsilon;

    public NumberPredicateBuilder(Class<T> type) {
        this.type = type;
    }

    public NumberPredicateBuilder(Class<T> type, T epsilon) {
        this(type);
        this.epsilon = epsilon;
    }

    @Override
    public Predicate<T> predicate(String exp) {
        exp = exp.trim();

        if ("*".equals(exp)) return new AnyPredicate();

        Operator operator = Operator.parse(exp);
        exp = exp.substring(operator.getOp().length()).trim();
        if (Double.class.equals(type)) {
            return new ComparatorPredicate(Double.valueOf(exp), new DoubleComparator((Double) epsilon), operator);
        } else if (BigDecimal.class.equals(type)) {
            return new ComparatorPredicate(new BigDecimal(exp), new BigDecimalComparator((BigDecimal) epsilon), operator);
        } else if (BigInteger.class.equals(type)) {
            return new ComparablePredicate(new BigInteger(exp), operator);
        } else if (Comparable.class.isAssignableFrom(type)) {
            if (Integer.class.equals(type)) {
                return new ComparablePredicate(Integer.parseInt(exp), operator);
            } else if (Long.class.equals(type)) {
                return new ComparablePredicate(Long.parseLong(exp), operator);
            } else if (Byte.class.equals(type)) {
                return new ComparablePredicate(Byte.parseByte(exp), operator);
            } else if (Float.class.equals(type)) {
                return new ComparablePredicate(Float.parseFloat(exp), operator);
            } else if (Short.class.equals(type)) {
                return new ComparablePredicate(Short.parseShort(exp), operator);
            }
        }
        throw new IllegalArgumentException("Type " + type.getClass() + " is not supported");
    }

    public static <T extends Number> NumberPredicateBuilder<T> builder(Class<T> type) {
        return new NumberPredicateBuilder<T>(type);
    }

    public static <T extends Number> NumberPredicateBuilder<T> builder(Class<T> type, T epsilon) {
        return new NumberPredicateBuilder<T>(type, epsilon);
    }
}
