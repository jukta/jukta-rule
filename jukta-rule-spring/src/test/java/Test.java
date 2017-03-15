import com.jukta.rule.core.impl.DefaultRuleSet;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * @since 1.0
 */
public class Test {

    public static void main(String[] args) {
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("test.xml");
        context.start();
        DefaultRuleSet rule = context.getBean(DefaultRuleSet.class);
        System.out.println();
    }

}
