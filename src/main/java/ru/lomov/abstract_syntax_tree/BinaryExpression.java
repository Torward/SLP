package ru.lomov.expressions;

import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;

@AllArgsConstructor
@RequiredArgsConstructor
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
                if (exp2.equals(0)) {
                    throw new ArithmeticException();
                }
                return exp1.calculate() / exp2.calculate();
            }
            case '+':
            default:
                return exp1.calculate() + exp2.calculate();
        }
    }
}
