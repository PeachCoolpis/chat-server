package com.chatserverapi.controller;


import com.chatserverapi.dto.MemberSaveReqDto;
import com.chatserverapi.entity.Member;
import com.chatserverapi.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/member")
@RequiredArgsConstructor
public class MemberController {

    private final MemberService memberService;
    
    
    @PostMapping("/create")
    public ResponseEntity<?> memberCreate(@RequestBody MemberSaveReqDto dto) {
        Member member =  memberService.create(dto);
        return new ResponseEntity<>(member.getId(),HttpStatus.CREATED);
    }
}
