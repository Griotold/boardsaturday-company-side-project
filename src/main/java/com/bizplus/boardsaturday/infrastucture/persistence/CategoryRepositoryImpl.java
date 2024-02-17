package com.bizplus.boardsaturday.infrastucture.persistence;

import com.bizplus.boardsaturday.domain.dto.CategoryWithPostCountDto;
import com.bizplus.boardsaturday.domain.dto.QCategoryWithPostCountDto;
import com.bizplus.boardsaturday.domain.entity.Category;
import com.bizplus.boardsaturday.domain.repository.CategoryRepository;
import com.bizplus.boardsaturday.infrastucture.persistence.jpa.JpaCategoryRepository;
import com.querydsl.core.types.ConstructorExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

import static com.bizplus.boardsaturday.domain.entity.QCategory.*;
import static com.bizplus.boardsaturday.domain.entity.QPost.*;

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
    public void delete(Category category) {
        jpaCategoryRepository.delete(category);
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
    public Long count() {
        return jpaCategoryRepository.count();
    }

    @Override
    public List<Category> findAllByDisplayOrder() {
        return jpaCategoryRepository.findAllByOrderByDisplayOrderAsc();
    }

    @Override
    public Long countForUpdateDisplayOrder(List<Long> ids) {
        return query
                .select(category.count())
                .from(category)
                .where(category.id.in(ids))
                .fetchOne();
    }

    @Override
    public List<CategoryWithPostCountDto> findAllWithPostCount() {
        return query.select(selectCategoryWithPostCountDto())
                .from(post)
                .innerJoin(post.category, category)
                .orderBy(category.displayOrder.asc())
                .groupBy(category.id)
                .fetch();
    }

    private ConstructorExpression<CategoryWithPostCountDto> selectCategoryWithPostCountDto() {
        return new QCategoryWithPostCountDto(
                category.id,
                category.name,
                category.description,
                category.displayOrder,
                category.status,
                post.count()
        );
    }

}
