package ru.nsu.babich.expression;

/**
 * Represents multiplication operation.
 */
public class Mul extends BinaryOperation {

    /**
     * Constructs multiplication operation with left and right operands.
     *
     * @param left Left multiplicand.
     * @param right Right multiplicand.
     */
    public Mul(Expression left, Expression right) {
        super(left, right);
    }

    /**
     * Returns derivative of multiplication.
     */
    public Expression derivative(String variableName) {
        var leftD = left.derivative(variableName);
        var rightD = right.derivative(variableName);
        return new Add(new Mul(leftD, right), new Mul(left, rightD));
    }

    /**
     * Evaluates multiplication in provided context.
     */
    public int eval(String context) {
        return left.eval(context) * right.eval(context);
    }

    /**
     * Simplifies multiplication expression.
     */
    public Expression simplify() {
        var leftS = left.simplify();
        var rightS = right.simplify();
        if (leftS instanceof Number leftN && rightS instanceof Number rightN) {
            return new Number(leftN.eval("") * rightN.eval(""));
        }
        if (leftS.equals(new Number(0)) || rightS.equals(new Number(0))) {
            return new Number(0);
        }
        if (leftS.equals(new Number(1))) {
            return rightS;
        }
        if (rightS.equals(new Number(1))) {
            return leftS;
        }
        return new Mul(leftS, rightS);
    }

    @Override
    public String toString() {
        return "(" + left.toString() + "*" + right.toString() + ")";
    }
}
