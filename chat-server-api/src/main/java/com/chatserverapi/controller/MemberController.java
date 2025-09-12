package com.chatserverapi.controller;


import com.chatserverapi.dto.MemberLoginReqDto;
import com.chatserverapi.dto.MemberSaveReqDto;
import com.chatserverapi.entity.Member;
import com.chatserverapi.service.MemberService;
import com.nimbusds.jose.JOSEException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.Objects;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class MemberController {
    
    private final MemberService memberService;
    
    
    @PostMapping("/member/create")
    public ResponseEntity<?> memberCreate(@RequestBody MemberSaveReqDto dto) {
        Member member = memberService.create(dto);
        return new ResponseEntity<>(member.getId(), HttpStatus.CREATED);
    }
    
    @PostMapping("/member/doLogin")
    public Map<String, Object> doLogin(@RequestBody MemberLoginReqDto dto) throws JOSEException {
        return memberService.login(dto);
    }
    
    @GetMapping("/member/list")
    public List<Member> memberList(@AuthenticationPrincipal Jwt jwt) {
        System.out.println("jwt = " + jwt);
        return memberService.findAll();
    }
}
