package ru.lomov;

import ru.lomov.abstract_syntax_tree.Expression;
import ru.lomov.entities.Token;
import ru.lomov.lexer.Lexer;
import ru.lomov.parser.Parser;

import java.util.List;

public class Main {
    public static void main(String[] args) {
        String input1 = "2+2";
        String input2 = "(ПИ + 2.22) * #f";
        List<Token> tokens = new Lexer(input2).tokenize();
        for (Token token: tokens) {
            System.out.println(token);
        }
        List<Expression> expressions = new Parser(tokens).parse();
        for (Expression exp: expressions) {
            System.out.println(exp + " = " + exp.calculate());
        }
    }
}