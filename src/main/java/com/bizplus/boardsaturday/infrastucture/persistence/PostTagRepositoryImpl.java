package com.bizplus.boardsaturday.infrastucture.persistence;

import com.bizplus.boardsaturday.domain.entity.Post;
import com.bizplus.boardsaturday.domain.entity.PostTag;
import com.bizplus.boardsaturday.domain.repository.PostTagRepository;
import com.bizplus.boardsaturday.infrastucture.persistence.jpa.JpaPostTagRepository;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import static com.bizplus.boardsaturday.domain.entity.QPostTag.postTag;
import static com.bizplus.boardsaturday.domain.entity.QPost.post;

@Repository
@RequiredArgsConstructor
public class PostTagRepositoryImpl implements PostTagRepository {

    private final JpaPostTagRepository jpaPostTagRepository;
    private final JPAQueryFactory query;
    @Override
    public PostTag create(PostTag postTag) {
        return jpaPostTagRepository.save(postTag);
    }

    @Override
    public void deleteAll(Post post) {
        query.delete(postTag).where(postTag.post.eq(post)).execute();
    }
}
