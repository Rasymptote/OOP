package ru.nsu.babich.expression;

public class Number extends Expression {

    private final int number;

    public Number(int number) {
        this.number = number;
    }

    public Expression derivative(String variableName) {
        return new Number(0);
    }

    public int eval(String context) {
        return number;
    }

    public Expression simplify() {
        return this;
    }

    @Override
    public boolean equals(Object obj) {
        return obj instanceof Number num && number == num.number;
    }

    @Override
    public String toString() {
        return Integer.toString(number);
    }
}
