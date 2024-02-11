package com.bizplus.boardsaturday.domain.repository;

import com.bizplus.boardsaturday.domain.entity.Tag;

import java.util.Optional;

public interface TagRepository {

    Tag create(Tag tag);

    Optional<Tag> findByName(String name);

    Optional<Tag> findById(Long id);
}
