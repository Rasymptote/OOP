package ru.nsu.babich.expression;

public class Add extends BinaryOperation {

    public Add(Expression left, Expression right) {
        super(left, right);
    }

    public Expression derivative(String variableName) {
        var leftD = left.derivative(variableName);
        var rightD = right.derivative(variableName);
        return new Add(leftD, rightD);
    }

    public double eval(String context) {
        return left.eval(context) + right.eval(context);
    }

    @Override
    public String toString() {
        return "(" + left.toString() + "+" + right.toString() + ")";
    }
}
