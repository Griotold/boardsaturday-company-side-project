package com.bizplus.boardsaturday.application.component;

import com.bizplus.boardsaturday.application.request.CreateCategoryRequest;
import com.bizplus.boardsaturday.application.response.CategoryResponse;
import com.bizplus.boardsaturday.domain.entity.Category;
import com.bizplus.boardsaturday.domain.repository.CategoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
@RequiredArgsConstructor
@Transactional
public class CategoryCreator {
    private final CategoryRepository categoryRepository;

    public CategoryResponse create(CreateCategoryRequest request) {
        Integer lastDisplayOrder = categoryRepository.lastDisplayOrder();

        Category category = request.toEntity(lastDisplayOrder + 1);

        Category categoryPS = categoryRepository.create(category);

        return CategoryResponse.of(categoryPS);
    }
}
