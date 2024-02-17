package com.bizplus.boardsaturday.application.component.tag;

import com.bizplus.boardsaturday.application.request.tag.CreateTagRequest;
import com.bizplus.boardsaturday.application.response.tag.TagResponse;
import com.bizplus.boardsaturday.domain.entity.Category;
import com.bizplus.boardsaturday.domain.entity.Tag;
import com.bizplus.boardsaturday.domain.repository.TagRepository;
import com.bizplus.boardsaturday.domain.type.ActiveStatus;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
@ActiveProfiles("test")
@Transactional
class TagCreatorTest {

    @Autowired TagCreator tagCreator;

    @Autowired
    TagRepository tagRepository;

    @Autowired
    EntityManager em;

    @BeforeEach
    void dataSet() {
        em.createNativeQuery("ALTER TABLE tag ALTER COLUMN tag_id RESTART WITH 1").executeUpdate();

    }
    @Test
    @DisplayName("태그가 기존에 없으면 새로 생성")
    void create_success() {
        // given
        CreateTagRequest request = new CreateTagRequest("test");

        // when
        TagResponse tagResponse = tagCreator.create(request);

        // then
        assertThat(tagResponse.getId()).isEqualTo(1L);
    }

    @Test
    @DisplayName("태그가 기존에 있으면 기존값 가져오기")
    void create_duplicate() throws Exception {
        // given
        tagRepository.create(new Tag("test"));
        tagRepository.create(new Tag("test2"));

        em.flush();
        em.clear();

        CreateTagRequest request = new CreateTagRequest("test");

        // when
        TagResponse tagResponse = tagCreator.create(request);

        // then
        assertThat(tagResponse.getId()).isEqualTo(1L);
    }
}