package com.chatserverapi.respotiroy.query;

import com.chatserverapi.entity.Member;

public interface MemberQueryRepository {
    
    Integer findByIsBooleanEmail(String email);
}
