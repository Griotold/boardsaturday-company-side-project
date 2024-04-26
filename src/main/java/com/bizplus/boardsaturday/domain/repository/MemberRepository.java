package com.bizplus.boardsaturday.domain.repository;

import com.bizplus.boardsaturday.domain.entity.Member;

import java.util.Optional;

public interface MemberRepository {
    Member create(Member member);

    Optional<Member> findById(Long id);

    Optional<Member> findByEmail(String email);

    // todo findByEmail에서 delete_status가 existing인 것만 가져와야 함
}
