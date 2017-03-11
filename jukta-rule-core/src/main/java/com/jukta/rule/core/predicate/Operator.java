package com.jukta.rule.core.predicate;

/**
 * @since 1.0
 */
public enum Operator {

    GREATER(">"),
    GREATER_OR_EQUALS("=>"),
    LESS("<"),
    LESS_OR_EQUALS("<="),
    EQUALS("");

    String op;

    Operator(String op) {
        this.op = op;
    }

    public String getOp() {
        return op;
    }

    boolean calc(int value) {
        if (this == Operator.EQUALS) {
            return value == 0;
        }
        if (this == Operator.GREATER) {
            return value > 0;
        }
        if (this == Operator.GREATER_OR_EQUALS) {
            return value >= 0;
        }
        if (this == Operator.LESS) {
            return value < 0;
        }
        return value <= 0;
    }

    public static Operator parse(String exp) {
        Operator operator = null;
        for (Operator o : Operator.values()) {
            if (exp.startsWith(o.getOp())) {
                operator = o;
                break;
            }
        }
        return operator;
    }
}
