package com.jukta.rule.core.predicate;

import java.util.Comparator;

public class DoubleComparator implements Comparator<Double> {

    private Double epsilon;

    public DoubleComparator() {
        this.epsilon = 0d;
    }

    public DoubleComparator(Double epsilon) {
        if (epsilon == null) {
            this.epsilon = 0d;
        } else {
            this.epsilon = Math.abs(epsilon);
        }
    }

    @Override
    public int compare(Double d1, Double d2) {
        Double delta = d1 - d2;
        if (epsilon.compareTo(Math.abs(delta)) >= 0) {
            return 0;
        } else {
            return delta > 0 ? 1 : -1;
        }
    }
}
