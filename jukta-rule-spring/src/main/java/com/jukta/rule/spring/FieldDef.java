package com.jukta.rule.spring;

import com.jukta.rule.core.ValueExtractor;

/**
 * @since 1.0
 */
public abstract class FieldDef {

    private Integer rank;

    public Integer getRank() {
        return rank;
    }

    public void setRank(Integer rank) {
        this.rank = rank;
    }

    public abstract ValueExtractor create();
}
