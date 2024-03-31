package com.bizplus.boardsaturday.application.component.comment.model;

import com.bizplus.boardsaturday.domain.entity.Comment;
import com.bizplus.boardsaturday.domain.entity.Member;
import com.bizplus.boardsaturday.domain.entity.Post;
import com.bizplus.boardsaturday.domain.type.ActiveStatus;
import com.bizplus.boardsaturday.domain.type.DeleteStatus;

public class CommentFixture {

    public static Comment create(Member member, Post post) {
        return new Comment(1L, "default content",
                ActiveStatus.ACTIVE,
                DeleteStatus.EXISTING,
                post,
                member,
                null);
    }

    public static Comment createCommentWithParent(Member member, Post post) {
        Comment parent = new Comment(1L, "default parent content",
                ActiveStatus.ACTIVE,
                DeleteStatus.EXISTING,
                post,
                member,
                null);

        return new Comment(2L, "default content",
                ActiveStatus.ACTIVE,
                DeleteStatus.EXISTING,
                post,
                member,
                parent);
    }
}
