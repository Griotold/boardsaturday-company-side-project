package com.bizplus.boardsaturday.infrastucture.persistence;

import com.bizplus.boardsaturday.domain.entity.Comment;
import com.bizplus.boardsaturday.domain.entity.Member;
import com.bizplus.boardsaturday.domain.entity.Post;
import com.bizplus.boardsaturday.domain.repository.CommentRepository;
import com.bizplus.boardsaturday.domain.type.ActiveStatus;
import com.bizplus.boardsaturday.domain.type.DeleteStatus;
import com.bizplus.boardsaturday.infrastucture.config.QuerydslConfig;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.jdbc.EmbeddedDatabaseConnection;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.ActiveProfiles;

import javax.persistence.EntityManager;

import static org.assertj.core.api.Assertions.assertThat;

@Slf4j
@DataJpaTest
@Import({CommentRepositoryImpl.class, QuerydslConfig.class})
@AutoConfigureTestDatabase(connection = EmbeddedDatabaseConnection.H2)
@ActiveProfiles("test")
class CommentRepositoryImplTest {
    @Autowired
    CommentRepository commentRepository;

    @Autowired
    EntityManager em;

    @BeforeEach
    void dataSet() {
        em.createNativeQuery("ALTER TABLE category ALTER COLUMN category_id RESTART WITH 1").executeUpdate();
        em.createNativeQuery("ALTER TABLE post ALTER COLUMN post_id RESTART WITH 1").executeUpdate();
        em.createNativeQuery("ALTER TABLE post_tag ALTER COLUMN post_tag_id RESTART WITH 1").executeUpdate();
        em.createNativeQuery("ALTER TABLE tag ALTER COLUMN tag_id RESTART WITH 1").executeUpdate();
        em.createNativeQuery("ALTER TABLE comment ALTER COLUMN comment_id RESTART WITH 1").executeUpdate();
        em.createNativeQuery("ALTER TABLE member ALTER COLUMN member_id RESTART WITH 1").executeUpdate();
    }
    
    @Test
    void CommentRepositoryImpl_create() {
        // given == Arrange
        String content = "Muad'Dib";
        Post post = new Post("title", "body", null, ActiveStatus.ACTIVE, DeleteStatus.EXISTING);
        Member member = new Member("Griotold", "griotold@naver.com", ActiveStatus.ACTIVE, DeleteStatus.EXISTING);
        Comment comment = new Comment(content, ActiveStatus.ACTIVE, DeleteStatus.EXISTING, post, member, null);

        // when == Act
        Comment savedComment = commentRepository.create(comment);

        // then == Assert
        assertThat(savedComment).isNotNull();
        assertThat(savedComment.getId()).isGreaterThan(0L);
    }
}