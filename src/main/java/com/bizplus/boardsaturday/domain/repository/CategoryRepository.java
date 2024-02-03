package com.bizplus.boardsaturday.domain.repository;

import com.bizplus.boardsaturday.domain.entity.Category;

import java.util.List;

public interface CategoryRepository {

    Long lastId();

    Integer lastDisplayOrder();

    Category create(Category category);

    List<Category> findAll();
}
