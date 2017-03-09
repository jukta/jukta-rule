package com.jukta.rule.core.impl;

import com.jukta.rule.core.builder.PredicateBuilder;
import com.jukta.rule.core.builder.RuleBuilder;
import com.jukta.rule.core.builder.RuleSetBuilder;
import com.jukta.rule.core.predicate.AnyPredicate;
import com.jukta.rule.core.predicate.NumberPredicates.*;
import com.jukta.rule.core.predicate.StringPredicate;
import org.junit.Before;
import org.junit.Test;

import java.text.ParseException;

import static org.junit.Assert.assertEquals;

/**
 * @since 1.0
 */
public class DefaultRuleSetTest {

    DefaultRuleSet<String[], String> ruleSet;

    @Before
    public void setup() {
        ruleSet = RuleSetBuilder
                .singleRuleSet("test", String[].class, String.class)
                .addField("f1", o -> o[0], 1)
                .addField("f2", o -> o[1], 0)
                .addField("f3", new NumberValueExtractor(2), 0)
                .addField("f4", o -> o[3], 0)
                .addField("f5", o -> o[4], 0)
                .addField("f6", o -> o[5], 0)
                .addField("f7", o -> o[6], 0)
                .addField("f8", new NumberValueExtractor(7), 0)
                .build();
    }

    @Test
    public void simple() throws Exception {

        RuleBuilder.rule("", ruleSet)
                .addPredicate("f1", new AnyPredicate())
                .addPredicate("f2", new StringPredicate("a"))
                .addPredicate("f3", PredicateBuilder.numberPredicate("==","2"))
                .addPredicate("f4", new NumberGreaterPredicate("1"))
                .addPredicate("f5", new NumberLessPredicate("2"))
                .addPredicate("f6", new NumberLessOrEqualsPredicate("1"))
                .addPredicate("f7", new NumberNotEqualsPredicate("1"))
                .addPredicate("f8", PredicateBuilder.numberPredicate(">=", "1"))
                .setResultFactory((strings, o) -> "a")
                .build();

        RuleBuilder.rule("", ruleSet)
                .addPredicate("f1", new AnyPredicate())
                .addPredicate("f2", new StringPredicate("b"))
                .addPredicate("f3", PredicateBuilder.numberPredicate("==","1"))
                .addPredicate("f4", new NumberGreaterPredicate("2"))
                .addPredicate("f4", new NumberLessPredicate("2"))
                .addPredicate("f4", new NumberLessOrEqualsPredicate("2"))
                .addPredicate("f4", new NumberNotEqualsPredicate("2"))
                .addPredicate("f4", PredicateBuilder.numberPredicate(">=", "2"))
                .setResultFactory((strings, o) -> "b")
                .build();

        assertEquals(ruleSet.eval(new String[]{"", "a", "2", "2","1","1","2","1"}), "a");
    }

    @Test
    public void predicateRank() throws ParseException {

        RuleBuilder.rule("", ruleSet)
                .addPredicate("f1", new AnyPredicate())
                .addPredicate("f2", new AnyPredicate())
                .addPredicate("f3", new AnyPredicate())
                .addPredicate("f4", new AnyPredicate())
                .addPredicate("f5", new AnyPredicate())
                .addPredicate("f6", new AnyPredicate())
                .addPredicate("f7", new AnyPredicate())
                .addPredicate("f8", new AnyPredicate())
                .setResultFactory((strings, o) -> "a")
                .build();

        RuleBuilder.rule("", ruleSet)
                .addPredicate("f1", new StringPredicate("a"))
                .addPredicate("f2", new AnyPredicate())
                .addPredicate("f3", new AnyPredicate())
                .addPredicate("f4", new AnyPredicate())
                .addPredicate("f5", new AnyPredicate())
                .addPredicate("f6", new AnyPredicate())
                .addPredicate("f7", new AnyPredicate())
                .addPredicate("f8", new AnyPredicate())
                .setResultFactory((strings, o) -> "b")
                .build();

        assertEquals(ruleSet.eval(new String[]{"a", "a", "a", "a", "a", "a", "a", "a"}), "b");
    }

    @Test
    public void fieldRank() throws ParseException {

        RuleBuilder.rule("", ruleSet)
                .addPredicate("f1", new AnyPredicate())
                .addPredicate("f2", new StringPredicate("a"))
                .addPredicate("f3", new AnyPredicate())
                .addPredicate("f4", new AnyPredicate())
                .addPredicate("f5", new AnyPredicate())
                .addPredicate("f6", new AnyPredicate())
                .addPredicate("f7", new AnyPredicate())
                .addPredicate("f8", new AnyPredicate())
                .setResultFactory((strings, o) -> "a")
                .build();

        RuleBuilder.rule("", ruleSet)
                .addPredicate("f1", new StringPredicate("a"))
                .addPredicate("f2", new AnyPredicate())
                .addPredicate("f3", new AnyPredicate())
                .addPredicate("f4", new AnyPredicate())
                .addPredicate("f5", new AnyPredicate())
                .addPredicate("f6", new AnyPredicate())
                .addPredicate("f7", new AnyPredicate())
                .addPredicate("f8", new AnyPredicate())
                .setResultFactory((strings, o) -> "b")
                .build();

        assertEquals(ruleSet.eval(new String[]{"a","a","","","","","",""}), "b");
    }

    @Test(expected = RuntimeException.class)
    public void ambiguousRules() throws ParseException {

        RuleBuilder.rule("", ruleSet)
                .addPredicate("f1", new AnyPredicate())
                .addPredicate("f2", new AnyPredicate())
                .setResultFactory((strings, o) -> "a")
                .build();

        RuleBuilder.rule("", ruleSet)
                .addPredicate("f1", new AnyPredicate())
                .addPredicate("f2", new AnyPredicate())
                .setResultFactory((strings, o) -> "b")
                .build();

        ruleSet.eval(new String[]{"a", "a"});
    }

    @Test
    public void initialFactory() throws ParseException {

        RuleBuilder.rule("", ruleSet)
                .addPredicate("f1", new AnyPredicate())
                .addPredicate("f2", new AnyPredicate())
                .addPredicate("f3", new AnyPredicate())
                .addPredicate("f4", new AnyPredicate())
                .addPredicate("f5", new AnyPredicate())
                .addPredicate("f6", new AnyPredicate())
                .addPredicate("f7", new AnyPredicate())
                .addPredicate("f8", new AnyPredicate())
                .setResultFactory((strings, o) -> o + "a")
                .build();

        ruleSet.setInitialFactory((strings, o) -> "A");
        assertEquals(ruleSet.eval(new String[]{"a","a","","","","","",""}), "Aa");
    }
}
