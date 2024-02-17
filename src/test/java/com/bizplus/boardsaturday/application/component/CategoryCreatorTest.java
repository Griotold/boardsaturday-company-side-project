package com.bizplus.boardsaturday.application.component;

import com.bizplus.boardsaturday.application.component.category.CategoryCreator;
import com.bizplus.boardsaturday.application.request.category.CreateCategoryRequest;
import com.bizplus.boardsaturday.application.response.category.CategoryResponse;
import com.bizplus.boardsaturday.domain.entity.Category;
import com.bizplus.boardsaturday.domain.repository.CategoryRepository;
import com.bizplus.boardsaturday.domain.type.ActiveStatus;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;

import static org.assertj.core.api.Assertions.*;

@SpringBootTest
@ActiveProfiles("test")
@Transactional
class CategoryCreatorTest {

    @Autowired
    CategoryCreator categoryCreator;

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
    void CategoryCreatorTest_create() throws Exception {
        // given
        String name = "test_category";
        String description = "blah blah";
        CreateCategoryRequest request = new CreateCategoryRequest(name, description);
        Integer lastDisplayOrder = categoryRepository.lastDisplayOrder();


        // when
        CategoryResponse categoryResponse = categoryCreator.create(request);

        // then
        assertThat(categoryResponse.getId()).isEqualTo(11);
        assertThat(categoryResponse.getDisplayOrder()).isEqualTo(lastDisplayOrder + 1);
    }

}