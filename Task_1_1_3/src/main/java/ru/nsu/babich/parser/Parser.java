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
        if (lexer.peek().getType() == TokenType.L_PAREN) {
            return parseOperation();
        }
        return parseAtom();
    }

    private Expression parseOperation() {
        lexer.consume();
        var left = parseExpression();
        if (!lexer.peek().isOperator()) {
            throw new RuntimeException("Unexpected token \"" + lexer.peek().getValue() + "\"");
        }
        var operator = lexer.peek().getType();
        lexer.consume();
        var right = parseExpression();
        if (lexer.peek().getType() != TokenType.R_PAREN) {
            throw new RuntimeException("Unexpected token \"" + lexer.peek().getValue() + "\"");
        }
        lexer.consume();
        return createOperation(operator, left, right);
    }

    private Expression parseAtom() {
        var token = lexer.peek();

        var result = switch (token.getType()) {
            case NUMBER -> parseNumber();
            case VARIABLE -> parseVariable();
            default -> throw new RuntimeException("Unexpected token in atom: " + token);
        };

        lexer.consume();
        return result;
    }

    private Expression parseNumber() {
        var token = lexer.peek();
        return new Number(Double.parseDouble(token.getValue()));
    }

    private Expression parseVariable() {
        var token = lexer.peek();
        return new Variable(token.getValue());
    }

    private Expression createOperation(TokenType operator, Expression left, Expression right) {
        return switch (operator) {
            case PLUS -> new Add(left, right);
            case MINUS -> new Sub(left, right);
            case MULTIPLY -> new Mul(left, right);
            case DIVIDE -> new Div(left, right);
            default -> throw new RuntimeException("Unsupported operator: " + operator);
        };
    }
}
