package com.chatserverapi.common.jwt;//package com.bgmagitapi.security.jwt;


import com.chatserverapi.entity.Member;
import com.nimbusds.jose.JOSEException;
import com.nimbusds.jose.crypto.MACSigner;
import com.nimbusds.jose.jwk.JWK;
import com.nimbusds.jose.jwk.OctetSequenceKey;
import org.springframework.security.core.GrantedAuthority;

import java.util.List;

public class MacSecuritySigner extends SecuritySigner{
    @Override
    public TokenPair getToken(Member user, JWK jwk, List<GrantedAuthority> authorities) throws JOSEException {
        MACSigner jwsSigner = new MACSigner(((OctetSequenceKey)jwk).toSecretKey()); // 대칭키
        
        String accessToken = super.generateAccessToken(jwsSigner, user, jwk, authorities);
        
        return new TokenPair(accessToken);
    }
}
