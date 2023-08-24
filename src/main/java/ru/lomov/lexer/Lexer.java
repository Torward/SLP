package ru.lomov.lexer;

import ru.lomov.entities.Token;
import ru.lomov.entities.TokenTypeList;

import java.util.ArrayList;
import java.util.List;

public class Lexer {
    private static final String OPERAND_CHARS = "+-*/=()";
    private static final TokenTypeList[] OPERAND = {
            TokenTypeList.PLUS, TokenTypeList.MINUS,
            TokenTypeList.MULTIPLIER, TokenTypeList.DELIMITER,
            TokenTypeList.ASSIGN, TokenTypeList.LEFT_PARENTHESIS,
            TokenTypeList.RIGHT_PARENTHESIS
    };
    private final String input;
    private final List<Token> tokens;
    private final int length;
    private int position = 0;

    public Lexer(String input) {
        this.input = input;
        this.tokens = new ArrayList<>();
        this.length = input.length();
    }

    public List<Token> tokenize() {
        while (position < length){
            final char current = peek(0);
            if (Character.isDigit(current)) tokenizeNumber();
            if (Character.isLetter(current)) tokenizeWord();
            else if (current == '#'){
                next();
                tokenizeHexNumber();
            }
           else if (OPERAND_CHARS.indexOf(current) != -1){
                tokenizeOperand();
            } else {
                //whitespaces
               next();
            }
        }
        return tokens;
    }

    private void tokenizeWord() {
        StringBuilder buffer = new StringBuilder();
        char current = peek(0);
        while (Character.isLetterOrDigit(current) || (current == '_') || (current == '$')) {
            buffer.append(current);
            current = next();
        }
        addToken(TokenTypeList.IDENTIFIER, buffer.toString());
    }

    private void tokenizeHexNumber() {
        StringBuilder buffer = new StringBuilder();
        char current = peek(0);
        while (Character.isDigit(current) || isHexNumber(current)){
            buffer.append(current);
            current = next();
        }
        addToken(TokenTypeList.HEX_NUMBER, buffer.toString());
    }

    private static boolean isHexNumber(char current) {
        return ("abcdef".indexOf(Character.toLowerCase(current))) != -1;
    }

    private void tokenizeOperand() {
        int pos = OPERAND_CHARS.indexOf(peek(0));
        addToken(OPERAND[pos]);
        next();
    }

    private void tokenizeNumber() {
        StringBuilder buffer = new StringBuilder();
        char current = peek(0);
        while (true){
            if (current == '.'){
                if (buffer.indexOf(".") != -1) throw new RuntimeException("Неверное вещественное число!");
            } else if (!Character.isDigit(current)) {
                break;
            }
            buffer.append(current);
            current = next();
        }
        addToken(TokenTypeList.NUMBER, buffer.toString());
    }

    private char next(){
       position++;
       return peek(0);
    }

    private char peek(int relativePosition){
        final int pos = position + relativePosition;
        if (pos >= length) return '\0';
        return input.charAt(pos);
    }
    private void addToken(TokenTypeList type){
        addToken(type,"");
    }
    private void addToken(TokenTypeList type, String text){
        tokens.add(new Token(type, text));
    }
}
