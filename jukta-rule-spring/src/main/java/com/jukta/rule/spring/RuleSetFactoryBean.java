package com.jukta.rule.spring;

import com.jukta.rule.core.builder.RuleSetBuilder;
import com.jukta.rule.core.impl.DefaultRuleSet;
import com.jukta.rule.core.extractor.JuelValueExtractor;
import org.springframework.beans.factory.FactoryBean;

import java.util.List;

/**
 * @since 1.0
 */
public class RuleSetFactoryBean implements FactoryBean<DefaultRuleSet> {
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
    public DefaultRuleSet getObject() throws Exception {
        RuleSetBuilder builder = RuleSetBuilder.singleRuleSet(name, inClass, outClass);
        for (FieldDef fieldDef : fields) {
            builder.addField("", fieldDef.create(), fieldDef.getRank());
        }
        return builder.build();
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
