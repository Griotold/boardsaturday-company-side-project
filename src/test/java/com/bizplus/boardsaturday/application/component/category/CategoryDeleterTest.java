package com.bizplus.boardsaturday.application.component.category;

import com.bizplus.boardsaturday.application.component.post.PostCreator;
import com.bizplus.boardsaturday.application.request.post.CreatePostRequest;
import com.bizplus.boardsaturday.domain.entity.Category;
import com.bizplus.boardsaturday.domain.entity.Tag;
import com.bizplus.boardsaturday.domain.repository.CategoryRepository;
import com.bizplus.boardsaturday.domain.repository.PostRepository;
import com.bizplus.boardsaturday.domain.repository.TagRepository;
import com.bizplus.boardsaturday.domain.type.ActiveStatus;
import com.bizplus.boardsaturday.global.error.ex.CategoryReferencedException;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.*;
@Slf4j
@SpringBootTest
@ActiveProfiles("test")
@Transactional
class CategoryDeleterTest {

    @Autowired
    PostRepository postRepository;

    @Autowired
    CategoryDeleter categoryDeleter;

    @Autowired
    EntityManager em;

    @Autowired
    CategoryRepository categoryRepository;

    @Autowired
    TagRepository tagRepository;

    @Autowired
    PostCreator postCreator;

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
        tagRepository.create(new Tag("tag1"));
        tagRepository.create(new Tag("tag2"));
        List<Long> tagIds1 = new ArrayList<>();
        tagIds1.add(1L);
        tagIds1.add(2L);

        postCreator.create(new CreatePostRequest(1L, "title1", "body1", tagIds1));
        postCreator.create(new CreatePostRequest(1L, "title222", "body1", tagIds1));
    }

    @Test
    @DisplayName("포스트가 참조하고 있는 카테고리를 삭제하려면 예외 발생")
    void test() throws Exception {
        // given
        Long id = 1L;

        // when
        assertThatThrownBy(() ->
                categoryDeleter.delete(id)).isInstanceOf(CategoryReferencedException.class);

    }

}