package com.bizplus.boardsaturday.infrastucture.persistence;

import com.bizplus.boardsaturday.domain.entity.Tag;
import com.bizplus.boardsaturday.domain.repository.TagRepository;
import com.bizplus.boardsaturday.infrastucture.persistence.jpa.JpaTagRepository;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class TagRepositoryImpl implements TagRepository {
    private final JpaTagRepository jpaTagRepository;
    private final JPAQueryFactory query;

    @Override
    public Tag create(Tag tag) {
        return jpaTagRepository.save(tag);
    }

    @Override
    public Optional<Tag> findByName(String name) {
        return jpaTagRepository.findByName(name);
    }
}
