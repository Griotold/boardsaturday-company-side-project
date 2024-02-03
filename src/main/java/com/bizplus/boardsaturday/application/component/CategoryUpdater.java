package com.bizplus.boardsaturday.application.component;

import com.bizplus.boardsaturday.application.request.UpdateCategoryRequest;
import com.bizplus.boardsaturday.application.response.CategoryResponse;
import com.bizplus.boardsaturday.domain.entity.Category;
import com.bizplus.boardsaturday.domain.repository.CategoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;

@Component
@RequiredArgsConstructor
@Transactional
public class CategoryUpdater {
    private final CategoryRepository categoryRepository;

    public CategoryResponse update(UpdateCategoryRequest request, Long id) {
        // 1. 기존 category 엔티티를 가져온다.
        Category category = categoryRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("카테고리를 찾을 수 없습니다."));
        // 2. update dto의 값으로 교체 한다.
        category.update(request);

        // 3. CategoryResponse로 변환 후 리턴
        return CategoryResponse.of(category);
    }
}
