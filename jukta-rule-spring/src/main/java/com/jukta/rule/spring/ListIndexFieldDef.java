package com.jukta.rule.spring;

import com.jukta.rule.core.ValueExtractor;
import com.jukta.rule.core.extractor.ListIndexValueExtractor;

/**
 * @since 1.0
 */
public class ListIndexFieldDef extends ObjectFieldDef {

    private Integer listIndex;

    public Integer getListIndex() {
        return listIndex;
    }

    public void setListIndex(Integer listIndex) {
        this.listIndex = listIndex;
    }

    @Override
    public ValueExtractor create() {
        return new ListIndexValueExtractor<>(listIndex, getType());
    }
}
