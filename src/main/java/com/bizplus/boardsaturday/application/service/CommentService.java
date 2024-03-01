package com.bizplus.boardsaturday.application.service;

import com.bizplus.boardsaturday.application.component.comment.CommentCreator;
import com.bizplus.boardsaturday.application.component.comment.CommentUpdater;
import com.bizplus.boardsaturday.application.request.comment.CreateCommentRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CommentService {
    private final CommentCreator commentCreator;
    private final CommentUpdater commentUpdater;

    public void create(CreateCommentRequest request) {
        commentCreator.create(request);
    }

    public void changeStatusOn(Long id) {
        commentUpdater.changeStatusOn(id);
    }

    public void changeStatusOff(Long id) {
        commentUpdater.changeStatusOff(id);
    }
}
