/*
 * Copyright (c) 2023. @author LomovFG
 */

package ru.lomov.lib;

import java.util.HashMap;
import java.util.Map;

public final class Constants {
    private static Map<String, Double> constants;

    static {
        constants = new HashMap<>();
        constants.put("ПИ", 3.14159265358979323846);
        constants.put("E", Math.E);
        constants.put("Е", Math.E);
        constants.put("ТАУ", Math.TAU);
        constants.put("ЗОЛОТОЕ_СЕЧЕНИЕ", 1.618033988749894);
    }

    public static boolean isExist(String key){
        return constants.containsKey(key);
    }
    public static double get(String key){
        if (!isExist(key)) return 0;
        return constants.get(key);
    }
}
