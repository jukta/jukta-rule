package com.jukta.rule.core.impl;

import com.jukta.rule.core.ResultFactory;
import de.odysseus.el.ExpressionFactoryImpl;
import de.odysseus.el.util.SimpleContext;

import javax.el.ExpressionFactory;
import javax.el.ValueExpression;

/**
 * @since 1.0
 */
public class JuelResultFactory<I> implements ResultFactory<I, String> {
    private String exp;
    private ExpressionFactory factory = new ExpressionFactoryImpl();

    public JuelResultFactory(String exp) {
        this.exp = exp;
    }

    @Override
    public String create(I i, String o) {
        SimpleContext context = new SimpleContext();
        context.setVariable("in", factory.createValueExpression(i, Object.class));
        ValueExpression e = factory.createValueExpression(context, exp, Object.class);
        return (String) e.getValue(context);
    }
}
