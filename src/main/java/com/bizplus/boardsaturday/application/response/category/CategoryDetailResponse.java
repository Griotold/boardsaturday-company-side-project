package com.bizplus.boardsaturday.application.response.category;

import com.bizplus.boardsaturday.domain.entity.Category;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class CategoryDetailResponse {
    private final Long id;
    private final String name;
    private final String description;
    private final int displayOrder;
    private final String status;
    private final LocalDateTime createdAt;
    private final LocalDateTime updatedAt;

    public CategoryDetailResponse(Category category) {
        this.id = category.getId();
        this.name = category.getName();
        this.description = category.getDescription();
        this.displayOrder = category.getDisplayOrder();
        this.status = category.getStatus().name();
        this.createdAt = category.getCreatedAt();
        this.updatedAt = category.getUpdatedAt();
    }

    public static CategoryDetailResponse of(Category category) {
        return new CategoryDetailResponse(category);
    }
}
