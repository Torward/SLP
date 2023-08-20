package ru.lomov.expressions;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

@AllArgsConstructor
@RequiredArgsConstructor
public class NumberExpressions implements Expression{
    private final double value;

    @Override
    public double calculate() {
        return value;
    }
}
