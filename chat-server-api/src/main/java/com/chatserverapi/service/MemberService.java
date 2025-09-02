package com.chatserverapi.service;

import com.chatserverapi.dto.MemberSaveReqDto;
import com.chatserverapi.entity.Member;
import com.chatserverapi.respotiroy.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class MemberService {
    
    private final MemberRepository memberRepository;
    
    
    public Member create(MemberSaveReqDto dto) {
        
        Integer byIsBooleanEmail = memberRepository.findByIsBooleanEmail(dto.getEmail());
        
        if(byIsBooleanEmail != null) {
            throw new IllegalArgumentException("이미 존재하는 이메일 입니다.");
        }
        Member member = new Member(dto);
        memberRepository.save(member);
        return member;
    }
}
