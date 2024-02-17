package com.bizplus.boardsaturday.domain.dto;

import com.bizplus.boardsaturday.domain.type.ActiveStatus;
import com.bizplus.boardsaturday.domain.type.DeleteStatus;
import com.querydsl.core.annotations.QueryProjection;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class PostWithCategoryDto {

    private final Long id;
    private final String title;
    private final String body;
    private final ActiveStatus activeStatus;
    private final DeleteStatus deleteStatus;

    private final LocalDateTime createdAt;
    private final LocalDateTime updatedAt;
    private final String categoryName;

    @QueryProjection
    public PostWithCategoryDto(Long id, String title, String body,
                               ActiveStatus activeStatus, DeleteStatus deleteStatus,
                               LocalDateTime createdAt, LocalDateTime updatedAt,
                               String categoryName) {
        this.id = id;
        this.title = title;
        this.body = body;
        this.activeStatus = activeStatus;
        this.deleteStatus = deleteStatus;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
        this.categoryName = categoryName;
    }

}
