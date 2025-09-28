package ru.nsu.babich.expression;

/**
 * Represents addition operation.
 */
public class Add extends BinaryOperation {

    /**
     * Constructs addition operation with left and right operands.
     *
     * @param left Left addend.
     * @param right Right addend.
     */
    public Add(Expression left, Expression right) {
        super(left, right);
    }

    /**
     * Returns derivative of addition.
     */
    public Expression derivative(String variableName) {
        var leftD = left.derivative(variableName);
        var rightD = right.derivative(variableName);
        return new Add(leftD, rightD);
    }

    /**
     * Evaluates addition in provided context.
     */
    public int eval(String context) {
        return left.eval(context) + right.eval(context);
    }

    /**
     * Simplifies addition expression.
     */
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
