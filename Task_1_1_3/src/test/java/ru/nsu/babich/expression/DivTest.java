package ru.nsu.babich.expression;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertInstanceOf;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;

class DivTest {

    @Test
    void testDivDerivative() {
        Expression div = new Div(new Variable("x"), new Number(2));
        Expression derivative = div.derivative("x");
        Expression expected = new Div(new Sub(new Mul(new Number(1), new Number(2)),
                new Mul(new Variable("x"), new Number(0))), new Mul(new Number(2), new Number(2)));
        assertEquals(expected.toString(), derivative.toString());
    }

    @Test
    void checkDivEval() {
        Expression div = new Div(new Number(10), new Number(2));
        assertEquals(5, div.eval(""));
    }

    @Test
    void checkDivByZeroThrowsException() {
        Expression div = new Div(new Number(10), new Number(0));
        assertThrows(ArithmeticException.class, () -> div.eval(""));
    }

    @Test
    void checkDivSimplifyConstants() {
        Expression div = new Div(new Number(10), new Number(2));
        Expression simplified = div.simplify();
        assertInstanceOf(Number.class, simplified);
        assertEquals(5, simplified.eval(""));
    }

    @Test
    void checkDivSimplifySameExpressions() {
        Expression div = new Div(new Variable("x"), new Variable("x"));
        Expression simplified = div.simplify();
        assertInstanceOf(Number.class, simplified);
        assertEquals(1, simplified.eval("x = 5"));
    }

    @Test
    void checkDivSimplifyOneDenominator() {
        Expression div = new Div(new Variable("x"), new Number(1));
        Expression simplified = div.simplify();
        assertInstanceOf(Variable.class, simplified);
        assertEquals("x", simplified.toString());
    }

    @Test
    void checkDivSimplifyZeroNumerator() {
        Expression div = new Div(new Number(0), new Variable("x"));
        Expression simplified = div.simplify();
        assertInstanceOf(Number.class, simplified);
        assertEquals(0, simplified.eval("x = 5"));
    }
}
