package ru.nsu.babich.parser;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertInstanceOf;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;
import ru.nsu.babich.expression.Add;
import ru.nsu.babich.expression.Div;
import ru.nsu.babich.expression.Mul;
import ru.nsu.babich.expression.Number;
import ru.nsu.babich.expression.Sub;
import ru.nsu.babich.expression.Variable;

class ParserTest {

    @Test
    void checkParserNumber() {
        Parser parser = new Parser("42");
        var expr = parser.parse();
        assertInstanceOf(Number.class, expr);
        assertEquals(42, expr.eval(""));
    }

    @Test
    void checkParserVariable() {
        Parser parser = new Parser("x");
        var expr = parser.parse();

        assertInstanceOf(Variable.class, expr);
        assertEquals(10, expr.eval("x=10"));
    }

    @Test
    void testParserSimpleAddition() {
        Parser parser = new Parser("x+5");
        var expr = parser.parse();
        var expected = new Add(new Variable("x"), new Number(5));
        assertEquals(expected.toString(), expr.toString());
    }

    @Test
    void checkParserSimpleSubtraction() {
        Parser parser = new Parser("x-3");
        var expr = parser.parse();
        var expected = new Sub(new Variable("x"), new Number(3));
        assertEquals(expected.toString(), expr.toString());
    }

    @Test
    void checkParserSimpleMultiplication() {
        Parser parser = new Parser("x*2");
        var expr = parser.parse();
        var expected = new Mul(new Variable("x"), new Number(2));
        assertEquals(expected.toString(), expr.toString());
    }

    @Test
    void checkParserSimpleDivision() {
        Parser parser = new Parser("x/2");
        var expr = parser.parse();
        var expected = new Div(new Variable("x"), new Number(2));
        assertEquals(expected.toString(), expr.toString());
    }

    @Test
    void checkParserOperatorPrecedence() {
        Parser parser = new Parser("2+3*4");
        var expr = parser.parse();
        assertEquals(14, expr.eval(""));
    }

    @Test
    void checkParserParenthesesPrecedence() {
        Parser parser = new Parser("(2+3)*4");
        var expr = parser.parse();
        assertEquals(20, expr.eval(""));
    }

    @Test
    void checkParserNestedParentheses() {
        Parser parser = new Parser("((x+1)*2)");
        var expr = parser.parse();
        assertEquals(22, expr.eval("x=10"));
    }

    @Test
    void checkParserUnexpectedToken() {
        Parser parser = new Parser("5+");
        assertThrows(RuntimeException.class, parser::parse);
    }
}
