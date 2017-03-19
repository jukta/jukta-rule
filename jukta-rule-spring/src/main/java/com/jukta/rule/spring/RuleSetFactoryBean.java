package com.jukta.rule.spring;

import com.jukta.rule.DefaultRuleSetDecorator;
import com.jukta.rule.core.ValueExtractor;
import com.jukta.rule.core.builder.*;
import com.jukta.rule.core.impl.DefaultRuleSet;
import org.springframework.beans.factory.FactoryBean;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @since 1.0
 */
public class RuleSetFactoryBean implements FactoryBean<DefaultRuleSetDecorator> {
    private String name;
    private Class inClass;
    private Class outClass;

    private List<FieldDef> fields;

    public void setName(String name) {
        this.name = name;
    }

    public void setInClass(Class inClass) {
        this.inClass = inClass;
    }

    public void setOutClass(Class outClass) {
        this.outClass = outClass;
    }

    public void setFields(List<FieldDef> fields) {
        this.fields = fields;
    }

    @Override
    public DefaultRuleSetDecorator getObject() throws Exception {

        List<PredicateBuilder> predicateBuilders = new ArrayList<>();

        RuleSetBuilder builder = RuleSetBuilder.singleRuleSet(name, inClass, outClass);
        for (FieldDef fieldDef : fields) {
            ValueExtractor extractor = fieldDef.create();
            PredicateBuilder predicateBuilder = getPredicateBuilder(extractor.getValueType());
            predicateBuilders.add(predicateBuilder);
            builder.addField("", extractor, fieldDef.getRank());
        }

        DefaultRuleSetDecorator decorator = new DefaultRuleSetDecorator(builder.build());
        decorator.setPredicateBuilderList(predicateBuilders);

        decorator.setResultFactoryBuilder(DefaultResultFactoryBuilder.build(inClass, outClass));

        return decorator;
    }

    private PredicateBuilder getPredicateBuilder(Class type) {
        if (String.class.equals(type)) {
            return StringPredicateBuilder.builder();
        }
        if (Number.class.isAssignableFrom(type)) {
            return NumberPredicateBuilder.builder(type);
        }
        if (Date.class.equals(type)) {
            return DatePredicateBuilder.builder(new SimpleDateFormat("dd.MM.yyyy hh:mm:ss"));
        }
        throw new RuntimeException("Type " + type.getName() + " is not supported");
    }

    @Override
    public Class<?> getObjectType() {
        return DefaultRuleSet.class;
    }

    @Override
    public boolean isSingleton() {
        return false;
    }
}
