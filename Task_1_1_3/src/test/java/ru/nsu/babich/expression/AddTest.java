package ru.nsu.babich.expression;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertInstanceOf;

import org.junit.jupiter.api.Test;

class AddTest {

    @Test
    void checkAddDerivative() {
        Expression add = new Add(new Variable("x"), new Number(5));
        Expression derivative = add.derivative("x");
        assertEquals(1, derivative.eval("x = 10"));
    }

    @Test
    void checkAddEval() {
        Expression add = new Add(new Number(30), new Number(8));
        assertEquals(38, add.eval(""));
    }

    @Test
    void checkAddEvalWithVariables() {
        Expression add = new Add(new Variable("x"), new Variable("y"));
        assertEquals(30, add.eval("x = 10; y = 20"));
    }

    @Test
    void checkAddSimplifyConstants() {
        Expression add = new Add(new Number(3), new Number(5));
        Expression simplified = add.simplify();
        assertInstanceOf(Number.class, simplified);
        assertEquals(8, simplified.eval(""));
    }

    @Test
    void checkAddSimplifyZeroLeft() {
        Expression add = new Add(new Number(0), new Variable("x"));
        Expression simplified = add.simplify();
        assertInstanceOf(Variable.class, simplified);
        assertEquals("x", simplified.toString());
    }

    @Test
    void checkAddSimplifyZeroRight() {
        Expression add = new Add(new Variable("x"), new Number(0));
        Expression simplified = add.simplify();
        assertInstanceOf(Variable.class, simplified);
        assertEquals("x", simplified.toString());
    }
}
