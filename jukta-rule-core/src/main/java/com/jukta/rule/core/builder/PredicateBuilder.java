package com.jukta.rule.core.builder;

import com.jukta.rule.core.Predicate;
import com.jukta.rule.core.predicate.AnyPredicate;
import com.jukta.rule.core.predicate.NumberPredicates.NumberEqualsPredicate;
import com.jukta.rule.core.predicate.NumberPredicates.NumberGreaterOrEqualsPredicate;
import com.jukta.rule.core.predicate.StringPredicate;
import com.jukta.rule.core.predicate.StringWildcardPredicate;

import java.text.NumberFormat;
import java.text.ParseException;

/**
 * @since 1.0
 */
public class PredicateBuilder {

    public static Predicate<String> stringPredicate(String exp) {
        exp = exp.trim();
        if (exp.equals("*")) {
            return new AnyPredicate();
        } else if (exp.contains("*")) {
            return new StringWildcardPredicate(exp);
        } else {
            return new StringPredicate(exp);
        }
    }

    public static Predicate<Number> numberPredicate(String exp, String value) throws Exception {
        exp = exp.trim();
        Number num = null;
        try {
            num = NumberFormat.getInstance().parse(value);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        switch(exp){
            case "==":
                return new NumberEqualsPredicate(num);
            case ">=":
                return new NumberGreaterOrEqualsPredicate(num);
        }
        throw new Exception("");
    }
}
