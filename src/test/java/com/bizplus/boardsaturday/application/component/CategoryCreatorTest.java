package com.bizplus.boardsaturday.application.component;

import com.bizplus.boardsaturday.application.component.category.CategoryCreator;
import com.bizplus.boardsaturday.application.request.category.CreateCategoryRequest;
import com.bizplus.boardsaturday.application.response.category.CategoryResponse;
import com.bizplus.boardsaturday.domain.repository.CategoryRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.*;

@SpringBootTest
class CategoryCreatorTest {

    @Autowired
    CategoryCreator categoryCreator;

    @Autowired
    CategoryRepository categoryRepository;

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