
/*
 * Copyright (c) 2023. @author LomovFG
 */

package ru.lomov.abstract_syntax_tree;

import lombok.AllArgsConstructor;
import ru.lomov.lib.Constants;

@AllArgsConstructor
public class ConstantExpression implements Expression{
private final String name;
    @Override
    public double calculate() {
        if (!Constants.isExist(name)) throw new RuntimeException("Постоянная не существует!");
        return Constants.get(name);
    }

    @Override
    public String toString() {
//        return String.format("%s [%f]", name, Constants.get(name));
        return String.format("%f", Constants.get(name));
    }
}
