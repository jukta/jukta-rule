package com.jukta.rule.spring;

import com.jukta.rule.core.ValueExtractor;
import com.jukta.rule.core.extractor.MapKeyValueExtractor;

/**
 * @since 1.0
 */
public class MapKeyFieldDef extends ObjectFieldDef {

    private String mapKey;

    public String getMapKey() {
        return mapKey;
    }

    public void setMapKey(String mapKey) {
        this.mapKey = mapKey;
    }

    @Override
    public ValueExtractor create() {
        return new MapKeyValueExtractor<>(mapKey, getType());
    }
}
