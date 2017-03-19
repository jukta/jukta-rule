package com.jukta.rule.core.result;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.jukta.rule.core.ResultFactory;
import de.odysseus.el.ExpressionFactoryImpl;
import de.odysseus.el.util.SimpleContext;

import javax.el.ExpressionFactory;
import javax.el.ValueExpression;

/**
 * @since 1.0
 */
public class JsonResultFactory<I,O> implements ResultFactory<I,O> {

    private Class<I> inType;
    private Class<O> outType;
    private String exp;
    private ObjectMapper mapper;
    private ExpressionFactory factory;

    public JsonResultFactory(String exp, Class<I> inType, Class<O> outType) {
        this.inType = inType;
        this.outType = outType;
        this.exp = exp;
        mapper = new ObjectMapper();
        factory = new ExpressionFactoryImpl();
    }

    @Override
    public O create(I i, O o) {
        SimpleContext context = new SimpleContext();
        context.setVariable("in", factory.createValueExpression(i, inType));
        context.setVariable("out", factory.createValueExpression(o, outType));
        ValueExpression e = factory.createValueExpression(context, exp, String.class);
        String exp1 = (String) e.getValue(context);
        try {
            return mapper.readValue(exp1, outType);
        } catch (Exception e1) {
            throw new RuntimeException("Error creating result", e1);
        }
    }
}
