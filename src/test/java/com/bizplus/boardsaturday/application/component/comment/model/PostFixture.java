package com.bizplus.boardsaturday.application.component.comment.model;

import com.bizplus.boardsaturday.domain.entity.Post;
import com.bizplus.boardsaturday.domain.type.ActiveStatus;
import com.bizplus.boardsaturday.domain.type.DeleteStatus;

public class PostFixture {

    public static Post create() {
        return new Post(1L, "default title",
                "default body",
                null, null,
                ActiveStatus.ACTIVE,
                DeleteStatus.EXISTING);
    }
}
