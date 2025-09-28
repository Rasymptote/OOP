package ru.nsu.babich.parser;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;

class LexerTest {

    @Test
    void checkLexerNumberToken() {
        Lexer lexer = new Lexer("1234");
        Token token = lexer.consume();
        assertEquals(TokenType.NUMBER, token.getType());
        assertEquals("1234", token.getValue());
    }

    @Test
    void checkLexerVariableToken() {
        Lexer lexer = new Lexer("variable");
        Token token = lexer.consume();
        assertEquals(TokenType.VARIABLE, token.getType());
        assertEquals("variable", token.getValue());
    }

    @Test
    void checkLexerOperators() {
        Lexer lexer = new Lexer("+-*/");

        assertEquals(TokenType.PLUS, lexer.consume().getType());
        assertEquals(TokenType.MINUS, lexer.consume().getType());
        assertEquals(TokenType.MULTIPLY, lexer.consume().getType());
        assertEquals(TokenType.DIVIDE, lexer.consume().getType());
        assertEquals(TokenType.EOF, lexer.consume().getType());
    }

    @Test
    void checkLexerParentheses() {
        Lexer lexer = new Lexer("()");

        assertEquals(TokenType.L_PAREN, lexer.consume().getType());
        assertEquals(TokenType.R_PAREN, lexer.consume().getType());
        assertEquals(TokenType.EOF, lexer.consume().getType());
    }

    @Test
    void checkLexerInvalidCharacter() {
        Lexer lexer = new Lexer("x$y");
        lexer.consume();
        assertThrows(RuntimeException.class, lexer::consume);
    }
}
