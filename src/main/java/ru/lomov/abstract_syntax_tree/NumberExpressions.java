package ru.lomov.abstract_syntax_tree;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class NumberExpressions implements Expression{
    private final double value;

    @Override
    public double calculate() {
        return value;
    }

    @Override
    public String toString() {
        return Double.toString(value);
    }
}
