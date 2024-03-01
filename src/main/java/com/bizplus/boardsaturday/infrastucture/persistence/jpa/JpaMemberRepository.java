package com.bizplus.boardsaturday.infrastucture.persistence.jpa;

import com.bizplus.boardsaturday.domain.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;

public interface JpaMemberRepository extends JpaRepository<Member, Long> {
}
