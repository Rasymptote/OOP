package ru.nsu.babich.expression;

public class Number extends Expression {

    private final double number;

    public Number(double number) {
        this.number = number;
    }

    public Expression derivative(String variableName) {
        return new Number(0);
    }

    public double eval(String context) {
        return number;
    }

    @Override
    public String toString() {
        return Double.toString(number);
    }
}
