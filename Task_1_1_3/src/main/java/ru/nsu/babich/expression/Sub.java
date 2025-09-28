package ru.nsu.babich.expression;

public class Sub extends BinaryOperation {

    public Sub(Expression left, Expression right) {
       super(left, right);
    }

    public Expression derivative(String variableName) {
        var leftD = left.derivative(variableName);
        var rightD = right.derivative(variableName);
        return new Sub(leftD, rightD);
    }

    public int eval(String context) {
        return left.eval(context) - right.eval(context);
    }

    public Expression simplify() {
        var leftS = left.simplify();
        var rightS = right.simplify();
        if (leftS instanceof Number leftN && rightS instanceof Number rightN) {
            return new Number(leftN.eval("") - rightN.eval(""));
        }
        if (leftS.equals(rightS)) {
            return new Number(0);
        }
        if (rightS.equals(new Number(0))) {
            return leftS;
        }
        return new Sub(leftS, rightS);
    }

    @Override
    public String toString() {
        return "(" + left.toString() + "-" + right.toString() + ")";
    }
}
