package com.bizplus.boardsaturday.application.component;

import com.bizplus.boardsaturday.application.request.CreateCategoryRequest;
import com.bizplus.boardsaturday.application.response.CategoryResponse;
import com.bizplus.boardsaturday.domain.entity.Category;
import com.bizplus.boardsaturday.domain.repository.CategoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class CategoryCreator {
    private final CategoryRepository categoryRepository;

    public CategoryResponse create(CreateCategoryRequest request) {
        // 1. 맨 마지막 순서를 가져와야 한다.
        Integer lastDisplayOrder = categoryRepository.lastDisplayOrder();

        // 2. 엔티티로 변환해줘야 한다.
        Category category = request.toEntity(lastDisplayOrder + 1);

        // 3. repository에 생성을 위임한다.
        Category categoryPS = categoryRepository.create(category);

        // 4. CategoryResponse로 변환해서 반환
        return CategoryResponse.of(categoryPS);
    }
}
