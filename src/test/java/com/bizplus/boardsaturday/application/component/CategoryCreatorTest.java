package com.bizplus.boardsaturday.application.component;

import com.bizplus.boardsaturday.application.request.CreateCategoryRequest;
import com.bizplus.boardsaturday.application.response.CategoryResponse;
import com.bizplus.boardsaturday.domain.repository.CategoryRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
class CategoryCreatorTest {

    @Autowired
    CategoryCreator categoryCreator;

    @Autowired
    CategoryRepository categoryRepository;

    // todo 질문2
    @Test
    void CategoryCreatorTest_create() throws Exception {
        // given
        String name = "test_category";
        String description = "blah blah";
        CreateCategoryRequest request = new CreateCategoryRequest(name, description);
        Integer lastDisplayOrder = categoryRepository.lastDisplayOrder();
        Long lastId = categoryRepository.lastId();


        // when
        CategoryResponse categoryResponse = categoryCreator.create(request);

        // then
        assertThat(categoryResponse.getId()).isEqualTo(lastId + 1);
        assertThat(categoryResponse.getDisplayOrder()).isEqualTo(lastDisplayOrder + 1);
    }

}