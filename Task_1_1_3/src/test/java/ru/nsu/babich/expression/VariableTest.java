package ru.nsu.babich.expression;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertInstanceOf;
import static org.junit.jupiter.api.Assertions.assertSame;

import org.junit.jupiter.api.Test;

class VariableTest {

    @Test
    void checkVariableDerivativeSameVariable() {
        Expression var = new Variable("x");
        Expression derivative = var.derivative("x");
        assertInstanceOf(Number.class, derivative);
        assertEquals(1, derivative.eval(""));
    }

    @Test
    void checkVariableDerivativeDifferentVariable() {
        Expression var = new Variable("x");
        Expression derivative = var.derivative("y");
        assertInstanceOf(Number.class, derivative);
        assertEquals(0, derivative.eval(""));
    }

    @Test
    void checkVariableEval() {
        Expression var = new Variable("x");
        assertEquals(50, var.eval("x = 50; y = 100"));
    }

    @Test
    void checkVariableSimplify() {
        Expression var = new Variable("x");
        Expression simplified = var.simplify();
        assertSame(var, simplified);
    }
}
