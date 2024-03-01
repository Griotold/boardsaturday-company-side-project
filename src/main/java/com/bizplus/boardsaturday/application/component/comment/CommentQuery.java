package com.bizplus.boardsaturday.application.component.comment;

import com.bizplus.boardsaturday.application.response.comment.CommentResponse;
import com.bizplus.boardsaturday.domain.entity.Comment;
import com.bizplus.boardsaturday.domain.repository.CommentRepository;
import com.bizplus.boardsaturday.domain.type.ActiveStatus;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class CommentQuery {

    private final CommentRepository commentRepository;

    public Page<CommentResponse> commentList(ActiveStatus activeStatus,
                                             String content,
                                             Pageable pageable) {
        Page<Comment> commentPage = commentRepository.list(activeStatus, content, pageable);

        return commentPage.map(CommentResponse::new);
    }
}
