package com.bizplus.boardsaturday.infrastucture.persistence;

import com.bizplus.boardsaturday.domain.entity.Category;
import com.bizplus.boardsaturday.domain.repository.CategoryRepository;
import com.bizplus.boardsaturday.infrastucture.persistence.jpa.JpaCategoryRepository;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

import static com.bizplus.boardsaturday.domain.entity.QCategory.*;

@Repository
@RequiredArgsConstructor
public class CategoryRepositoryImpl implements CategoryRepository {
    private final JpaCategoryRepository categoryRepository;
    private final JPAQueryFactory query;

    @Override
    public Integer lastDisplayOrder() {
        return query
                .select(category.displayOrder.max())
                .from(category)
                .fetchOne();
    }

    @Override
    public Category create(Category category) {
        return categoryRepository.save(category);

    }

    @Override
    public List<Category> findAll() {
        return categoryRepository.findAll();
    }


}
