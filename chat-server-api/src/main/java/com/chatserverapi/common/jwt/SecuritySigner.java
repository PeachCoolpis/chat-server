package com.chatserverapi.common.jwt;

import com.chatserverapi.entity.Member;
import com.nimbusds.jose.JOSEException;
import com.nimbusds.jose.JWSAlgorithm;
import com.nimbusds.jose.JWSHeader;
import com.nimbusds.jose.JWSSigner;
import com.nimbusds.jose.jwk.JWK;
import com.nimbusds.jwt.JWTClaimsSet;
import com.nimbusds.jwt.SignedJWT;
import org.springframework.security.core.GrantedAuthority;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public abstract class SecuritySigner {


    protected String generateAccessToken(JWSSigner signer, Member user, JWK jwk, List<GrantedAuthority> authorities) throws JOSEException {
        JWSHeader header = new JWSHeader.Builder((JWSAlgorithm) jwk.getAlgorithm())
                .keyID(jwk.getKeyID())
                .build();

        List<String> roleList = new ArrayList<>();
        if (authorities != null && !authorities.isEmpty()) {
            for (GrantedAuthority auth : authorities) {
                roleList.add("ROLE_" + auth.getAuthority());
            }
        }
        Date date = new Date(System.currentTimeMillis());
        JWTClaimsSet claims = new JWTClaimsSet.Builder()
                .subject("user")
                .claim("id", user.getId())
                .claim("name", user.getName())
                .claim("expirationTime",date)
                .claim("roles", roleList)
                //.expirationTime(new Date(System.currentTimeMillis() + 1000 * 60 * 60)) // 1시간
                .expirationTime(new Date(System.currentTimeMillis() + 1000 * 60 * 5)) // 5분
                .build();

        SignedJWT jwt = new SignedJWT(header, claims);
        jwt.sign(signer);

        return jwt.serialize();
    }

    public abstract TokenPair getToken(Member user, JWK jwk, List<GrantedAuthority> authorities) throws JOSEException;
}
