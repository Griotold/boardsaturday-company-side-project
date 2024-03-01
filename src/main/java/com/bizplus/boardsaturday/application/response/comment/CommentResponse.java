package com.bizplus.boardsaturday.application.response.comment;

import com.bizplus.boardsaturday.domain.entity.Comment;
import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public class CommentResponse {
    private final Long id;
    private final Long postId;
    private final String postTitle;
    private final String content;
    private final Long memberId;
    private final String memberName;
    private final String memberEmail;
    private final String activeStatus;
    private final String deleteStatus;
    private final Long parentId;
    private final String parentContent;

    public CommentResponse(Comment comment) {
        this.id = comment.getId();
        this.postId = comment.getPost().getId();
        this.postTitle = comment.getPost().getTitle();
        this.content = comment.getContent();
        this.memberId = comment.getMember().getId();
        this.memberName = comment.getMember().getName();
        this.memberEmail = comment.getMember().getEmail();
        this.activeStatus = comment.getActiveStatus().name();
        this.deleteStatus = comment.getDeleteStatus().name();
        this.parentId = comment.getParent().getId();
        this.parentContent = comment.getParent().getContent();
    }
}
