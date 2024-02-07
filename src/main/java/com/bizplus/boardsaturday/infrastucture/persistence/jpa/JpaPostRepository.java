package com.bizplus.boardsaturday.infrastucture.persistence.jpa;

import com.bizplus.boardsaturday.domain.entity.Post;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface JpaPostRepository extends JpaRepository<Post, Long> {

    List<Post> findAllByOrderByCreatedAtAsc();
}
