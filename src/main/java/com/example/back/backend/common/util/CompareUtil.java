package com.example.back.backend.common.util;

import java.math.BigDecimal;

public class CompareUtil {

    public static <T> T getValueOrDefault(T inputValue, T defaultValue) {
        if (inputValue == null) {
            return defaultValue;
        }

        if (inputValue instanceof String) {
            String str = (String) inputValue;
            if (str.trim().isEmpty()) {
                return defaultValue;
            }
        }

        if (inputValue instanceof BigDecimal) {
            BigDecimal bd = (BigDecimal) inputValue;
            if (bd.compareTo(BigDecimal.ZERO) == 0) {
                return defaultValue;
            }
        }

        return inputValue;
    }

}
