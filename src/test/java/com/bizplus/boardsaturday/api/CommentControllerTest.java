package com.bizplus.boardsaturday.api;

import com.bizplus.boardsaturday.application.component.comment.model.MemberFixture;
import com.bizplus.boardsaturday.application.component.comment.model.PostFixture;
import com.bizplus.boardsaturday.application.request.comment.CreateCommentRequest;
import com.bizplus.boardsaturday.domain.entity.Comment;
import com.bizplus.boardsaturday.domain.entity.Member;
import com.bizplus.boardsaturday.domain.entity.Post;
import com.bizplus.boardsaturday.domain.repository.CommentRepository;
import com.bizplus.boardsaturday.domain.repository.MemberRepository;
import com.bizplus.boardsaturday.domain.repository.PostRepository;
import com.bizplus.boardsaturday.domain.type.ActiveStatus;
import com.bizplus.boardsaturday.domain.type.DeleteStatus;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@Transactional
@Slf4j
@AutoConfigureMockMvc
@ActiveProfiles("test")
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.MOCK)
class CommentControllerTest {

    @Autowired
    private MockMvc mvc;

    @Autowired
    private ObjectMapper om;

    @Autowired
    private EntityManager em;

    @Autowired
    private CommentRepository commentRepository;

    @Autowired
    private PostRepository postRepository;

    @Autowired
    private MemberRepository memberRepository;

    @BeforeEach
    void dataSet() {
        em.createNativeQuery("ALTER TABLE category ALTER COLUMN category_id RESTART WITH 1").executeUpdate();
        em.createNativeQuery("ALTER TABLE post ALTER COLUMN post_id RESTART WITH 1").executeUpdate();
        em.createNativeQuery("ALTER TABLE post_tag ALTER COLUMN post_tag_id RESTART WITH 1").executeUpdate();
        em.createNativeQuery("ALTER TABLE tag ALTER COLUMN tag_id RESTART WITH 1").executeUpdate();
        em.createNativeQuery("ALTER TABLE comment ALTER COLUMN comment_id RESTART WITH 1").executeUpdate();
        em.createNativeQuery("ALTER TABLE member ALTER COLUMN member_id RESTART WITH 1").executeUpdate();

        Post post = PostFixture.create();
        postRepository.create(post);
        Member member = MemberFixture.create();
        memberRepository.create(member);

        for (int i = 0; i < 10; i++) {
            Comment comment = new Comment("default content" + i, ActiveStatus.ACTIVE, DeleteStatus.EXISTING,
                    post, member, null);

            commentRepository.create(comment);
        }
    }

    @Test
    @DisplayName("컨트롤러 - 댓글 리스트 불러오기")
    void CommentController_comments() throws Exception {
        // given
        // when
        ResultActions resultActions = mvc.perform(get("/api/comments"));
        String responseBody = resultActions.andReturn().getResponse().getContentAsString();
        log.debug("테스트 : responseBody = {}", responseBody);

        // then
        resultActions.andExpect(status().isOk());
        resultActions.andExpect(jsonPath("code").value("R-001"));
        resultActions.andExpect(jsonPath("message").value("Good"));
    }

    @Test
    @DisplayName("카테고리 생성 - 성공")
    void CommentControllerTest_create_success() throws Exception {
        // given
        Long memberId = 1L;
        Long postId = 1L;
        String content = "댓글댓글댓글";
        CreateCommentRequest request = new CreateCommentRequest(memberId, postId, null, content);
        String requestBody = om.writeValueAsString(request);
        log.info("test : requestBody = {}", requestBody);

        // when
        ResultActions resultActions = mvc.perform(post("/api/comments")
                .content(requestBody)
                .contentType(MediaType.APPLICATION_JSON));
        String responseBody = resultActions.andReturn().getResponse().getContentAsString();
        log.info("test : responseBody = {}", responseBody);


        // then
        resultActions.andExpect(status().isCreated());
    }

    @Test
    void CommentControllerTest_create_fail_1() throws Exception {
        // given
        Long memberId = 2L;
        Long postId = 1L;
        String content = "댓글댓글댓글";
        CreateCommentRequest request = new CreateCommentRequest(memberId, postId, null, content);
        String requestBody = om.writeValueAsString(request);
        log.info("test : requestBody = {}", requestBody);

        // when
        ResultActions resultActions = mvc.perform(post("/api/comments")
                .content(requestBody)
                .contentType(MediaType.APPLICATION_JSON));
        String responseBody = resultActions.andReturn().getResponse().getContentAsString();
        log.info("test : responseBody = {}", responseBody);

        // then
        resultActions.andExpect(status().isBadRequest());
        resultActions.andExpect(jsonPath("errorCode").value("B-003"));
        resultActions.andExpect(jsonPath("errorMessage").value("회원을 찾을 수 없습니다."));
    }

}