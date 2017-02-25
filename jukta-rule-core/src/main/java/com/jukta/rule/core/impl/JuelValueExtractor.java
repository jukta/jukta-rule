package com.jukta.rule.core.impl;

import com.jukta.rule.core.ValueExtractor;
import de.odysseus.el.ExpressionFactoryImpl;
import de.odysseus.el.util.SimpleContext;

import javax.el.ExpressionFactory;
import javax.el.ValueExpression;

/**
 * @since 1.0
 */
public class JuelValueExtractor<T> implements ValueExtractor<T> {
    private String exp;
    private ExpressionFactory factory = new ExpressionFactoryImpl();

    public JuelValueExtractor(String exp) {
        this.exp = exp;
    }

    @Override
    public Object extract(T t) {
        SimpleContext context = new SimpleContext();
        context.setVariable("in", factory.createValueExpression(t, Object.class));
        ValueExpression e = factory.createValueExpression(context, exp, Object.class);
        return e.getValue(context);
    }

}
