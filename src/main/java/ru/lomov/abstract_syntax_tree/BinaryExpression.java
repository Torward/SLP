package ru.lomov.abstract_syntax_tree;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class BinaryExpression implements Expression {
    private final char operand;
    private final Expression exp1;
    private final Expression exp2;

    @Override
    public double calculate() {
        switch (operand) {
            case '-':
                return exp1.calculate() - exp2.calculate();
            case '*':
                return exp1.calculate() * exp2.calculate();
            case '/': {
//                if (exp2.equals(0)) {
//                    throw new ArithmeticException();
//                }
                return exp1.calculate() / exp2.calculate();
            }
            case '+':
            default:
                return exp1.calculate() + exp2.calculate();
        }
    }

    @Override
    public String toString() {
        return String.format("[%s %c %s]", exp1, operand, exp2);
    }
}
