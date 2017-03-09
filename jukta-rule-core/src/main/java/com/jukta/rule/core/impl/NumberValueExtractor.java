package com.jukta.rule.core.impl;

import com.jukta.rule.core.ValueExtractor;

import java.lang.reflect.Array;
import java.text.NumberFormat;
import java.text.ParseException;

/**
 * Created by JSLO on 08.03.2017.
 */
public class NumberValueExtractor implements ValueExtractor<String[]> {

    private int value;

    public NumberValueExtractor(int value) {
        this.value = value;
    }
    @Override
    public Object extract(String[] t) {
        try {
            return NumberFormat.getInstance().parse(t[value]);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return null;
    }
}
