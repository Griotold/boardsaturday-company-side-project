package com.bizplus.boardsaturday.application.response.category;

import com.bizplus.boardsaturday.domain.entity.Category;
import lombok.Getter;

@Getter
public class CategoryWithPostCountResponse {
    private final Long id;
    private final String name;
    private final String description;
    private final int displayOrder;
    private final String status;
    private final Integer postCount;

    public CategoryWithPostCountResponse(Category category, Integer postCount) {
        this.id = category.getId();
        this.name = category.getName();
        this.description = category.getDescription();
        this.displayOrder = category.getDisplayOrder();
        this.status = category.getStatus().name();
        this.postCount = postCount;
    }

    public static CategoryWithPostCountResponse of(Category category, Integer postCount) {
        return new CategoryWithPostCountResponse(category, postCount);
    }
}
