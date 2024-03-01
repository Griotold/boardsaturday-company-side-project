package com.bizplus.boardsaturday.application.request.post;

import com.bizplus.boardsaturday.domain.entity.Category;
import com.bizplus.boardsaturday.domain.entity.Post;
import com.bizplus.boardsaturday.domain.type.ActiveStatus;
import com.bizplus.boardsaturday.domain.type.DeleteStatus;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.List;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class CreatePostRequest {

    @NotNull(message = "카테고리 ID는 필수 입력값입니다.")
    private Long categoryId;

    @NotBlank(message = "제목은 필수 입력값입니다.")
    @Size(max = 100, message = "100자 이내로 적어주세요.")
    private String title;

    @NotEmpty
    private String body;

    private List<Long> tagIds;

    public CreatePostRequest(Long categoryId,
                             String title,
                             String body,
                             List<Long> tagIds) {
        this.categoryId = categoryId;
        this.title = title;
        this.body = body;
        this.tagIds = tagIds;
    }

    public Post toEntity(Category category) {
        return new Post(title,
                body,
                category,
                ActiveStatus.ACTIVE,
                DeleteStatus.EXISTING);
    }
}
