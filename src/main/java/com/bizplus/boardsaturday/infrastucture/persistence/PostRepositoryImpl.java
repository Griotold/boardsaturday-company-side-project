package com.bizplus.boardsaturday.infrastucture.persistence;

import com.bizplus.boardsaturday.domain.dto.PostWithCategoryDto;
import com.bizplus.boardsaturday.domain.dto.QPostWithCategoryDto;
import com.bizplus.boardsaturday.domain.entity.Post;
import com.bizplus.boardsaturday.domain.repository.PostRepository;
import com.bizplus.boardsaturday.infrastucture.persistence.jpa.JpaPostRepository;
import com.querydsl.core.types.ConstructorExpression;
import com.querydsl.core.types.Expression;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

import static com.bizplus.boardsaturday.domain.entity.QPost.post;
import static com.bizplus.boardsaturday.domain.entity.QCategory.category;

@Repository
@RequiredArgsConstructor
public class PostRepositoryImpl implements PostRepository {
    private final JpaPostRepository jpaPostRepository;
    private final JPAQueryFactory query;
    @Override
    public Post create(Post post) {
        return jpaPostRepository.save(post);
    }

    @Override
    public List<Post> findAll() {
        return jpaPostRepository.findAll();
    }

    @Override
    public List<PostWithCategoryDto> findAllOrderByCreatedAt() {
        return query.select(selectPostWithCategory())
                .from(post)
                .innerJoin(post.category, category)
                .fetch();
    }

    @Override
    public Optional<Post> findById(Long id) {
        return jpaPostRepository.findById(id);
    }

    @Override
    public Optional<Post> findByIdWithCategory(Long id) {
        List<Post> result = query
                .selectFrom(post)
                .innerJoin(post.category, category).fetchJoin()
                .where(post.id.eq(id))
                .fetch();
        return Optional.of(result.get(0));
    }

    private ConstructorExpression<PostWithCategoryDto> selectPostWithCategory() {
        return new QPostWithCategoryDto(
                post.id,
                post.title,
                post.body,
                post.activeStatus,
                post.deleteStatus,
                post.createdAt,
                post.updatedAt,
                category.name
        );
    }
}
