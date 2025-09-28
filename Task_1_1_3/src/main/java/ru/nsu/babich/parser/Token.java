package ru.nsu.babich.parser;

public class Token {
    private final TokenType type;
    private final String value;

    public Token(TokenType type, String value) {
        this.type = type;
        this.value = value;
    }

    public Token(TokenType type) {
        this(type, "");
    }

    public TokenType getType() {
        return type;
    }

    public String getValue() {
        return value;
    }

    public boolean isOperator() {
        return type.isOperator();
    }

    @Override
    public String toString() {
        return type + (value.isEmpty() ? "" : "(" + value + ")");
    }
}
