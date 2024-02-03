package com.bizplus.boardsaturday.application.component;

import com.bizplus.boardsaturday.application.request.UpdateCategoryRequest;
import com.bizplus.boardsaturday.application.response.CategoryResponse;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
@Transactional
class CategoryUpdaterTest {

    @Autowired CategoryUpdater categoryUpdater;

    @Test
    void CategoryUpdaterTest_update() throws Exception {
        // given
        Long id = 1L;
        String updatedName = "수정한 네임!";
        String updatedDescription = "수정한 설명";
        UpdateCategoryRequest request = new UpdateCategoryRequest(updatedName, updatedDescription);

        // when
        CategoryResponse response = categoryUpdater.update(request, id);

        // then
        Assertions.assertThat(response.getName()).isEqualTo("수정한 네임!");
        Assertions.assertThat(response.getDescription()).isEqualTo("수정한 설명");
    }

}