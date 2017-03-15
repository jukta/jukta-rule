package com.jukta.rule.spring;

import org.springframework.beans.factory.xml.NamespaceHandlerSupport;

/**
 * @since 1.0
 */
public class ComponentNamespaceHandler extends NamespaceHandlerSupport {

    @Override
    public void init() {
        registerBeanDefinitionParser("singleRule", new ComponentBeanDefinitionParser());
    }

}
