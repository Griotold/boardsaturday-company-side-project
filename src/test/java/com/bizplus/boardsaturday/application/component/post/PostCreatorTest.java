package com.bizplus.boardsaturday.application.component.post;

import com.bizplus.boardsaturday.application.request.post.CreatePostRequest;
import com.bizplus.boardsaturday.domain.entity.Category;
import com.bizplus.boardsaturday.domain.entity.Post;
import com.bizplus.boardsaturday.domain.entity.PostTag;
import com.bizplus.boardsaturday.domain.entity.Tag;
import com.bizplus.boardsaturday.domain.repository.CategoryRepository;
import com.bizplus.boardsaturday.domain.repository.TagRepository;
import com.bizplus.boardsaturday.domain.type.ActiveStatus;
import lombok.extern.slf4j.Slf4j;
import org.apiguardian.api.API;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;
@Slf4j
@SpringBootTest
@ActiveProfiles("test")
@Transactional
class PostCreatorTest {

    @Autowired
    PostCreator postCreator;
    @Autowired
    CategoryRepository categoryRepository;
    @Autowired
    TagRepository tagRepository;
    @Autowired
    EntityManager em;

    @BeforeEach
    void dataSet() {
        em.createNativeQuery("ALTER TABLE category ALTER COLUMN category_id RESTART WITH 1").executeUpdate();
        em.createNativeQuery("ALTER TABLE post ALTER COLUMN post_id RESTART WITH 1").executeUpdate();
        em.createNativeQuery("ALTER TABLE post_tag ALTER COLUMN post_tag_id RESTART WITH 1").executeUpdate();
        em.createNativeQuery("ALTER TABLE tag ALTER COLUMN tag_id RESTART WITH 1").executeUpdate();
    }

    @Test
    void create() throws Exception {
        // given
        Category category = new Category("category1", "des1", 1, ActiveStatus.ACTIVE);
        categoryRepository.create(category);
        tagRepository.create(new Tag("tag1"));
        tagRepository.create(new Tag("tag2"));
        tagRepository.create(new Tag("tag3"));
        List<Long> tagIds = new ArrayList<>();
        tagIds.add(1L);
        tagIds.add(2L);
        CreatePostRequest request = new CreatePostRequest(1L, "title1", "bodybodybody", tagIds);

        // when
        Post post = postCreator.create(request);

        em.flush();
        em.clear();

        for (PostTag postTag : post.getPostTags()) {
            log.info("test : tagName = {}", postTag.getTag().getName());
        }

        // then
        assertThat(post.getPostTags().size()).isEqualTo(2);
    }

}