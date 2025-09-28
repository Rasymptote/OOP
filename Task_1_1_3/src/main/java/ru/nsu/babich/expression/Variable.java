package ru.nsu.babich.expression;

import java.util.Scanner;

public class Variable extends Expression {
    private final String variable;

    public Variable(String variable) {
        this.variable = variable;
    }

    public Expression derivative(String variableName) {
        if (variable.equals(variableName)) {
            return new Number(1);
        }
        return new Number(0);
    }

    public int eval(String context) {
        try (Scanner scanner = new Scanner(context)) {
            scanner.useDelimiter("[= ;]+");

            while (scanner.hasNext()) {
                String currentVar = scanner.next();
                if (currentVar.equals(variable) && scanner.hasNextDouble()) {
                    return scanner.nextInt();
                }
                if (scanner.hasNext()) {
                    scanner.next();
                }
            }
            throw new IllegalArgumentException("Variable \"" + variable + "\" not found");
        }
    }

    public Expression simplify() {
        return this;
    }

    @Override
    public boolean equals(Object obj) {
        return obj instanceof Variable var && variable.equals(var.variable);
    }

    @Override
    public String toString() {
        return variable;
    }
}
