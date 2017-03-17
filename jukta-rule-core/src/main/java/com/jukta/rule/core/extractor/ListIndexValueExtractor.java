package com.jukta.rule.core.extractor;

import com.jukta.rule.core.ValueExtractor;

import java.util.List;

/**
 * @since 1.0
 */
public class ListIndexValueExtractor<T extends List,V> implements ValueExtractor<T,V> {

    private Class<V> type;
    private int index;

    public ListIndexValueExtractor(int index, Class<V> type) {
        this.index = index;
        this.type = type;
    }

    @Override
    public V extract(T t) {
        return (V) t.get(index);
    }

    @Override
    public Class<V> getValueType() {
        return type;
    }
}
