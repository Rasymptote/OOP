package ru.nsu.babich.expression;

/**
 * Represents binary operations in expression.
 * A binary operation has two operands: left and right.
 */
public abstract class BinaryOperation extends Expression {

    public final Expression left;
    public final Expression right;

    /**
     * Constructs a new binary expression.
     *
     * @param left Left operand.
     * @param right Right operand.
     */
    public BinaryOperation(Expression left, Expression right) {
        this.left = left;
        this.right = right;
    }
}
