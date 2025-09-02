package com.chatserverapi.respotiroy.impl;

import com.chatserverapi.respotiroy.query.MemberQueryRepository;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class MemberRepositoryImpl implements MemberQueryRepository {
    
    private final JPAQueryFactory queryFactory;
}
