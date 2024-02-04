package com.bizplus.boardsaturday.global.dummy;

import com.bizplus.boardsaturday.domain.entity.Category;
import com.bizplus.boardsaturday.domain.repository.CategoryRepository;
import com.bizplus.boardsaturday.domain.type.CategoryStatus;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.annotation.Transactional;

@Configuration
public class DummyDevInit {

    // 테스트 환경이랑 비즈니스 환경을 분리해라
    @Transactional
    @Bean
    CommandLineRunner init(CategoryRepository categoryRepository) {
        return (args -> {
            for (int i = 0; i < 10; i++) {
                Category category = new Category("카테고리" + i, "설명" + i, i, CategoryStatus.ACTIVE);
                categoryRepository.create(category);
            }
        });
    }
}
