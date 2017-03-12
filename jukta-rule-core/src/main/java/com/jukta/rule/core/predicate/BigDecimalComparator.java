package com.jukta.rule.core.predicate;


import java.math.BigDecimal;
import java.util.Comparator;

public class BigDecimalComparator implements Comparator<BigDecimal> {

    private BigDecimal epsilon;

    public BigDecimalComparator() {
        this.epsilon = BigDecimal.ZERO;
    }

    public BigDecimalComparator(BigDecimal epsilon) {
        if (epsilon == null) {
            this.epsilon = BigDecimal.ZERO;
        } else {
            this.epsilon = epsilon.abs();
        }
    }

    @Override
    public int compare(BigDecimal o1, BigDecimal o2) {
        BigDecimal delta = o1.subtract(o2);
        if (epsilon.compareTo(delta.abs()) >= 0) {
            return 0;
        } else {
            return delta.compareTo(BigDecimal.ZERO) > 0 ? 1 : -1;
        }
    }
}
