package com.bizplus.boardsaturday.application.request;

import com.bizplus.boardsaturday.domain.entity.Category;
import com.bizplus.boardsaturday.domain.type.ActiveStatus;
import lombok.extern.slf4j.Slf4j;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;
@Slf4j
class CreateCategoryRequestTest {

    @Test
    void CreateCategoryRequestTest_toEntity() throws Exception {
        // given
        String name = "test_category";
        String description = "blah blah balh";
        CreateCategoryRequest request = new CreateCategoryRequest(name, description);
        int lastDisplayOrder = 50;

        // when
        Category category = request.toEntity(lastDisplayOrder + 1);
        log.info("category = {}", category);

        // then
        assertThat(category.getDisplayOrder()).isEqualTo(51);
        assertThat(category.getStatus()).isEqualTo(ActiveStatus.ACTIVE);

    }

}