package com.chatserverapi.common.jwt;

import com.nimbusds.jose.JOSEException;
import com.nimbusds.jose.JWSAlgorithm;
import com.nimbusds.jose.jwk.OctetSequenceKey;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.util.Base64;

@Configuration
@RequiredArgsConstructor
public class SignatureConfig {
    
    @Value("${jwt.secret}")
    private String secret;
    
    @Bean
    public MacSecuritySigner macSecuritySigner() {
        return new MacSecuritySigner();
    }
    
    @Bean
    public OctetSequenceKey octetSequenceKey(SecretKey key) throws JOSEException {
        return new OctetSequenceKey.Builder(key)
                .keyID("macKey")
                .algorithm(JWSAlgorithm.HS256)
                .build();
    }
    
    @Bean
    public SecretKey hmacKey() {
        byte[] keyBytes = Base64.getUrlDecoder().decode(secret.trim()); // ★ UrlDecoder
        return new SecretKeySpec(keyBytes, "HmacSHA256");
    }
}
