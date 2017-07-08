package com.jukta.rule.core.result;

import com.jukta.rule.core.ResultFactory;
import de.odysseus.el.ExpressionFactoryImpl;
import de.odysseus.el.util.SimpleContext;

import javax.el.ExpressionFactory;
import javax.el.ValueExpression;

/**
 * Applies EL expression and returns result as output value. Input object is put to EL context with 'in' variable name.
 * @since 1.0
 */
public class SimpleJuelResultFactory<I,O> implements ResultFactory<I,O> {

    private Class<I> inType;
    private Class<O> outType;
    private String exp;
    private ExpressionFactory factory = new ExpressionFactoryImpl();

    public SimpleJuelResultFactory(String exp, Class<I> inType, Class<O> outType) {
        this.inType = inType;
        this.outType = outType;
        this.exp = exp;
        factory = new ExpressionFactoryImpl();
    }

    @Override
    public O create(I i, O o) {
        SimpleContext context = new SimpleContext();
        context.setVariable("in", factory.createValueExpression(i, inType));
        ValueExpression e = factory.createValueExpression(context, exp, outType);
        return (O) e.getValue(context);
    }
}
