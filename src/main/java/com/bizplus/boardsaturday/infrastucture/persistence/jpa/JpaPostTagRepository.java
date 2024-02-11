package com.bizplus.boardsaturday.infrastucture.persistence.jpa;

import com.bizplus.boardsaturday.domain.entity.PostTag;
import org.springframework.data.jpa.repository.JpaRepository;

public interface JpaPostTagRepository extends JpaRepository<PostTag, Long> {

}
