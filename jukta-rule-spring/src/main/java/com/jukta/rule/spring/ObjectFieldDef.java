package com.jukta.rule.spring;

import com.jukta.rule.core.ValueExtractor;
import com.jukta.rule.core.extractor.JuelValueExtractor;

/**
 * @since 1.0
 */
public class ObjectFieldDef extends FieldDef {

    private String exp;
    private Class type;

    public String getExp() {
        return exp;
    }

    public void setExp(String exp) {
        this.exp = exp;
    }

    public Class getType() {
        return type;
    }

    public void setType(Class type) {
        this.type = type;
    }

    @Override
    public ValueExtractor create() {
        return new JuelValueExtractor<>(exp, type);
    }
}
