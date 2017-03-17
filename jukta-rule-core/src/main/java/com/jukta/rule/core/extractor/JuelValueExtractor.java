package com.jukta.rule.core.extractor;

import com.jukta.rule.core.ValueExtractor;
import de.odysseus.el.ExpressionFactoryImpl;
import de.odysseus.el.util.SimpleContext;

import javax.el.ExpressionFactory;
import javax.el.ValueExpression;

/**
 * @since 1.0
 */
public class JuelValueExtractor<T, V> implements ValueExtractor<T, V> {
    private String exp;
    private Class<V> valueType;
    private ExpressionFactory factory = new ExpressionFactoryImpl();

    public JuelValueExtractor(String exp, Class<V> valueType) {
        this.exp = exp;
        this.valueType = valueType;
    }

    @Override
    public V extract(T t) {
        SimpleContext context = new SimpleContext();
        context.setVariable("in", factory.createValueExpression(t, Object.class));
        ValueExpression e = factory.createValueExpression(context, exp, valueType);
        return (V) e.getValue(context);
    }

    @Override
    public Class<V> getValueType() {
        return valueType;
    }


}
