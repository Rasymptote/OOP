package ru.nsu.babich.parser;

/**
 * Represents lexical analyzer for mathematical expressions.
 * Converts input string into a sequence of tokens for parsing.
 */
public class Lexer {
    private final String input;
    private int position;
    private Token currentToken;

    /**
     * Constructs a lexer with the given input string.
     * Removes all whitespace characters from the input.
     *
     * @param input The expression.
     */
    public Lexer(String input) {
        this.input = input.replaceAll("\\s", "");
        this.position = 0;
        this.currentToken = null;
    }

    /**
     * Peeks at the next token without consuming it.
     *
     * @return The next token without advancing the position.
     */
    public Token peek() {
        if (currentToken == null) {
            return consume();
        }
        
        return currentToken;
    }

    /**
     * Consumes the current token and advances to the next one.
     *
     * @return The consumed token.
     */
    public Token consume() {
        char current = peekCharacter();

        if (current == '\0') {
            currentToken = new Token(TokenType.EOF);
        } else if (Character.isDigit(current)) {
            currentToken = readNumber();
        } else if (Character.isLetter(current)) {
            currentToken = readVariable();
        } else {
            currentToken = readToken();
        }
        
        return currentToken;
    }

    private Token readNumber() {
        StringBuilder builder = new StringBuilder();
        while (Character.isDigit(peekCharacter())) {
            builder.append(consumeCharacter());
        }
        return new Token(TokenType.NUMBER, builder.toString());
    }

    private Token readVariable() {
        StringBuilder builder = new StringBuilder();
        while (Character.isLetter(peekCharacter())) {
            builder.append(consumeCharacter());
        }
        return new Token(TokenType.VARIABLE, builder.toString());
    }

    private Token readToken() {
        char consumed = consumeCharacter();
        return switch (consumed) {
            case '(' -> new Token(TokenType.L_PAREN);
            case ')' -> new Token(TokenType.R_PAREN);
            case '+' -> new Token(TokenType.PLUS);
            case '-' -> new Token(TokenType.MINUS);
            case '*' -> new Token(TokenType.MULTIPLY);
            case '/' -> new Token(TokenType.DIVIDE);
            default -> throw new RuntimeException("Invalid character: " + consumed);
        };
    }

    private char peekCharacter() {
        return position < input.length() ? input.charAt(position) : '\0';
    }

    private char consumeCharacter() {
        return position < input.length() ? input.charAt(position++) : '\0';
    }
}
