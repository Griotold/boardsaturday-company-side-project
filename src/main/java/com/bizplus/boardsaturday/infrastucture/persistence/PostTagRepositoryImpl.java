package com.bizplus.boardsaturday.infrastucture.persistence;

import com.bizplus.boardsaturday.domain.entity.PostTag;
import com.bizplus.boardsaturday.domain.repository.PostTagRepository;
import com.bizplus.boardsaturday.infrastucture.persistence.jpa.JpaPostTagRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class PostTagRepositoryImpl implements PostTagRepository {

    private final JpaPostTagRepository jpaPostTagRepository;
    @Override
    public PostTag create(PostTag postTag) {
        return jpaPostTagRepository.save(postTag);
    }
}
