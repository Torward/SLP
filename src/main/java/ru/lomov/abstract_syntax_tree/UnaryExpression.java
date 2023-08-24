package ru.lomov.abstract_syntax_tree;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class UnaryExpression implements Expression{
    private final char operand;
    private final Expression exp;

    @Override
    public double calculate() {
        switch (operand) {
            case '-':
                return -exp.calculate();
            case '+':
            default:
                return exp.calculate();
        }
    }

    @Override
    public String toString() {
        return String.format("%c %s", operand, exp );
    }
}
