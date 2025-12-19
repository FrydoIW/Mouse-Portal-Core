package com.example.back.backend.common.util;

import dev.samstevens.totp.qr.QrData;
import dev.samstevens.totp.qr.QrGenerator;
import dev.samstevens.totp.qr.ZxingPngQrGenerator;
import dev.samstevens.totp.secret.DefaultSecretGenerator;
import dev.samstevens.totp.secret.SecretGenerator;
import org.springframework.stereotype.Component;

import java.util.Base64;

@Component
public class GenereateSecretKey {

    private final SecretGenerator secretGenerator = new DefaultSecretGenerator();
    private final QrGenerator qrGenerator = new ZxingPngQrGenerator();

    /**
     * Generate Base32 secret (Google Authenticator)
     */
    public String generateSecret() {
        return secretGenerator.generate();
    }

    /**
     * Generate QR Code as Base64 PNG
     */
    public String generateQrBase64(String email, String secret) {

        QrData qrData = new QrData.Builder()
                .label(email)
                .secret(secret)
                .issuer("TikusCores")
                .build();

        try {
            byte[] png = qrGenerator.generate(qrData);
            return Base64.getEncoder().encodeToString(png);
        } catch (Exception e) {
            throw new IllegalStateException("Failed to generate QR Code", e);
        }
    }

}
