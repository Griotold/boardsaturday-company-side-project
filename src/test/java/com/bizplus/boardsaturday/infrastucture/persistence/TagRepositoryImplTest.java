package com.bizplus.boardsaturday.infrastucture.persistence;

import com.bizplus.boardsaturday.domain.entity.Tag;
import com.bizplus.boardsaturday.domain.repository.TagRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.EntityNotFoundException;

import java.util.Optional;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;
@ActiveProfiles("test")
@SpringBootTest
@Transactional
class TagRepositoryImplTest {
    @Autowired
    TagRepository tagRepository;

    @Autowired
    EntityManager em;

    @BeforeEach
    void dataSet() {
        em.createNativeQuery("ALTER TABLE tag ALTER COLUMN tag_id RESTART WITH 1").executeUpdate();

        for (int i = 0; i < 10; i++) {
            Tag tag = new Tag("tag" + i);
            tagRepository.create(tag);
        }
    }

    @Test
    void create() throws Exception {
        // given
        Tag tag = new Tag("test");

        // when
        Tag tagPS = tagRepository.create(tag);

        // then
        assertThat(tagPS.getId()).isEqualTo(11);
    }

    @Test
    void findByName() throws Exception {
        // given
        String name = "tag5";
        // when
        Tag tag = tagRepository.findByName(name)
                .orElseThrow(() -> new EntityNotFoundException("태그를 찾을 수 없습니다."));

        // then
        assertThat(tag.getName()).isEqualTo(name);
    }

}