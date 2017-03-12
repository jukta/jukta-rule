package com.jukta.rule.core.predicate;

import java.util.Comparator;

public class DoubleComparator implements Comparator<Double> {
    @Override
    public int compare(Double d1, Double d2) {
        double delta = d1 - d2;
        if (Math.abs(delta) >= 10e-12) {
            if (delta < 0) {
                return -1;
            }
            if (delta > 0) {
                return 1;
            }
        }
        return 0;
    }
}
