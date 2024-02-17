package com.bizplus.boardsaturday.application.component;

import com.bizplus.boardsaturday.application.component.category.CategoryUpdater;
import com.bizplus.boardsaturday.application.request.category.UpdateCategoryRequest;
import com.bizplus.boardsaturday.application.response.category.CategoryResponse;
import com.bizplus.boardsaturday.domain.entity.Category;
import com.bizplus.boardsaturday.domain.repository.CategoryRepository;
import com.bizplus.boardsaturday.domain.type.ActiveStatus;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.EntityNotFoundException;
@ActiveProfiles("test")
@SpringBootTest
@Transactional
class CategoryUpdaterTest {

    @Autowired
    CategoryUpdater categoryUpdater;

    @Autowired
    CategoryRepository categoryRepository;

    @Autowired
    EntityManager em;

    @BeforeEach
    void dataSet() {
        em.createNativeQuery("ALTER TABLE category ALTER COLUMN category_id RESTART WITH 1").executeUpdate();
        em.createNativeQuery("ALTER TABLE post ALTER COLUMN post_id RESTART WITH 1").executeUpdate();

        for (int i = 0; i < 10; i++) {
            Category category
                    = new Category("category" + i, "description" + i, i + 1, ActiveStatus.ACTIVE);
            categoryRepository.create(category);
        }
    }

    @Test
    void CategoryUpdaterTest_update() throws Exception {
        // given
        Long id = 1L;
        String updatedName = "수정한 네임!";
        String updatedDescription = "수정한 설명";
        UpdateCategoryRequest request = new UpdateCategoryRequest(updatedName, updatedDescription);

        // when
        CategoryResponse response = categoryUpdater.update(request, id);
        Category category = categoryRepository.findById(id)
                .orElseThrow(EntityNotFoundException::new);

        // then
        Assertions.assertThat(category.getName()).isEqualTo("수정한 네임!");
        Assertions.assertThat(category.getDescription()).isEqualTo("수정한 설명");
    }

}