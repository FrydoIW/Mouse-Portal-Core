package com.example.back.backend.common.util;

import dev.samstevens.totp.code.CodeGenerator;
import dev.samstevens.totp.code.CodeVerifier;
import dev.samstevens.totp.code.DefaultCodeGenerator;
import dev.samstevens.totp.code.DefaultCodeVerifier;
import dev.samstevens.totp.time.SystemTimeProvider;
import dev.samstevens.totp.time.TimeProvider;
import org.springframework.stereotype.Component;

@Component
public class ValidatingOtp {

    private final TimeProvider timeProvider = new SystemTimeProvider();
    private final CodeGenerator codeGenerator = new DefaultCodeGenerator();
    private final CodeVerifier verifier = new DefaultCodeVerifier(codeGenerator, timeProvider);

    public ValidatingOtp() {
        ((DefaultCodeVerifier) verifier).setAllowedTimePeriodDiscrepancy(1);
    }

    /**
     * Verify OTP code (6 digit) against user's Base32 secret.
     * @param secret Base32 secret stored in DB (two_factor_secret)
     * @param code OTP input from user (e.g. "123456")
     */
    public boolean isValid(String secret, String code) {
        if (secret == null || secret.isBlank()) return false;
        if (code == null) return false;

        String normalized = code.trim().replaceAll("\\s+", "");
        if (!normalized.matches("\\d{6}")) return false;

        return verifier.isValidCode(secret, normalized);
    }

}
