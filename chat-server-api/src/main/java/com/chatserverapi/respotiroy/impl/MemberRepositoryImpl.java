package com.chatserverapi.respotiroy.impl;

import com.chatserverapi.respotiroy.query.MemberQueryRepository;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;

import static com.chatserverapi.entity.QMember.member;

@RequiredArgsConstructor
public class MemberRepositoryImpl implements MemberQueryRepository {
    
    private final JPAQueryFactory queryFactory;
    
    @Override
    public Integer findByIsBooleanEmail(String email) {
        return queryFactory
                .selectOne()
                .from(member)
                .where(member.email.eq(email))
                .fetchFirst();
    }
}
