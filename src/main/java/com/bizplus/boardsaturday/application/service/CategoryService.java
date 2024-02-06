package com.bizplus.boardsaturday.application.service;

import com.bizplus.boardsaturday.application.component.CategoryCreator;
import com.bizplus.boardsaturday.application.component.CategoryDeleter;
import com.bizplus.boardsaturday.application.component.CategoryQuery;
import com.bizplus.boardsaturday.application.component.CategoryUpdater;
import com.bizplus.boardsaturday.application.request.CreateCategoryRequest;
import com.bizplus.boardsaturday.application.request.UpdateCategoryRequest;
import com.bizplus.boardsaturday.application.response.CategoryDetailResponse;
import com.bizplus.boardsaturday.application.response.CategoryResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class CategoryService {

    private final CategoryQuery categoryQuery;
    private final CategoryCreator categoryCreator;
    private final CategoryUpdater categoryUpdater;
    private final CategoryDeleter categoryDeleter;

    public List<CategoryResponse> findAll() {
        return categoryQuery.findAll();
    }

    public CategoryResponse create(CreateCategoryRequest request) {
        return categoryCreator.create(request);
    }

    public CategoryResponse update(UpdateCategoryRequest request, Long id) {
        return categoryUpdater.update(request, id);
    }

    public CategoryDetailResponse findOne(Long id) {
        return categoryQuery.findOne(id);
    }

    public CategoryResponse changeStatusOn(Long id) {
        return categoryUpdater.changeStatusOn(id);
    }

    public CategoryResponse changeStatusOff(Long id) {
        return categoryUpdater.changeStatusOff(id);
    }

    public void delete(Long id) {
        categoryDeleter.delete(id);
    }

    public void updateDisplayOrder(List<Long> ids) {
        categoryUpdater.updateDisplayOrder(ids);
    }

}
