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

    public int eval(String context) {
        return left.eval(context) + right.eval(context);
    }

    public Expression simplify() {
        var leftS = left.simplify();
        var rightS = right.simplify();
        if (leftS instanceof Number leftN && rightS instanceof Number rightN) {
            return new Number(leftN.eval("") + rightN.eval(""));
        }
        if (leftS.equals(new Number(0))) {
            return rightS;
        }
        if (rightS.equals(new Number(0))) {
            return leftS;
        }
        return new Add(leftS, rightS);
    }

    @Override
    public String toString() {
        return "(" + left.toString() + "+" + right.toString() + ")";
    }
}
