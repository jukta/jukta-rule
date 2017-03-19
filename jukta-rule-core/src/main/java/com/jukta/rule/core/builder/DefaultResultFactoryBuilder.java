package com.jukta.rule.core.builder;

import com.jukta.rule.core.ResultFactory;
import com.jukta.rule.core.result.JsonResultFactory;
import com.jukta.rule.core.result.JuelResultFactory;
import com.jukta.rule.core.result.SimpleJuelResultFactory;

/**
 * @since 1.0
 */
public class DefaultResultFactoryBuilder<I,O> implements ResultFactoryBuilder<I,O> {

    private Class<I> inType;
    private Class<O> outType;
    private String jsonPattern;
    private String juelPattern;

    private DefaultResultFactoryBuilder(Class<I> inType, Class<O> outType) {
        this.inType = inType;
        this.outType = outType;
        jsonPattern = "^\\{.+\\}$";
        juelPattern = "^\\$\\{[^\\}]+\\}\\s*:.+$";
    }

    @Override
    public ResultFactory<I, O> factory(String exp) {
        if (exp.matches(jsonPattern)) {
            return new JsonResultFactory<>(exp, inType, outType);
        } else if (exp.matches(juelPattern)){
            String valueExp = exp.substring(exp.indexOf(":")+1).trim();
            String fieldExp = exp.substring(0, exp.indexOf(":")).trim();
            return new JuelResultFactory<>(fieldExp, valueExp, inType, outType);
        } else {
            return new SimpleJuelResultFactory<>(exp, inType, outType);
        }
    }

    public static <I,O> ResultFactoryBuilder<I,O> build(Class<I> inType, Class<O> outType) {
        return new DefaultResultFactoryBuilder<>(inType, outType);
    }
}
