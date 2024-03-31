package com.bizplus.boardsaturday.domain.repository;

import com.bizplus.boardsaturday.domain.entity.Member;

import java.util.Optional;

public interface MemberRepository {
    Member create(Member member);

    Optional<Member> findById(Long id);

    Optional<Member> findByEmail(String email);
}
