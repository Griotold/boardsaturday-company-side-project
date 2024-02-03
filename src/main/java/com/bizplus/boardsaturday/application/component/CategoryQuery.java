package com.bizplus.boardsaturday.application.component;

import com.bizplus.boardsaturday.application.response.CategoryResponse;
import com.bizplus.boardsaturday.domain.entity.Category;
import com.bizplus.boardsaturday.domain.repository.CategoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class CategoryQuery {

    private final CategoryRepository categoryRepository;

    public List<CategoryResponse> findAll() {
        List<Category> all = categoryRepository.findAll();
        List<CategoryResponse> collect = all.stream()
                .map(CategoryResponse::new).collect(Collectors.toList());

        return collect;
    }
}
