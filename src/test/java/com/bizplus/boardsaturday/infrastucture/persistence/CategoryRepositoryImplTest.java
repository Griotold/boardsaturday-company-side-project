package com.bizplus.boardsaturday.infrastucture.persistence;

import com.bizplus.boardsaturday.application.component.post.PostCreator;
import com.bizplus.boardsaturday.application.request.post.CreatePostRequest;
import com.bizplus.boardsaturday.domain.dto.CategoryWithPostCountDto;
import com.bizplus.boardsaturday.domain.entity.Category;
import com.bizplus.boardsaturday.domain.repository.CategoryRepository;
import com.bizplus.boardsaturday.domain.type.ActiveStatus;
import lombok.extern.slf4j.Slf4j;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import java.util.List;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;
@Slf4j
@ActiveProfiles("test")
@SpringBootTest
@Transactional
class CategoryRepositoryImplTest {

    @Autowired
    CategoryRepository categoryRepository;

    @Autowired
    PostCreator postCreator;

    @Autowired
    EntityManager em;

    @BeforeEach
    void dataSet() {
        em.createNativeQuery("ALTER TABLE category ALTER COLUMN category_id RESTART WITH 1").executeUpdate();
        em.createNativeQuery("ALTER TABLE post ALTER COLUMN post_id RESTART WITH 1").executeUpdate();
        em.createNativeQuery("ALTER TABLE post_tag ALTER COLUMN post_tag_id RESTART WITH 1").executeUpdate();
        em.createNativeQuery("ALTER TABLE tag ALTER COLUMN tag_id RESTART WITH 1").executeUpdate();

        Category category1 = new Category("category1", "des1", 1, ActiveStatus.ACTIVE);
        Category category2 = new Category("category2", "des2", 2, ActiveStatus.ACTIVE);
        categoryRepository.create(category1);
        categoryRepository.create(category2);

        postCreator.create(new CreatePostRequest(1L, "title1", "body1", List.of()));
        postCreator.create(new CreatePostRequest(1L, "title222", "body1", List.of()));
        postCreator.create(new CreatePostRequest(1L, "title3", "body1", List.of()));
        postCreator.create(new CreatePostRequest(2L, "title224", "body1", List.of()));
        postCreator.create(new CreatePostRequest(2L, "title5", "body1", List.of()));
        postCreator.create(new CreatePostRequest(2L, "title226", "body1", List.of()));
    }

    @Test
    @DisplayName("find All 테스트")
    void findAll_test() throws Exception {
        // given
        // when
        List<Category> all = categoryRepository.findAll();

        // then
        assertThat(all.size()).isEqualTo(2);
    }

    @Test
    @DisplayName("마지막 노출순서 가져오기")
    void lastDisplayOrder_test() throws Exception {
        // given
        // when
        Integer lastDisplayOrder = categoryRepository.lastDisplayOrder();

        // then
        assertThat(lastDisplayOrder).isEqualTo(2);
    }

    @Test
    @DisplayName("게시글 갯수를 포함한 카테고리 리스트")
    void findAllWithPostCount() throws Exception {
        // given
        // when
        List<CategoryWithPostCountDto> dto = categoryRepository.findAllWithPostCount();
        for (CategoryWithPostCountDto el : dto) {
            log.info("categoryId = {}", el.getCategoryId());
            log.info("categoryName = {}", el.getName());
            log.info("postCount = {}", el.getPostCount());
        }

        // then
    }

}