package com.jukta.rule.core.builder;

import com.jukta.rule.core.result.JsonResultFactory;
import com.jukta.rule.core.result.JuelResultFactory;
import com.jukta.rule.core.result.SimpleJuelResultFactory;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * @since 1.0
 */
public class DefaultResultFactoryBuilderTest {

    @Test
    public void jsonPattern() {
        String exp = "{\"a\": \"${in.b}\", \"b\": \"${in.a}\"}";
        ResultFactoryBuilder<String, String> builder = DefaultResultFactoryBuilder.build(String.class, String.class);
        assertEquals(JsonResultFactory.class, builder.factory(exp).getClass());
    }

    @Test
    public void juelPattern() {
        String exp = "${in.b} : ${in.a}";
        ResultFactoryBuilder<String, String> builder = DefaultResultFactoryBuilder.build(String.class, String.class);
        assertEquals(JuelResultFactory.class, builder.factory(exp).getClass());
    }

    @Test
    public void simpleJuelPattern() {
        String exp = "a ${in.b}";
        ResultFactoryBuilder<String, String> builder = DefaultResultFactoryBuilder.build(String.class, String.class);
        assertEquals(SimpleJuelResultFactory.class, builder.factory(exp).getClass());
    }

}
