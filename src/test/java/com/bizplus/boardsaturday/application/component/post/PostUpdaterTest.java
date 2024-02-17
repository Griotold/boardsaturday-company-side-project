package com.bizplus.boardsaturday.application.component.post;

import com.bizplus.boardsaturday.application.request.post.CreatePostRequest;
import com.bizplus.boardsaturday.application.request.post.UpdatePostRequest;
import com.bizplus.boardsaturday.domain.entity.Category;
import com.bizplus.boardsaturday.domain.entity.Post;
import com.bizplus.boardsaturday.domain.entity.PostTag;
import com.bizplus.boardsaturday.domain.entity.Tag;
import com.bizplus.boardsaturday.domain.repository.CategoryRepository;
import com.bizplus.boardsaturday.domain.repository.PostRepository;
import com.bizplus.boardsaturday.domain.repository.TagRepository;
import com.bizplus.boardsaturday.domain.type.ActiveStatus;
import com.bizplus.boardsaturday.domain.type.DeleteStatus;
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
import javax.persistence.EntityNotFoundException;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;
@Slf4j
@SpringBootTest
@ActiveProfiles("test")
@Transactional
class PostUpdaterTest {

    @Autowired
    PostUpdater postUpdater;

    @Autowired
    PostCreator postCreator;

    @Autowired
    PostRepository postRepository;

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

        Category category1 = new Category("category1", "des1", 1, ActiveStatus.ACTIVE);
        Category category2 = new Category("category2", "des2", 2, ActiveStatus.ACTIVE);
        categoryRepository.create(category1);
        categoryRepository.create(category2);

        tagRepository.create(new Tag("tag1"));
        tagRepository.create(new Tag("tag2"));
        tagRepository.create(new Tag("tag3"));
        tagRepository.create(new Tag("tag4"));
        tagRepository.create(new Tag("tag5"));
        tagRepository.create(new Tag("tag6"));

        List<Long> tagIds = new ArrayList<>();
        tagIds.add(1L);
        tagIds.add(2L);
        CreatePostRequest request = new CreatePostRequest(1L, "title1", "body1", tagIds);

        Post post = postCreator.create(request);
    }

    @Test
    void update() {
        // given
        Post post = postRepository.findById(1L).get();
        List<Long> newTagIds = new ArrayList<>();
        newTagIds.add(3L);
        newTagIds.add(4L);
        newTagIds.add(5L);
        newTagIds.add(6L);
        UpdatePostRequest updatePostRequest = new UpdatePostRequest(2L, "title2", "body2", newTagIds);

        // when
        postUpdater.update(updatePostRequest, post.getId());

        em.flush();
        em.clear();

        log.info("post.title = {}", post.getTitle());
        log.info("post.body = {}", post.getBody());
        log.info("post.categoryId = {}", post.getCategory().getId());
        for (PostTag postTag : post.getPostTags()) {
            log.info("post.postTag.name = {}", postTag.getTag().getName());
        }

        assertThat(post.getTitle()).isEqualTo("title2");
        assertThat(post.getCategory().getId()).isEqualTo(2L);
        assertThat(post.getPostTags().size()).isEqualTo(4);
    }

    @Test
    @DisplayName("잘못된 카테고리id를 넣었을 때")
    void update_fail_1() {
        //given
        Post post = postRepository.findById(1L).get();
        List<Long> newTagIds = new ArrayList<>();
        newTagIds.add(3L);
        newTagIds.add(4L);
        newTagIds.add(5L);
        newTagIds.add(6L);
        UpdatePostRequest updatePostRequest = new UpdatePostRequest(3L, "title2", "body2", newTagIds);

        assertThatThrownBy(() ->
                postUpdater.update(updatePostRequest, post.getId())).isInstanceOf(EntityNotFoundException.class);

    }

    @Test
    @DisplayName("태그 id 리스트가 빈 리스트일 떄")
    void update_fail_2() {
        // given
        Post post = postRepository.findById(1L).get();
        UpdatePostRequest updatePostRequest = new UpdatePostRequest(2L, "title2", "body2", List.of());

        // when
        postUpdater.update(updatePostRequest, post.getId());

        // then
        assertThat(post.getPostTags().size()).isEqualTo(0);
    }

    @Test
    @DisplayName("태그id 리스트가 없는 태그id도 포함될 때")
    void update_fail_3() {
        // given
        Post post = postRepository.findById(1L).get();
        List<Long> newTagIds = new ArrayList<>();
        newTagIds.add(3L);
        newTagIds.add(4L);
        newTagIds.add(5L);
        newTagIds.add(6L);
        newTagIds.add(7L);
        UpdatePostRequest updatePostRequest = new UpdatePostRequest(2L, "title2", "body2", newTagIds);

        // when
        assertThatThrownBy(() ->
                postUpdater.update(updatePostRequest, post.getId())).isInstanceOf(EntityNotFoundException.class);
    }
}