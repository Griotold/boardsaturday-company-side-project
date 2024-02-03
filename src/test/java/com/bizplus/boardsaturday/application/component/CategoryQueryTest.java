package com.bizplus.boardsaturday.application.component;

import com.bizplus.boardsaturday.application.response.CategoryResponse;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
class CategoryQueryTest {

    @Autowired
    CategoryQuery categoryQuery;

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