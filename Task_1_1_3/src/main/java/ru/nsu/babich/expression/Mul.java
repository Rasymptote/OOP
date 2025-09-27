package ru.nsu.babich.expression;

public class Mul extends BinaryOperation {

    public Mul(Expression left, Expression right) {
       super(left, right);
    }

    public Expression derivative(String variableName) {
        var leftD = left.derivative(variableName);
        var rightD = right.derivative(variableName);
        return new Add(new Mul(leftD, right), new Mul(left, rightD));
    }

    public double eval(String context) {
        return left.eval(context) * right.eval(context);
    }

    @Override
    public String toString() {
        return "(" + left.toString() + "*" + right.toString() + ")";
    }
}
