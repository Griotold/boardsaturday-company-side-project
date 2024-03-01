package com.bizplus.boardsaturday.application.service;

import com.bizplus.boardsaturday.application.component.comment.CommentCreator;
import com.bizplus.boardsaturday.application.component.comment.CommentDeleter;
import com.bizplus.boardsaturday.application.component.comment.CommentQuery;
import com.bizplus.boardsaturday.application.component.comment.CommentUpdater;
import com.bizplus.boardsaturday.application.request.comment.CreateCommentRequest;
import com.bizplus.boardsaturday.application.response.comment.CommentResponse;
import com.bizplus.boardsaturday.domain.type.ActiveStatus;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CommentService {
    private final CommentCreator commentCreator;
    private final CommentUpdater commentUpdater;
    private final CommentDeleter commentDeleter;
    private final CommentQuery commentQuery;

    public void create(CreateCommentRequest request) {
        commentCreator.create(request);
    }

    public Page<CommentResponse> commentList(ActiveStatus activeStatus,
                                             String content,
                                             Pageable pageable) {
        return commentQuery.commentList(activeStatus, content, pageable);
    }

    public void changeStatusOn(Long id) {
        commentUpdater.changeStatusOn(id);
    }

    public void changeStatusOff(Long id) {
        commentUpdater.changeStatusOff(id);
    }

    public void delete(Long id) {
        commentDeleter.delete(id);
    }
}
