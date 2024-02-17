package com.bizplus.boardsaturday.application.response.category;

import com.bizplus.boardsaturday.domain.dto.CategoryWithPostCountDto;
import com.bizplus.boardsaturday.domain.entity.Category;
import lombok.Getter;

@Getter
public class CategoryWithPostCountResponse {
    private final Long categoryId;
    private final String name;
    private final String description;
    private final int displayOrder;
    private final String status;
    private final Long postCount;

    public CategoryWithPostCountResponse(CategoryWithPostCountDto dto) {
        this.categoryId = dto.getCategoryId();
        this.name = dto.getName();
        this.description = dto.getDescription();
        this.displayOrder = dto.getDisplayOrder();
        this.status = dto.getStatus().name();
        this.postCount = dto.getPostCount();
    }

    public static CategoryWithPostCountResponse of(CategoryWithPostCountDto dto) {
        return new CategoryWithPostCountResponse(dto);
    }
}
