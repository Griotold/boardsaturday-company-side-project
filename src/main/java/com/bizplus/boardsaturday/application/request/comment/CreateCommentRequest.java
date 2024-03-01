package com.bizplus.boardsaturday.application.request.comment;

import com.bizplus.boardsaturday.domain.entity.Comment;
import com.bizplus.boardsaturday.domain.entity.Member;
import com.bizplus.boardsaturday.domain.entity.Post;
import com.bizplus.boardsaturday.domain.type.ActiveStatus;
import com.bizplus.boardsaturday.domain.type.DeleteStatus;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class CreateCommentRequest {

    @NotNull(message = "member ID는 필수 입력값입니다.")
    private Long memberId;

    @NotNull(message = "게시글 ID는 필수 입력값입니다.")
    private Long postId;

    // 일반적인 댓글은 null이 들어오고, 대댓글이면 부모 댓글의 id가 들어온다.
    private Long parentId;

    @NotBlank(message = "댓글 내용을 필수 입력값입니다.")
    @Size(max = 100, message = "100자 이내로 적어주세요.")
    private String content;

    public CreateCommentRequest(Long memberId,
                                Long postId,
                                Long parentId,
                                String content) {
        this.memberId = memberId;
        this.postId = postId;
        this.parentId = parentId;
        this.content = content;
    }

    public Comment toEntity(Member member, Post post, Comment parent) {
        return new Comment(this.content,
                ActiveStatus.ACTIVE,
                DeleteStatus.EXISTING,
                post,
                member,
                parent);
    }

    public boolean isReply() {
        return this.parentId != null;
    }
}
