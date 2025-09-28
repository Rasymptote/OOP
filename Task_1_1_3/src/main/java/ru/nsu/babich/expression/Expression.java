package ru.nsu.babich.expression;

/**
 * Represents an arithmetic expression.
 */
public abstract class Expression {

    /**
     * Prints the expression.
     */
    public void print() {
        System.out.println(this);
    }

    /**
     * Calculates the derivative of expression with respect to specified variable.
     *
     * @param variableName The name of the variable.
     * @return The derivative of the expression.
     */
    public abstract Expression derivative(String variableName);

    /**
     * Evaluates the expression in the provided context.
     * @param context Context containing variable and its value.
     * @return The result of calculation.
     */
    public abstract int eval(String context);

    /**
     * Simplifies the expression.
     * @return Simplified expression.
     */
    public abstract Expression simplify();
}
