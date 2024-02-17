package com.bizplus.boardsaturday.application.component;

import com.bizplus.boardsaturday.application.component.category.CategoryQuery;
import com.bizplus.boardsaturday.application.response.category.CategoryResponse;
import com.bizplus.boardsaturday.domain.entity.Category;
import com.bizplus.boardsaturday.domain.repository.CategoryRepository;
import com.bizplus.boardsaturday.domain.type.ActiveStatus;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static org.assertj.core.api.Assertions.*;
@ActiveProfiles("test")
@SpringBootTest
@Transactional
class CategoryQueryTest {

    @Autowired
    CategoryQuery categoryQuery;

    @Autowired
    CategoryRepository categoryRepository;

    @BeforeEach
    void dataSet() {
        for (int i = 0; i < 10; i++) {
            Category category
                    = new Category("category" + i, "description" + i, i + 1, ActiveStatus.ACTIVE);
            categoryRepository.create(category);
        }
    }

    @Test
    @DisplayName("CategoryQuery.findAll() - CategoryResponse 변환")
    void findAll_test() throws Exception {
        // given
        // when
        List<CategoryResponse> all = categoryQuery.findAll();

        // then
        assertThat(all.size()).isEqualTo(10);
        assertThat(all.get(0)).isInstanceOf(CategoryResponse.class);
    }

}