package com.bizplus.boardsaturday.domain.entity;

import com.bizplus.boardsaturday.domain.type.ActiveStatus;
import com.bizplus.boardsaturday.domain.type.DeleteStatus;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class CommentTest {
    // 활성화 댓글
    private Comment comment;

    // 비활성화 댓글
    private Comment inactiveComment;

    @BeforeEach
    void dataSet() {
        comment = new Comment("active content", ActiveStatus.ACTIVE,
                DeleteStatus.EXISTING, new Post(), new Member(), null);

        inactiveComment = new Comment("inactive content", ActiveStatus.INACTIVE,
                DeleteStatus.EXISTING, new Post(), new Member(), null);
    }

    @Test
    void Comment_changeStatusOn() throws Exception {
        // given
        // when
        inactiveComment.changeStatusOn();

        // then
        assertThat(inactiveComment.getActiveStatus()).isEqualTo(ActiveStatus.ACTIVE);
    }

    @Test
    void Comment_status_off() throws Exception {
        // given
        // when
        comment.changeStatusOff();

        // then
        assertThat(comment.getActiveStatus()).isEqualTo(ActiveStatus.INACTIVE);
    }

    @Test
    void Comment_delete() throws Exception {
        // given
        // when
        comment.delete();

        // then
        assertThat(comment.getDeleteStatus()).isEqualTo(DeleteStatus.DELETED);
    }
}