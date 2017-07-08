package com.jukta.rule.core.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @since 1.0
 */
public class EvalContext<I,O> {

    private List<Rule<I,O>> rules;
    private List<Rule<I,O>> result;
    private List<?> values;
    private I inObj;
    private Map<String, Object> attributes;

    public EvalContext(List<Rule<I, O>> rules, List<?> values, I inObj) {
        this.rules = rules;
        this.values = values;
        this.inObj = inObj;
        attributes = new HashMap<>();
    }

    public List<Rule<I, O>> getRules() {
        return rules;
    }

    public List<?> getValues() {
        return values;
    }

    public I getInObj() {
        return inObj;
    }

    public Object getAttribute(String name) {
        return attributes.get(name);
    }

    public void setAttribute(String name, Object value) {
        attributes.put(name, value);
    }

    public List<Rule<I, O>> getResult() {
        return result;
    }

    public void setResult(List<Rule<I, O>> result) {
        this.result = result;
    }
}
