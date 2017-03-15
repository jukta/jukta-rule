package com.jukta.rule.spring;

import com.jukta.rule.core.impl.JuelValueExtractor;
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

        List<Element> childElements = DomUtils.getChildElementsByTagName(element, "extractor");

        ManagedList<BeanDefinition> extractors = parseExtractors(childElements);

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

        factory.addPropertyValue("extractors", extractors);
        return factory.getBeanDefinition();
    }

    private ManagedList<BeanDefinition> parseExtractors(List<Element> childElements) {
        ManagedList<BeanDefinition> children = new ManagedList<>(childElements.size());

        for (Element e : childElements) {
            try {
                BeanDefinitionBuilder factory = BeanDefinitionBuilder.rootBeanDefinition(JuelValueExtractor.class);
                factory.addConstructorArgValue(e.getAttribute("exp"));
                String type = e.getAttribute("type");
                Class c = Class.forName(type);
                factory.addConstructorArgValue(c);
                children.add(factory.getBeanDefinition());
            } catch (ClassNotFoundException e1) {
                throw new RuntimeException("Parsing error");
            }
        }

        return children;
    }

}
