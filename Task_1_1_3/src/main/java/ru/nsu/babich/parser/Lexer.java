package ru.nsu.babich.parser;

public class Lexer {
    private final String input;
    private int position;
    private Token currentToken;

    public Lexer(String input) {
        this.input = input.replaceAll("\\s", "");
        this.position = 0;
        this.currentToken = null;
    }
    
    public Token peek() {
        if (currentToken == null) {
            return consume();
        }
        
        return currentToken;
    }

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
