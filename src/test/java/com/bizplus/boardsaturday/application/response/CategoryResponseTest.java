package com.bizplus.boardsaturday.application.response;

import com.bizplus.boardsaturday.application.response.category.CategoryResponse;
import com.bizplus.boardsaturday.domain.entity.Category;
import com.bizplus.boardsaturday.domain.type.ActiveStatus;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;

class CategoryResponseTest {

    @Test
    @DisplayName("CategoryResponse의 정적 팩토리 메서드")
    void CategoryResponseTest_of() throws Exception {
        // given
        String name = "test_category";
        String description = "blah blah";
        int lastDisplayOrder = 50;
        Category category = new Category(name, description, lastDisplayOrder, ActiveStatus.ACTIVE);

        // when
        CategoryResponse categoryResponse = CategoryResponse.of(category);

        // then
        assertThat(categoryResponse).isInstanceOf(CategoryResponse.class);
        assertThat(categoryResponse.getDisplayOrder()).isEqualTo(50);
        assertThat(categoryResponse.getId()).isNull();
    }

}