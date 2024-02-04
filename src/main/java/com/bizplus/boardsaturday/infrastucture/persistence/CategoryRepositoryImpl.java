package com.bizplus.boardsaturday.infrastucture.persistence;

import com.bizplus.boardsaturday.domain.entity.Category;
import com.bizplus.boardsaturday.domain.repository.CategoryRepository;
import com.bizplus.boardsaturday.infrastucture.persistence.jpa.JpaCategoryRepository;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

import static com.bizplus.boardsaturday.domain.entity.QCategory.*;

@Repository
@RequiredArgsConstructor
public class CategoryRepositoryImpl implements CategoryRepository {
    private final JpaCategoryRepository jpaCategoryRepository;
    private final JPAQueryFactory query;

    @Override
    public Optional<Category> findById(Long id) {
        return jpaCategoryRepository.findById(id);
    }

    @Override
    public Integer lastDisplayOrder() {
        return query
                .select(category.displayOrder.max().coalesce(1))
                .from(category)
                .fetchOne();
    }

    @Override
    public Category create(Category category) {
        return jpaCategoryRepository.save(category);

    }

    @Override
    public List<Category> findAll() {
        return jpaCategoryRepository.findAll();
    }

    @Override
    public List<Category> findAllByDisplayOrder() {
        return jpaCategoryRepository.findAllByOrderByDisplayOrderAsc();
    }


}
