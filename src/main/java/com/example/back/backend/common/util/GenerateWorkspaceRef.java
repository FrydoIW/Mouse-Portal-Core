package com.example.back.backend.common.util;

import java.security.SecureRandom;

public final class GenerateWorkspaceRef {

    private GenerateWorkspaceRef() {
    }

    private static final SecureRandom RANDOM = new SecureRandom();
    private static final int LENGTH = 10;

    public static String generate() {
        char[] out = new char[LENGTH];
        for (int i = 0; i < LENGTH; i++) {
            out[i] = (char) ('0' + RANDOM.nextInt(10)); // 0-9
        }
        return new String(out);
    }

    public static String generate(int length) {
        if (length <= 0) {
            throw new IllegalArgumentException("length must be > 0");
        }
        char[] out = new char[length];
        for (int i = 0; i < length; i++) {
            out[i] = (char) ('0' + RANDOM.nextInt(10));
        }
        return new String(out);
    }
}
