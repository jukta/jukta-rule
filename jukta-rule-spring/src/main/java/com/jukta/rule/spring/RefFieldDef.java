package com.jukta.rule.spring;

import com.jukta.rule.core.ValueExtractor;

/**
 * @since 1.0
 */
public class RefFieldDef extends FieldDef {

    private ValueExtractor extractorRef;

    public ValueExtractor getExtractorRef() {
        return extractorRef;
    }

    public void setExtractorRef(ValueExtractor extractorRef) {
        this.extractorRef = extractorRef;
    }

    @Override
    public ValueExtractor create() {
        return extractorRef;
    }
}
