package com.jukta.rule.spring;

import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.support.AbstractBeanDefinition;
import org.springframework.beans.factory.support.BeanDefinitionBuilder;
import org.springframework.beans.factory.support.ManagedList;
import org.springframework.beans.factory.xml.AbstractBeanDefinitionParser;
import org.springframework.beans.factory.xml.ParserContext;
import org.springframework.util.xml.DomUtils;
import org.w3c.dom.Element;

import java.util.List;

/**
 * @since 1.0
 */
public class ComponentBeanDefinitionParser extends AbstractBeanDefinitionParser {

    @Override
    protected AbstractBeanDefinition parseInternal(Element element, ParserContext parserContext) {

        List<Element> childElements = DomUtils.getChildElementsByTagName(element, "field");

        ManagedList<BeanDefinition> fields = parseExtractors(childElements);

        BeanDefinitionBuilder factory = BeanDefinitionBuilder.rootBeanDefinition(RuleSetFactoryBean.class);
        String name = element.getAttribute("id");
        String inClassStr = element.getAttribute("inClass");
        String outClassStr = element.getAttribute("outClass");
        try {
            Class inClass = Class.forName(inClassStr);
            Class outClass = Class.forName(outClassStr);
            factory.addPropertyValue("name", name);
            factory.addPropertyValue("inClass", inClass);
            factory.addPropertyValue("outClass", outClass);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException("Parsing error", e);
        }

        factory.addPropertyValue("fields", fields);
        return factory.getBeanDefinition();
    }

    private ManagedList<BeanDefinition> parseExtractors(List<Element> childElements) {
        ManagedList<BeanDefinition> children = new ManagedList<>(childElements.size());

        for (Element e : childElements) {
            try {

                BeanDefinitionBuilder factory = null;
                if (e.hasAttribute("extractorRef")) {
                    String ref = e.getAttribute("extractorRef");
                    factory = BeanDefinitionBuilder.rootBeanDefinition(RefFieldDef.class);
                    factory.addPropertyReference("extractorRef", ref);
                } else if (e.hasAttribute("listIndex")) {
                    String listIndex = e.getAttribute("listIndex");
                    factory = BeanDefinitionBuilder.rootBeanDefinition(ListIndexFieldDef.class);
                    factory.addPropertyValue("listIndex", Integer.parseInt(listIndex));
                } else if (e.hasAttribute("mapKey")) {
                    String mapKey = e.getAttribute("mapKey");
                    factory = BeanDefinitionBuilder.rootBeanDefinition(MapKeyFieldDef.class);
                    factory.addPropertyValue("mapKey", mapKey);
                } else if (e.hasAttribute("exp")) {
                    String exp = e.getAttribute("exp");
                    factory = BeanDefinitionBuilder.rootBeanDefinition(ObjectFieldDef.class);
                    factory.addPropertyValue("exp", exp);
                }
                if (e.hasAttribute("type")) {
                    String type = e.getAttribute("type");
                    Class c = Class.forName(type);
                    factory.addPropertyValue("type", c);
                }
                if (e.hasAttribute("rank")) {
                    String rank = e.getAttribute("rank");
                    factory.addPropertyValue("rank", Integer.parseInt(rank));
                }

                children.add(factory.getBeanDefinition());
            } catch (ClassNotFoundException e1) {
                throw new RuntimeException("Parsing error");
            }
        }

        return children;
    }

}
