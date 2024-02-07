package com.bizplus.boardsaturday.application.response.post;

import com.bizplus.boardsaturday.domain.dto.PostWithCategoryDto;
import com.bizplus.boardsaturday.global.util.DateFormatUtil;
import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public class PostResponse {

    private final Long id;
    private final String title;
    private final String body;
    private final String activeStatus;
    private final String deleteStatus;
    private final String createdAt;
    private final String updatedAt;
    private final String categoryName;

    public PostResponse(PostWithCategoryDto postWithCategoryDto) {
        this.id = postWithCategoryDto.getId();
        this.title = postWithCategoryDto.getTitle();
        this.body = postWithCategoryDto.getBody();
        this.activeStatus = postWithCategoryDto.getActiveStatus().name();
        this.deleteStatus = postWithCategoryDto.getDeleteStatus().name();
        this.createdAt = DateFormatUtil.toStringFormat(postWithCategoryDto.getCreatedAt());
        this.updatedAt = DateFormatUtil.toStringFormat(postWithCategoryDto.getUpdatedAt());
        this.categoryName = postWithCategoryDto.getCategoryName();
    }
}
