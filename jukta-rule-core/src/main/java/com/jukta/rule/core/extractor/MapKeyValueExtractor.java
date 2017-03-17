package com.jukta.rule.core.extractor;

import com.jukta.rule.core.ValueExtractor;

import java.util.Map;

/**
 * @since 1.0
 */
public class MapKeyValueExtractor<T extends Map,V> implements ValueExtractor<T,V> {

    private String key;
    private Class<V> type;

    public MapKeyValueExtractor(String key, Class<V> type) {
        this.key = key;
        this.type = type;
    }

    @Override
    public V extract(T t) {
        return (V) t.get(key);
    }

    @Override
    public Class<V> getValueType() {
        return type;
    }
}
