package com.jukta.rule.core.impl;

import com.jukta.rule.core.ValueExtractor;
import com.jukta.rule.core.builder.RuleBuilder;
import com.jukta.rule.core.builder.RuleSetBuilder;
import com.jukta.rule.core.predicate.AnyPredicate;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * @since 1.0
 */
public class PluralRuleSetTest {

    DefaultRuleSet<String[], String> ruleSet;

    @Before
    public void setup() {
        ruleSet = RuleSetBuilder
                .pluralRuleSet("test", String[].class, String.class)
                .addField("f1", new ValueExtractor<String[], String>() {
                    @Override
                    public String extract(String[] strings) {
                        return strings[0];
                    }

                    @Override
                    public Class<String> getValueType() {
                        return String.class;
                    }
                }, 1)
                .addField("f2", new ValueExtractor<String[], String>() {
                    @Override
                    public String extract(String[] strings) {
                        return strings[1];
                    }

                    @Override
                    public Class<String> getValueType() {
                        return String.class;
                    }
                }, 0)
                .setInitialFactory((strings, o) -> "A")
                .build();
    }

    @Test
    public void factoryChain(){

        RuleBuilder.rule("", ruleSet)
                .addPredicate("f1", new AnyPredicate())
                .addPredicate("f2", new AnyPredicate())
                .setResultFactory((strings, o) -> o + "a")
                .build();

        RuleBuilder.rule("", ruleSet)
                .addPredicate("f1", new AnyPredicate())
                .addPredicate("f2", new AnyPredicate())
                .setResultFactory((strings, o) -> o + "b")
                .build();

        assertEquals(ruleSet.eval(new String[]{"a", "a"}), "Aab");
    }

}
