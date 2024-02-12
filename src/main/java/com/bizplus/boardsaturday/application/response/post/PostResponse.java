package com.bizplus.boardsaturday.application.response.post;

import com.bizplus.boardsaturday.domain.dto.PostWithCategoryDto;
import com.bizplus.boardsaturday.domain.entity.Post;
import com.bizplus.boardsaturday.global.util.DateFormatUtil;
import lombok.Getter;
import lombok.ToString;

import java.util.List;
import java.util.stream.Collectors;

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
    private final List<String> tagNames;

    public PostResponse(Post post) {
        this.id = post.getId();
        this.title = post.getTitle();
        this.body = post.getBody();
        this.activeStatus = post.getActiveStatus().name();
        this.deleteStatus = post.getDeleteStatus().name();
        this.createdAt = DateFormatUtil.toStringFormat(post.getCreatedAt());
        this.updatedAt = DateFormatUtil.toStringFormat(post.getUpdatedAt());
        this.categoryName = post.getCategory().getName();
        this.tagNames = post.getPostTags().stream().map((pt) -> pt.getTag().getName()).collect(Collectors.toList());
    }
}
