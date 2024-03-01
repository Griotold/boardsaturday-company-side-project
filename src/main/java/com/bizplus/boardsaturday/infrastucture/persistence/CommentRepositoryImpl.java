package com.bizplus.boardsaturday.infrastucture.persistence;

import com.bizplus.boardsaturday.domain.entity.Comment;
import com.bizplus.boardsaturday.domain.repository.CommentRepository;
import com.bizplus.boardsaturday.domain.type.ActiveStatus;
import com.bizplus.boardsaturday.infrastucture.persistence.jpa.JpaCommentRepository;
import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.support.PageableExecutionUtils;
import org.springframework.stereotype.Repository;

import static com.bizplus.boardsaturday.domain.entity.QComment.comment;

import java.util.List;
import java.util.Optional;
@RequiredArgsConstructor
@Repository
public class CommentRepositoryImpl implements CommentRepository {
    private final JpaCommentRepository jpaCommentRepository;
    private final JPAQueryFactory query;

    @Override
    public Comment create(Comment comment) {
        return jpaCommentRepository.save(comment);
    }

    @Override
    public Optional<Comment> findById(Long id) {
        return jpaCommentRepository.findById(id);
    }

    @Override
    public Page<Comment> list(ActiveStatus activeStatus, String content, Pageable pageable) {
        BooleanBuilder booleanBuilder = toBooleanBuilder(activeStatus, content);

        List<Comment> comments = query
                .selectFrom(comment)
                .innerJoin(comment.post).fetchJoin()
                .innerJoin(comment.member).fetchJoin()
                .leftJoin(comment.parent).fetchJoin()
                .where(booleanBuilder)
                .orderBy(comment.createdAt.desc())
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .fetch();

        JPAQuery<Long> count = query.select(comment.count())
                .from(comment)
                .where(booleanBuilder);

        return PageableExecutionUtils.getPage(comments, pageable, count::fetchOne);
    }

    private BooleanBuilder toBooleanBuilder(ActiveStatus activeStatus, String content) {
        BooleanBuilder builder = new BooleanBuilder();
        builder.and(eqActiveStatus(activeStatus));
        builder.and(likeContent(content));
        return null;
    }

    private BooleanExpression likeContent(String content) {
        String condition = content == null ? "" : content;
        return comment.content.likeIgnoreCase("%" + condition + "%");
    }

    private BooleanExpression eqActiveStatus(ActiveStatus activeStatus) {
        return activeStatus != null ? comment.activeStatus.eq(activeStatus) : null;
    }
}
