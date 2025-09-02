package com.chatserverapi.respotiroy;

import com.chatserverapi.entity.Member;
import com.chatserverapi.respotiroy.query.MemberQueryRepository;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MemberRepository extends JpaRepository<Member, Long> , MemberQueryRepository {
}
