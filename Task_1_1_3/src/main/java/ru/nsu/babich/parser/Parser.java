package ru.nsu.babich.parser;

import ru.nsu.babich.expression.Add;
import ru.nsu.babich.expression.Div;
import ru.nsu.babich.expression.Expression;
import ru.nsu.babich.expression.Mul;
import ru.nsu.babich.expression.Number;
import ru.nsu.babich.expression.Sub;
import ru.nsu.babich.expression.Variable;

public class Parser {
    private final Lexer lexer;

    public Parser(String input) {
        this.lexer = new Lexer(input);
    }

    public Expression parse() {
        return parseExpression();
    }

    private Expression parseExpression() {
        var expression = parseAddSub();
        if (lexer.peek().getType() != TokenType.EOF) {
            throw new RuntimeException("Unexpected token \"" + lexer.peek().getType() + "\"");
        }
        return expression;
    }

    private Expression parseAddSub() {
        Expression left = parseMulDiv();
        while (true) {
            var operator = lexer.peek();
            switch (operator.getType()) {
                case PLUS -> {
                    lexer.consume();
                    left = new Add(left, parseMulDiv());
                }
                case MINUS -> {
                    lexer.consume();
                    left = new Sub(left, parseMulDiv());
                }
                default -> {
                    return left;
                }
            }
        }
    }

    private Expression parseMulDiv() {
        Expression left = parseAtomOrParen();
        while (true) {
            var operator = lexer.peek();
            switch (operator.getType()) {
                case MULTIPLY -> {
                    lexer.consume();
                    left = new Mul(left, parseAtomOrParen());
                }
                case DIVIDE -> {
                    lexer.consume();
                    left = new Div(left, parseAtomOrParen());
                }
                default -> {
                    return left;
                }
            }
        }
    }

    private Expression parseAtomOrParen() {
        var token = lexer.peek();

        switch (token.getType()) {
            case NUMBER:
                return parseNumber();
            case VARIABLE:
                return parseVariable();
            case L_PAREN:
                lexer.consume();
                Expression expression = parseAddSub();
                if (lexer.peek().getType() != TokenType.R_PAREN) {
                    throw new RuntimeException("Unexpected token \"" + lexer.peek().getType() + "\"");
                }
                lexer.consume();
                return expression;
            default:
                throw new RuntimeException("Unexpected token \"" + token + "\"");
        }
    }

    private Expression parseNumber() {
        var token = lexer.peek();
        lexer.consume();
        return new Number(Integer.parseInt(token.getValue()));
    }

    private Expression parseVariable() {
        var token = lexer.peek();
        lexer.consume();
        return new Variable(token.getValue());
    }
}