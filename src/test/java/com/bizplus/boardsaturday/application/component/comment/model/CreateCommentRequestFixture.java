package com.bizplus.boardsaturday.application.component.comment.model;

import com.bizplus.boardsaturday.application.request.comment.CreateCommentRequest;

public class CreateCommentRequestFixture {

    public static CreateCommentRequest create(Long memberId, Long postId) {
        return new CreateCommentRequest(
                memberId,
                postId,
                null,
                "default content"
        );
    }

    public static CreateCommentRequest create(Long memberId, Long postId, Long parentId) {
        return new CreateCommentRequest(
                memberId,
                postId,
                parentId,
                "default content"
        );
    }
}
