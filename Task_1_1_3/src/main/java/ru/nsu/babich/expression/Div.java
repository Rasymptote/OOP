package ru.nsu.babich.expression;

public class Div extends BinaryOperation {

    public Div(Expression left, Expression right) {
        super(left, right);
    }

    public Expression derivative(String variableName) {
        var leftD = left.derivative(variableName);
        var rightD = right.derivative(variableName);
        return new Div(new Sub(leftD, rightD), new Mul(right, right));
    }

    public double eval(String context) {
        if (right.eval(context) == 0) {
            throw new ArithmeticException("Division by zero");
        }
        return left.eval(context) / right.eval(context);
    }

    @Override
    public String toString() {
        return "(" + left.toString() + "/" + right.toString() + ")";
    }
}
