package ru.nsu.babich.expression;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertInstanceOf;

import org.junit.jupiter.api.Test;

class SubTest {

    @Test
    void checkSubDerivative() {
        Expression sub = new Sub(new Variable("x"), new Number(5));
        Expression derivative = sub.derivative("x");
        assertEquals(1, derivative.eval("x = 10"));
    }

    @Test
    void checkSubEval() {
        Expression sub = new Sub(new Number(10), new Number(3));
        assertEquals(7, sub.eval(""));
    }

    @Test
    void checkSubSimplifyConstants() {
        Expression sub = new Sub(new Number(15), new Number(3));
        Expression simplified = sub.simplify();
        assertInstanceOf(Number.class, simplified);
        assertEquals(12, simplified.eval(""));
    }

    @Test
    void checkSubSimplifySameExpressions() {
        Expression sub = new Sub(new Variable("x"), new Variable("x"));
        Expression simplified = sub.simplify();
        assertInstanceOf(Number.class, simplified);
        assertEquals(0, simplified.eval(""));
    }

    @Test
    void checkSubSimplifyZeroRight() {
        Expression sub = new Sub(new Variable("x"), new Number(0));
        Expression simplified = sub.simplify();
        assertInstanceOf(Variable.class, simplified);
        assertEquals("x", simplified.toString());
    }
}
