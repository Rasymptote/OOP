package ru.nsu.babich.parser;

import java.util.Arrays;

public enum TokenType {
    NUMBER,
    VARIABLE,
    PLUS,
    MINUS,
    MULTIPLY,
    DIVIDE,
    L_PAREN,
    R_PAREN,
    EOF;

    private final static TokenType[] OPERATORS = new TokenType[]{PLUS, MINUS, MULTIPLY, DIVIDE};

    public boolean isOperator() {
        return Arrays.asList(OPERATORS).contains(this);
    }
}
