package com.bizplus.boardsaturday.application.service;

import com.bizplus.boardsaturday.application.component.CategoryCreator;
import com.bizplus.boardsaturday.application.component.CategoryQuery;
import com.bizplus.boardsaturday.application.component.CategoryUpdater;
import com.bizplus.boardsaturday.application.request.CreateCategoryRequest;
import com.bizplus.boardsaturday.application.request.UpdateCategoryRequest;
import com.bizplus.boardsaturday.application.response.CategoryResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
//@Transactional(readOnly = true) // todo 질문3. 서비스에 붙이면 안되는 이유
public class CategoryService {

    private final CategoryQuery categoryQuery;
    private final CategoryCreator categoryCreator;
    private final CategoryUpdater categoryUpdater;

    public List<CategoryResponse> findAll() {
        return categoryQuery.findAll();
    }

    public CategoryResponse create(CreateCategoryRequest request) {
        return categoryCreator.create(request);
    }

    public CategoryResponse update(UpdateCategoryRequest request, Long id) {
        return categoryUpdater.update(request, id);
    }

}
