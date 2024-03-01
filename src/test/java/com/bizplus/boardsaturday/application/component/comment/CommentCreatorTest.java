package com.bizplus.boardsaturday.application.component.comment;

import com.bizplus.boardsaturday.application.request.comment.CreateCommentRequest;
import com.bizplus.boardsaturday.domain.entity.Comment;
import com.bizplus.boardsaturday.domain.entity.Member;
import com.bizplus.boardsaturday.domain.entity.Post;
import com.bizplus.boardsaturday.domain.repository.MemberRepository;
import com.bizplus.boardsaturday.domain.repository.PostRepository;
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

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;
@Slf4j
@SpringBootTest
@ActiveProfiles("test")
@Transactional
class CommentCreatorTest {

    @Autowired
    CommentCreator commentCreator;

    @Autowired
    PostRepository postRepository;

    @Autowired
    MemberRepository memberRepository;

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
    @DisplayName("일반 댓글 생성")
    void create() throws Exception {
        // given
        Member member = new Member("Ted", "ted2345@naver.com", ActiveStatus.ACTIVE, DeleteStatus.EXISTING);
        Member savedMember = memberRepository.create(member);

        Post post = new Post("Post Title", "Post Body", null, ActiveStatus.ACTIVE, DeleteStatus.EXISTING);
        Post savedPost = postRepository.create(post);

        // when
        CreateCommentRequest request
                = new CreateCommentRequest(savedMember.getId(), savedPost.getId(), null, "comment content");

        Comment comment = commentCreator.create(request);

        // then
        assertThat(comment.getId()).isEqualTo(1L);
        assertThat(comment.getContent()).isEqualTo(request.getContent());
        assertThat(comment.getMember().getName()).isEqualTo(savedMember.getName());
        assertThat(comment.getPost().getTitle()).isEqualTo(savedPost.getTitle());
    }
}