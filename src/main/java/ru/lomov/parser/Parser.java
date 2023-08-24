package ru.lomov.parser;

import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import ru.lomov.abstract_syntax_tree.*;
import ru.lomov.entities.Token;
import ru.lomov.entities.TokenTypeList;

import java.util.ArrayList;
import java.util.List;


public class Parser {
    private static final Token EOF = new Token(TokenTypeList.EOF, "");
    private final List<Token> tokens;
    private final int size;
    private int position;

    public Parser(List<Token> tokens) {
        this.tokens = tokens;
        size = tokens.size();
    }

    public List<Expression> parse(){
        List<Expression> result = new ArrayList<>();
        while (!match(TokenTypeList.EOF)){
            result.add(expression());
        }
        return result;
    }
    private Expression primary() {
        Token current = get(0);
        if (match(TokenTypeList.NUMBER)) {
            return new NumberExpressions(Double.parseDouble(current.getText()));
        }
        if (match(TokenTypeList.HEX_NUMBER)) {
            return new NumberExpressions(Long.parseLong(current.getText(), 16));
        }
        if (match(TokenTypeList.IDENTIFIER)) {
            return new ConstantExpression(current.getText());
        }

        if (match(TokenTypeList.LEFT_PARENTHESIS)){
          Expression result = expression();
          match(TokenTypeList.RIGHT_PARENTHESIS);
          return result;
        }
      throw new RuntimeException("Неизвестное выражение.");
    }
    private Expression expression(){
        return additive();
    }

    private Expression additive(){
        Expression result = multiplicative();
        while (true) {
            if (match(TokenTypeList.PLUS)) {
                result = new BinaryExpression('+', result, multiplicative());
                continue;
            }
            if (match(TokenTypeList.MINUS)) {
                result = new BinaryExpression('-', result, multiplicative());
                continue;
            }
            break;
        }
        return result;
    }

    private Expression multiplicative(){
        Expression result = unary();
        while (true) {
            if (match(TokenTypeList.MULTIPLIER)) {
                result = new BinaryExpression('*', result, unary());
                continue;
            }
            if (match(TokenTypeList.DELIMITER)) {
                result = new BinaryExpression('/', result, unary());
                continue;
            }
            break;
        }
        return result;
    }

    private Expression unary(){
        if (match(TokenTypeList.MINUS)){
            return new UnaryExpression('-', primary());
        }
        if (match(TokenTypeList.PLUS)){
            return primary();
        }

        return primary();
    }
    private boolean match(TokenTypeList type){
        Token current = get(0);
        if (type != current.getType()) return false;
        position++;
        return true;
    }
    private Token get(int relativePosition){
        final int pos = position + relativePosition;
        if (pos >= size) return EOF;
        return tokens.get(pos);
    }
}
