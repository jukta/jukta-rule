package com.jukta.rule.core.result;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * @since 1.0
 */
public class JsonResultFactoryTest {

    @Test
    public void regular() {
        TestModel inModel = new TestModel();
        inModel.setA("a");
        inModel.setB("b");

        String exp = "{\"a\": \"${in.b}\", \"b\": \"${in.a}\"}";

        JsonResultFactory<TestModel, TestModel> factory = new JsonResultFactory<>(exp, TestModel.class, TestModel.class);
        TestModel res = factory.create(inModel, null);
        assertEquals("b", res.getA());
        assertEquals("a", res.getB());
    }

    private static class TestModel {
        private String a;
        private String b;

        public String getA() {
            return a;
        }

        public void setA(String a) {
            this.a = a;
        }

        public String getB() {
            return b;
        }

        public void setB(String b) {
            this.b = b;
        }
    }

}
