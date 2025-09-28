package ru.nsu.babich.expression;

/**
 * Represents division operation.
 */
public class Div extends BinaryOperation {

    /**
     * Constructs division operation with left and right operands.
     *
     * @param left Dividend.
     * @param right Divisor.
     */
    public Div(Expression left, Expression right) {
        super(left, right);
    }

    /**
     * Returns derivative of division.
     */
    public Expression derivative(String variableName) {
        var leftD = left.derivative(variableName);
        var rightD = right.derivative(variableName);
        return new Div(new Sub(new Mul(leftD, right),
                new Mul(left, rightD)), new Mul(right, right));
    }

    /**
     * Evaluates division in provided context.
     */
    public int eval(String context) {
        if (right.eval(context) == 0) {
            throw new ArithmeticException("Division by zero");
        }
        return left.eval(context) / right.eval(context);
    }

    /**
     * Simplifies division expression.
     */
    public Expression simplify() {
        var leftS = left.simplify();
        var rightS = right.simplify();
        if (leftS instanceof Number leftN && rightS instanceof Number rightN) {
            return new Number(leftN.eval("") / rightN.eval(""));
        }
        if (leftS.equals(rightS)) {
            return new Number(1);
        }
        if (rightS.equals(new Number(1))) {
            return leftS;
        }
        if (leftS.equals(new Number(0))) {
            return new Number(0);
        }
        return new Div(leftS, rightS);
    }

    @Override
    public String toString() {
        return "(" + left.toString() + "/" + right.toString() + ")";
    }
}
