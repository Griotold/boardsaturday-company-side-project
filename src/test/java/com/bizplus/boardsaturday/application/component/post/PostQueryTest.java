package com.bizplus.boardsaturday.application.component.post;

import com.bizplus.boardsaturday.application.response.post.PostResponse;
import com.bizplus.boardsaturday.domain.entity.Category;
import com.bizplus.boardsaturday.domain.entity.Post;
import com.bizplus.boardsaturday.domain.repository.CategoryRepository;
import com.bizplus.boardsaturday.domain.repository.PostRepository;
import com.bizplus.boardsaturday.domain.type.ActiveStatus;
import com.bizplus.boardsaturday.domain.type.DeleteStatus;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
@Transactional
@ActiveProfiles("test")
class PostQueryTest {

    @Autowired
    PostQuery postQuery;

    @Autowired
    PostRepository postRepository;

    @Autowired
    CategoryRepository categoryRepository;

    @Autowired
    EntityManager em;
    @BeforeEach
    void dataSet() {
        em.createNativeQuery("ALTER TABLE category ALTER COLUMN category_id RESTART WITH 1").executeUpdate();
        em.createNativeQuery("ALTER TABLE post ALTER COLUMN post_id RESTART WITH 1").executeUpdate();
        em.createNativeQuery("ALTER TABLE post_tag ALTER COLUMN post_tag_id RESTART WITH 1").executeUpdate();
        em.createNativeQuery("ALTER TABLE tag ALTER COLUMN tag_id RESTART WITH 1").executeUpdate();
        Category category = new Category("category", "description", 1, ActiveStatus.ACTIVE);
        categoryRepository.create(category);

        for (int i = 0; i < 10; i++) {

            Post post = new Post("title" + i, "body" + i, category, ActiveStatus.ACTIVE, DeleteStatus.EXISTING);
            postRepository.create(post);
        }
    }

    @Test
    void findAll_test() throws Exception {
        List<PostResponse> all = postQuery.findAll();

        for (PostResponse postResponse : all) {
            System.out.println(postResponse);
        }
    }

}