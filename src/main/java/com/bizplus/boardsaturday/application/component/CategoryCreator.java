package com.bizplus.boardsaturday.application.component;

import com.bizplus.boardsaturday.application.request.CreateCategoryRequest;
import com.bizplus.boardsaturday.application.response.CategoryResponse;
import com.bizplus.boardsaturday.domain.repository.CategoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class CategoryCreator {
    private final CategoryRepository categoryRepository;

    public CategoryResponse create(CreateCategoryRequest request) {
        // 1. 맨 마지막 순서를 가져와야 한다.

        // 2. 엔티티로 변환해줘야 한다.

        // 3. repository에 생성을 위임한다.
        return null;
    }
}
