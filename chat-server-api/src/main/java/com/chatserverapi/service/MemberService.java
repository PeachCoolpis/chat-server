package com.chatserverapi.service;

import com.chatserverapi.common.jwt.MacSecuritySigner;
import com.chatserverapi.common.jwt.TokenPair;
import com.chatserverapi.dto.MemberLoginReqDto;
import com.chatserverapi.dto.MemberSaveReqDto;
import com.chatserverapi.entity.Member;
import com.chatserverapi.entity.Role;
import com.chatserverapi.respotiroy.MemberRepository;
import com.nimbusds.jose.JOSEException;
import com.nimbusds.jose.jwk.JWK;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;
import java.util.stream.Collectors;

@Service
@Transactional
@RequiredArgsConstructor
public class MemberService {
    
    private final MemberRepository memberRepository;
    
    private final PasswordEncoder passwordEncoder;
    
    private final MacSecuritySigner macSecuritySigner;
    
    private final JWK jwk;
    
    public Member create(MemberSaveReqDto dto) {
        
        Integer byIsBooleanEmail = memberRepository.findByIsBooleanEmail(dto.getEmail());
        
        if (byIsBooleanEmail != null) {
            throw new IllegalArgumentException("이미 존재하는 이메일 입니다.");
        }
        dto.setPassword(passwordEncoder.encode(dto.getPassword()));
        Member member = new Member(dto);
        memberRepository.save(member);
        return member;
    }
    
    public Map<String,Object> login(MemberLoginReqDto dto) throws JOSEException {
        String password = dto.getPassword();
        String email = dto.getEmail();
        
        Member member = memberRepository.findByMember(email)
                .orElseThrow(() -> new BadCredentialsException("이메일 또는 비밀번호가 올바르지 않습니다."));
        
        if (!passwordEncoder.matches(password, member.getPassword())) {
            throw new BadCredentialsException("이메일 또는 비밀번호가 올바르지 않습니다.");
        }
        String role = member.getRole().name();
        List<GrantedAuthority> authorities =
                List.of(new SimpleGrantedAuthority(role));
        TokenPair token = macSecuritySigner.getToken(member, jwk, authorities);
        
        return Map.of("token", token.getAccessToken() , "user", member);
    }
    
    @Transactional(readOnly = true)
    public List<Member> findAll() {
        return memberRepository.findAll();
    }
}
