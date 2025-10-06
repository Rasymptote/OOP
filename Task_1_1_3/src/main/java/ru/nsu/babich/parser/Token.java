package ru.nsu.babich.parser;

/**
 * Represents a lexical token in the expression parser.
 */
public class Token {
    private final TokenType type;
    private final String value;

    /**
     * Constructs a token with the specified type and value.
     *
     * @param type The type of the token.
     * @param value The value associated with the token.
     */
    public Token(TokenType type, String value) {
        this.type = type;
        this.value = value;
    }

    /**
     * Constructs a Token with the specified type and an empty value.
     * Used for operators, parentheses, EOF.
     *
     * @param type The type of the token.
     */
    public Token(TokenType type) {
        this(type, "");
    }

    public TokenType getType() {
        return type;
    }

    public String getValue() {
        return value;
    }

    @Override
    public String toString() {
        return type + (value.isEmpty() ? "" : "(" + value + ")");
    }
}
