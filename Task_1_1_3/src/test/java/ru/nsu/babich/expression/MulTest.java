package ru.nsu.babich.expression;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertInstanceOf;

import org.junit.jupiter.api.Test;

class MulTest {

    @Test
    void checkMulDerivative() {
        Expression mul = new Mul(new Variable("x"), new Number(5));
        Expression derivative = mul.derivative("x");
        assertEquals(5, derivative.eval("x = 10"));
    }

    @Test
    void checkMulEval() {
        Expression mul = new Mul(new Number(3), new Number(40));
        assertEquals(120, mul.eval(""));
    }

    @Test
    void checkMulSimplifyConstants() {
        Expression mul = new Mul(new Number(5), new Number(4));
        Expression simplified = mul.simplify();
        assertInstanceOf(Number.class, simplified);
        assertEquals(20, simplified.eval(""));
    }

    @Test
    void checkMulSimplifyZero() {
        Expression mul = new Mul(new Number(0), new Variable("x"));
        Expression simplified = mul.simplify();
        assertInstanceOf(Number.class, simplified);
        assertEquals(0, simplified.eval(""));
    }

    @Test
    void checkMulSimplifyOneLeft() {
        Expression mul = new Mul(new Number(1), new Variable("y"));
        Expression simplified = mul.simplify();
        assertInstanceOf(Variable.class, simplified);
        assertEquals("y", simplified.toString());
    }

    @Test
    void testMulSimplifyOneRight() {
        Expression mul = new Mul(new Variable("xyz"), new Number(1));
        Expression simplified = mul.simplify();
        assertInstanceOf(Variable.class, simplified);
        assertEquals("xyz", simplified.toString());
    }
}
