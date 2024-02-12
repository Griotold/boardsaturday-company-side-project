package com.bizplus.boardsaturday.infrastucture.persistence;

import com.bizplus.boardsaturday.application.component.post.PostCreator;
import com.bizplus.boardsaturday.application.request.post.CreatePostRequest;
import com.bizplus.boardsaturday.domain.entity.Category;
import com.bizplus.boardsaturday.domain.entity.Post;
import com.bizplus.boardsaturday.domain.repository.CategoryRepository;
import com.bizplus.boardsaturday.domain.repository.PostRepository;
import com.bizplus.boardsaturday.domain.type.ActiveStatus;
import lombok.extern.slf4j.Slf4j;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import java.util.List;

import static org.assertj.core.api.Assertions.*;

@Slf4j
@SpringBootTest
@ActiveProfiles("test")
@Transactional
class PostRepositoryImplTest {

    @Autowired
    PostRepository postRepository;

    @Autowired
    PostCreator postCreator;

    @Autowired
    EntityManager em;

    @Autowired
    CategoryRepository categoryRepository;

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
    void searchByCategory() {
        // given
        Long categoryId = 1L;
        Category category = categoryRepository.findById(categoryId).get();

        // when
        List<Post> postsByCategory = postRepository.searchBy(category, ActiveStatus.ACTIVE, null, null);
        List<Post> postsByNull = postRepository.searchBy(null, ActiveStatus.ACTIVE, null, null);

        log.info("postsByCategory.size = {}", postsByCategory.size());
        for (Post post : postsByCategory) {
            log.info("post = {}", post.getTitle());
        }

        log.info("postsByNull.size = {}", postsByNull.size());

        // then
        assertThat(postsByCategory.size()).isEqualTo(3);
        assertThat(postsByNull.size()).isEqualTo(6);
    }
    @Test
    void searchByTitle() throws Exception {
        // given
        String title = "22";

        // when
        List<Post> postsByTitle = postRepository.searchBy(null, ActiveStatus.ACTIVE, title, null);
        List<Post> postsByNull = postRepository.searchBy(null, null, null, null);

        log.info("postsSearchByTitle.size = {}", postsByTitle.size());
        for (Post post : postsByTitle) {
            log.info("post.title = {}", post.getTitle());
        }

        log.info("postsByNull.size = {}", postsByNull.size());

        // then
        assertThat(postsByTitle.size()).isEqualTo(3);
        assertThat(postsByNull.size()).isEqualTo(6);
    }
}