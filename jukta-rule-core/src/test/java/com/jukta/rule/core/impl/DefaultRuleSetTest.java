package com.jukta.rule.core.impl;

import com.jukta.rule.core.builder.RuleBuilder;
import com.jukta.rule.core.builder.RuleSetBuilder;
import com.jukta.rule.core.predicate.AnyPredicate;
import com.jukta.rule.core.predicate.StringPredicate;
import org.junit.Before;
import org.junit.Test;

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
                .build();
    }

    @Test
    public void simple() {

        RuleBuilder.rule("", ruleSet)
                .addPredicate("f1", new AnyPredicate())
                .addPredicate("f2", new StringPredicate("a"))
                .setResultFactory((strings, o) -> "a")
                .build();

        RuleBuilder.rule("", ruleSet)
                .addPredicate("f1", new AnyPredicate())
                .addPredicate("f2", new StringPredicate("b"))
                .setResultFactory((strings, o) -> "b")
                .build();

        assertEquals(ruleSet.eval(new String[]{"", "a"}), "a");
    }

    @Test
    public void predicateRank() {

        RuleBuilder.rule("", ruleSet)
                .addPredicate("f1", new AnyPredicate())
                .addPredicate("f2", new AnyPredicate())
                .setResultFactory((strings, o) -> "a")
                .build();

        RuleBuilder.rule("", ruleSet)
                .addPredicate("f1", new StringPredicate("a"))
                .addPredicate("f2", new AnyPredicate())
                .setResultFactory((strings, o) -> "b")
                .build();

        assertEquals(ruleSet.eval(new String[]{"a", "a"}), "b");
    }

    @Test
    public void fieldRank() {

        RuleBuilder.rule("", ruleSet)
                .addPredicate("f1", new AnyPredicate())
                .addPredicate("f2", new StringPredicate("a"))
                .setResultFactory((strings, o) -> "a")
                .build();

        RuleBuilder.rule("", ruleSet)
                .addPredicate("f1", new StringPredicate("a"))
                .addPredicate("f2", new AnyPredicate())
                .setResultFactory((strings, o) -> "b")
                .build();

        assertEquals(ruleSet.eval(new String[]{"a", "a"}), "b");
    }

    @Test(expected = RuntimeException.class)
    public void ambiguousRules() {

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
    public void initialFactory() {

        RuleBuilder.rule("", ruleSet)
                .addPredicate("f1", new AnyPredicate())
                .addPredicate("f2", new AnyPredicate())
                .setResultFactory((strings, o) -> o + "a")
                .build();

        ruleSet.setInitialFactory((strings, o) -> "A");
        assertEquals(ruleSet.eval(new String[]{"a", "a"}), "Aa");
    }

}
