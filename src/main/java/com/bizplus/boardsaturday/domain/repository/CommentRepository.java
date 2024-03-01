package com.bizplus.boardsaturday.domain.repository;

import com.bizplus.boardsaturday.domain.entity.Comment;

import java.util.Optional;

public interface CommentRepository {
    Comment create(Comment comment);

    Optional<Comment> findById(Long id);
}
