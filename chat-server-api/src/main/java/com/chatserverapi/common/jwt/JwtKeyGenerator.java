package com.chatserverapi.common.jwt;

import java.security.SecureRandom;
import java.util.Base64;

public class JwtKeyGenerator {
    public static void main(String[] args) {
        byte[] key = new byte[32];                // 256-bit
        new java.security.SecureRandom().nextBytes(key);
        String secretB64Url = java.util.Base64.getUrlEncoder().withoutPadding().encodeToString(key);
        System.out.println("Secret Key: " + secretB64Url);
    }
}
