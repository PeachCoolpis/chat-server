package com.chatserverapi.respotiroy.query;

import com.chatserverapi.entity.Member;

import java.util.Optional;

public interface MemberQueryRepository {
    
    Integer findByIsBooleanEmail(String email);
    
    Optional<Member> findByMember(String email);
}
