package com.bizplus.boardsaturday.infrastucture.persistence;

import com.bizplus.boardsaturday.domain.entity.Category;
import com.bizplus.boardsaturday.domain.repository.CategoryRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
class CategoryRepositoryImplTest {

    @Autowired
    CategoryRepository categoryRepository;

    @Test
    @DisplayName("find All 테스트")
    void findAll_test() throws Exception {
        // given
        // when
        List<Category> all = categoryRepository.findAll();

        // then
        Assertions.assertThat(all.size()).isEqualTo(10);
    }

}