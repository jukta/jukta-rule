package com.jukta.rule.core.predicate;

import org.junit.Test;
import static org.junit.Assert.*;
/**
 * @since 1.0
 */
public class StringWildcardPredicateTest {

    @Test
    public void simpleWildcardTest() {
        StringWildcardPredicate a = new StringWildcardPredicate("a*b");
        assertTrue(a.eval("aab"));
        assertTrue(a.eval("aasdfjklasdfb"));
        assertFalse(a.eval("abc"));
    }

    @Test
    public void startsWildcardTest() {
        StringWildcardPredicate a = new StringWildcardPredicate("*abc");
        assertTrue(a.eval("abc"));
        assertTrue(a.eval("abcabc"));
        assertTrue(a.eval("1abc"));
        assertFalse(a.eval("abc1"));
    }

    @Test
    public void endsWildcardTest() {
        StringWildcardPredicate a = new StringWildcardPredicate("abc*");
        assertTrue(a.eval("abc"));
        assertTrue(a.eval("abcabc"));
        assertTrue(a.eval("abc1"));
        assertFalse(a.eval("1abc"));
    }

    @Test
    public void multiWildcardTest() {
        StringWildcardPredicate a = new StringWildcardPredicate("a*b*c");
        assertTrue(a.eval("abc"));
        assertTrue(a.eval("a1b1c"));
        assertTrue(a.eval("aabcbabcc"));
        assertFalse(a.eval("babcbabcc"));
    }

    @Test
    public void symbolsWildcardTest() {
        StringWildcardPredicate a = new StringWildcardPredicate("^ab*c?");
        assertTrue(a.eval("^abc?"));
        assertTrue(a.eval("^abc1"));
        assertTrue(a.eval("^ab2c1"));
    }

    @Test
    public void escapingWildcardTest() {
        StringWildcardPredicate a = new StringWildcardPredicate("ab\\*c\\?");
        assertTrue(a.eval("ab*c?"));
        assertFalse(a.eval("ab1c?"));
        assertFalse(a.eval("ab*c1"));
    }

}
