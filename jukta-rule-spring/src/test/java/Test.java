import com.jukta.rule.DefaultRuleSetDecorator;
import com.jukta.rule.core.RuleSet;
import com.jukta.rule.core.impl.DefaultRuleSet;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

/**
 * @since 1.0
 */
public class Test {

    public static void main(String[] args) {
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("test.xml");
        context.start();
        DefaultRuleSetDecorator rule = (DefaultRuleSetDecorator) context.getBean("qwe");


        rule.addRule(Arrays.asList(new String[] {"*", "*"}), "${in.i}");
        rule.addRule(Arrays.asList(new String[] {"a", "*"}), "hello A ${in.i}");
        rule.addRule(Arrays.asList(new String[] {"b", "*"}), "hello B ${in.i}");
        rule.addRule(Arrays.asList(new String[] {"c", "*"}), "hello C ${in.i}");

        for (int j = 0; j < 100; j++) {
            rule.addRule(Arrays.asList(new String[] {"*", "" + j}), "by int C ${in.i}");
        }

        String[] in = new String[] {"a", "b", "c", "d"};

        long t = System.currentTimeMillis();
        for (int i = 0; i < 100000; i++) {
            String res = (String) rule.eval(new Model(in[i % 4], i));
//            System.out.println(res);
        }

        System.out.println(System.currentTimeMillis() - t);



    }

}
