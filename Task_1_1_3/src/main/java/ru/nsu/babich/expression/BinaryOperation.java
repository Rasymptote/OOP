package ru.nsu.babich.expression;

public abstract class BinaryOperation extends Expression {

    public final Expression left;
    public final Expression right;

    public BinaryOperation(Expression left, Expression right) {
        this.left = left;
        this.right = right;
    }
}
