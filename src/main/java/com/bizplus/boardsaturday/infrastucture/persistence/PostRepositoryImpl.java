package com.bizplus.boardsaturday.infrastucture.persistence;

import com.bizplus.boardsaturday.domain.common.BaseEntity;
import com.bizplus.boardsaturday.domain.dto.PostWithCategoryDto;
import com.bizplus.boardsaturday.domain.dto.QPostWithCategoryDto;
import com.bizplus.boardsaturday.domain.entity.Category;
import com.bizplus.boardsaturday.domain.entity.Post;
import com.bizplus.boardsaturday.domain.repository.PostRepository;
import com.bizplus.boardsaturday.domain.type.ActiveStatus;
import com.bizplus.boardsaturday.infrastucture.persistence.jpa.JpaPostRepository;
import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.types.ConstructorExpression;
import com.querydsl.core.types.Expression;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.support.PageableExecutionUtils;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

import static com.bizplus.boardsaturday.domain.entity.QPost.post;
import static com.bizplus.boardsaturday.domain.entity.QCategory.category;
import static com.bizplus.boardsaturday.domain.entity.QPostTag.postTag;
import static com.bizplus.boardsaturday.domain.entity.QTag.tag;

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
    public Optional<Post> findByIdWithFetch(Long id) {
        Post findPost = query.selectFrom(post)
                .innerJoin(post.category).fetchJoin()
                .innerJoin(post.postTags, postTag).fetchJoin()
                .innerJoin(postTag.tag, tag).fetchJoin()
                .where(post.id.eq(id))
                .fetchOne();

        return Optional.ofNullable(findPost);
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

    @Override
    public List<Post> findAllWithCategoryAndTags() {
        return query.select(post)
                .from(post)
                .innerJoin(post.category).fetchJoin()
                .orderBy(post.createdAt.desc())
                .fetch();
    }

    @Override
    public List<Post> searchBy(Category category, ActiveStatus activeStatus, String title, String body) {
        BooleanBuilder booleanBuilder = toBooleanBuilder(category, activeStatus, title, body);

        return query
                .selectFrom(post)
                .innerJoin(post.category).fetchJoin()
                .where(booleanBuilder)
                .orderBy(post.createdAt.desc())
                .fetch();
    }

    @Override
    public Page<Post> searchByPage(Category category, ActiveStatus activeStatus, String title, String body, Pageable pageable) {
        BooleanBuilder booleanBuilder = toBooleanBuilder(category, activeStatus, title, body);

        List<Post> content = query
                .selectFrom(post)
                .innerJoin(post.category).fetchJoin()
                .where(booleanBuilder)
                .orderBy(post.createdAt.desc())
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .fetch();

        JPAQuery<Long> count = query.select(post.count())
                .from(post)
                .where(booleanBuilder);

        return PageableExecutionUtils.getPage(content, pageable, count::fetchOne);
    }

    private BooleanBuilder toBooleanBuilder(Category category, ActiveStatus activeStatus, String title, String body) {
        BooleanBuilder builder = new BooleanBuilder();
        builder.and(eqCategory(category));
        builder.and(eqActiveStatus(activeStatus));
        builder.and(likeTitle(title));
        builder.and(likeBody(body));
        return builder;
    }

    private BooleanExpression eqCategory(Category category) {
        return category != null ? post.category.eq(category) : null;
    }

    private BooleanExpression eqActiveStatus(ActiveStatus activeStatus) {
        return activeStatus != null ? post.activeStatus.eq(activeStatus) : null;
    }

    private BooleanExpression likeTitle(String title) {
        String condition = title == null ? "" : title;
        return post.title.likeIgnoreCase("%" + condition + "%");
    }

    private BooleanExpression likeBody(String body) {
        String condition = body == null ? "" : body;
        return post.body.likeIgnoreCase("%" + condition + "%");
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
