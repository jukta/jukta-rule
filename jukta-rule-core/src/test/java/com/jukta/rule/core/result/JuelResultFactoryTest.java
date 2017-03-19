package com.jukta.rule.core.result;

import org.junit.Test;
import static org.junit.Assert.*;

/**
 * @since 1.0
 */
public class JuelResultFactoryTest {

    @Test
    public void regular() {
        TestModel inModel = new TestModel();
        inModel.setA("a");
        inModel.setB("b");

        TestModel outModel = new TestModel();
        outModel.setB("B");

        JuelResultFactory<TestModel, TestModel> factory = new JuelResultFactory<>("${out.a}", "+ ${in.b} +", TestModel.class, TestModel.class);
        TestModel res = factory.create(inModel, outModel);
        assertEquals("+ b +", res.getA());
        assertEquals("B", res.getB());
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
