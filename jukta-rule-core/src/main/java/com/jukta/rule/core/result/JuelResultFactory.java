package com.jukta.rule.core.result;

import com.jukta.rule.core.ResultFactory;
import de.odysseus.el.ExpressionFactoryImpl;
import de.odysseus.el.util.SimpleContext;

import javax.el.ExpressionFactory;
import javax.el.ValueExpression;

/**
 * @since 1.0
 */
public class JuelResultFactory<I,O> implements ResultFactory<I,O> {

    private Class<I> inType;
    private Class<O> outType;
    private String fieldExp;
    private String valueExp;
    private ExpressionFactory factory;

    public JuelResultFactory(String fieldExp, String valueExp, Class<I> inType, Class<O> outType) {
        this.inType = inType;
        this.outType = outType;
        this.fieldExp = fieldExp;
        this.valueExp = valueExp;
        factory = new ExpressionFactoryImpl();
    }

    @Override
    public O create(I i, O o) {
        O out = o;
        if (out == null) {
            out = (O) i;
        }

        SimpleContext context = new SimpleContext();
        context.setVariable("in", factory.createValueExpression(i, inType));
        context.setVariable("out", factory.createValueExpression(out, outType));

        ValueExpression value = factory.createValueExpression(context, valueExp, String.class);
        Object result = value.getValue(context);

        ValueExpression field = factory.createValueExpression(context, fieldExp, Object.class);
        field.setValue(context, result);
        return o;
    }
}
