package com.bizplus.boardsaturday.domain.dto;

import com.querydsl.core.annotations.QueryProjection;
import lombok.Getter;

@Getter
public class CategoryWithPostCountDto {
    private final Long categoryId;
    private final String name;
    private final String description;
    private final int displayOrder;
    private final String status;
    private final Integer postCount;

    @QueryProjection
    public CategoryWithPostCountDto(Long categoryId,
                                    String name,
                                    String description,
                                    int displayOrder,
                                    String status,
                                    Integer postCount) {
        this.categoryId = categoryId;
        this.name = name;
        this.description = description;
        this.displayOrder = displayOrder;
        this.status = status;
        this.postCount = postCount;
    }
}
