package com.bizplus.boardsaturday.application.component.category;

import com.bizplus.boardsaturday.application.response.category.CategoryDetailResponse;
import com.bizplus.boardsaturday.application.response.category.CategoryResponse;
import com.bizplus.boardsaturday.application.response.category.CategoryWithPostCountResponse;
import com.bizplus.boardsaturday.domain.dto.CategoryWithPostCountDto;
import com.bizplus.boardsaturday.domain.entity.Category;
import com.bizplus.boardsaturday.domain.repository.CategoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class CategoryQuery {

    private final CategoryRepository categoryRepository;

    public List<CategoryResponse> findAll() {
        List<Category> all = categoryRepository.findAllByDisplayOrder();
        List<CategoryResponse> collect = all.stream()
                .map(CategoryResponse::new).collect(Collectors.toList());

        return collect;
    }

    public List<CategoryWithPostCountResponse> findAllWithPostCount() {
        List<CategoryWithPostCountDto> all = categoryRepository.findAllWithPostCount();
        return all.stream().map(CategoryWithPostCountResponse::new).collect(Collectors.toList());
    }

    public CategoryDetailResponse findOne(Long id) {
        Category category = categoryRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("카테고리를 찾을 수 없습니다."));

        return CategoryDetailResponse.of(category);
    }
}
