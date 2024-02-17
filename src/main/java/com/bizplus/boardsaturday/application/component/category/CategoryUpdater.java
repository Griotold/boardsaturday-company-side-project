package com.bizplus.boardsaturday.application.component.category;

import com.bizplus.boardsaturday.application.request.category.UpdateCategoryRequest;
import com.bizplus.boardsaturday.application.response.category.CategoryResponse;
import com.bizplus.boardsaturday.domain.entity.Category;
import com.bizplus.boardsaturday.domain.repository.CategoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

@Component
@RequiredArgsConstructor
@Transactional
public class CategoryUpdater {
    private final CategoryRepository categoryRepository;

    public CategoryResponse update(UpdateCategoryRequest request, Long id) {
        // todo EntityNotFoundException 처리 -> BusinessException 상속하는 CustomException 선언
        Category category = categoryRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("카테고리를 찾을 수 없습니다."));

        category.update(request);

        return CategoryResponse.of(category);
    }

    public CategoryResponse changeStatusOn(Long id) {
        Category category = categoryRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("카테고리를 찾을 수 없습니다."));

        category.changeStatusOn();

        return CategoryResponse.of(category);
    }

    public CategoryResponse changeStatusOff(Long id) {
        Category category = categoryRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("카테고리를 찾을 수 없습니다."));

        category.changeStatusOff();

        return CategoryResponse.of(category);
    }

    public void updateDisplayOrder(List<Long> ids) {
        Long count = categoryRepository.count();
        Long countIds = categoryRepository.countForUpdateDisplayOrder(ids);

        if (count.longValue() != countIds.longValue()) {
            throw new RuntimeException("입력이 잘못 되었습니다.");
        }

        AtomicInteger atomicInteger = new AtomicInteger(1);

        for (Long id : ids) {
            Category category = categoryRepository.findById(id)
                    .orElseThrow(() -> new EntityNotFoundException("카테고리를 찾을 수 없습니다."));

            category.updateDisplayOrder(atomicInteger.getAndIncrement());
        }
    }
}
