package ru.nsu.babich.expression;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertInstanceOf;
import static org.junit.jupiter.api.Assertions.assertSame;

import org.junit.jupiter.api.Test;

class NumberTest {

    @Test
    void checkNumberDerivative() {
        Expression num = new Number(5);
        Expression derivative = num.derivative("x");
        assertInstanceOf(Number.class, derivative);
        assertEquals(0, derivative.eval(""));
    }

    @Test
    void checkNumberEval() {
        Expression num = new Number(5);
        assertEquals(5, num.eval(""));
        assertEquals(5, num.eval("x = 10"));
    }

    @Test
    void checkNumberSimplify() {
        Expression num = new Number(5);
        Expression simplified = num.simplify();
        assertSame(num, simplified);
    }
}
