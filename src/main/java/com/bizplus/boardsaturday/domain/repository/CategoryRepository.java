package com.bizplus.boardsaturday.domain.repository;

import com.bizplus.boardsaturday.domain.dto.CategoryWithPostCountDto;
import com.bizplus.boardsaturday.domain.entity.Category;

import java.util.List;
import java.util.Optional;

public interface CategoryRepository {

    Optional<Category> findById(Long id);

    void delete(Category category);

    Integer lastDisplayOrder();

    Category create(Category category);

    List<Category> findAll();

    List<Category> findAllByDisplayOrder();

    Long count();

    Long countForUpdateDisplayOrder(List<Long> ids);

    List<CategoryWithPostCountDto> findAllWithPostCount();

}
