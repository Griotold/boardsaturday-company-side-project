package com.bizplus.boardsaturday.domain.repository;

import com.bizplus.boardsaturday.domain.entity.Comment;
import com.bizplus.boardsaturday.domain.type.ActiveStatus;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

public interface CommentRepository {
    Comment create(Comment comment);

    Optional<Comment> findById(Long id);

    Page<Comment> list(ActiveStatus activeStatus, String content, Pageable pageable);
}
