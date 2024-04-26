package com.bizplus.boardsaturday.application.component.comment;

import com.bizplus.boardsaturday.application.component.comment.model.CommentFixture;
import com.bizplus.boardsaturday.application.component.comment.model.CreateCommentRequestFixture;
import com.bizplus.boardsaturday.application.component.comment.model.MemberFixture;
import com.bizplus.boardsaturday.application.component.comment.model.PostFixture;
import com.bizplus.boardsaturday.application.request.comment.CreateCommentRequest;
import com.bizplus.boardsaturday.domain.entity.Comment;
import com.bizplus.boardsaturday.domain.entity.Member;
import com.bizplus.boardsaturday.domain.entity.Post;
import com.bizplus.boardsaturday.domain.repository.CommentRepository;
import com.bizplus.boardsaturday.domain.repository.MemberRepository;
import com.bizplus.boardsaturday.domain.repository.PostRepository;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.context.ActiveProfiles;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@Slf4j
@ExtendWith(MockitoExtension.class)
@ActiveProfiles("test")
public class CommentCreatorMockTest {

    @Mock
    private CommentRepository commentRepository;

    @Mock
    private PostRepository postRepository;

    @Mock
    private MemberRepository memberRepository;

    @InjectMocks
    private CommentCreator commentCreator;

    @Test
    @DisplayName("댓글 생성 - Mock")
    void CommentCreatorMockTest_create() {
        // given
        // Fixture Object 패턴 적용
        Member member = MemberFixture.create();
        Post post = PostFixture.create();
        Comment comment = CommentFixture.create(member, post);
        CreateCommentRequest createCommentRequest = CreateCommentRequestFixture.create(member.getId(), post.getId());

        when(memberRepository.findById(any())).thenReturn(Optional.of(member));
        when(postRepository.findById(any())).thenReturn(Optional.of(post));
        when(commentRepository.create(any())).thenReturn(comment);

        // when
        Comment savedComment = commentCreator.create(createCommentRequest);

        // then
        assertThat(savedComment).isEqualTo(comment); // 또는 필요한 필드를 검증
    }

    @DisplayName("대댓글이 아니면 commentRepository.findById() 호출 X")
    @Test
    void CommentCreatorMockTest_create_notReply() {
        // given
        Member member = MemberFixture.create();
        Post post = PostFixture.create();
        Comment comment = CommentFixture.create(member, post);
        CreateCommentRequest createCommentRequest = CreateCommentRequestFixture.create(member.getId(), post.getId());

        when(memberRepository.findById(any())).thenReturn(Optional.of(member));
        when(postRepository.findById(any())).thenReturn(Optional.of(post));
        when(commentRepository.create(any())).thenReturn(comment);

        // when
        Comment savedComment = commentCreator.create(createCommentRequest);

        // then
        // 일반 댓글은 commentRepository.findById() 가 호출되어선 안된다.
        verify(memberRepository, times(1)).findById(any());
        verify(postRepository, times(1)).findById(any());
        verify(commentRepository, times(0)).findById(any());
        verify(commentRepository, times(1)).create(any());
    }

    @DisplayName("대댓글인 경우, commentRepository.findById() 호출!")
    @Test
    void CommentCreatorMockTest_create_reply() {
        // given
        Member member = MemberFixture.create();
        Post post = PostFixture.create();
        Comment comment = CommentFixture.createCommentWithParent(member, post);
        CreateCommentRequest createCommentRequest
                = CreateCommentRequestFixture.create(member.getId(),
                post.getId(),
                comment.getParent().getId());

        when(memberRepository.findById(any())).thenReturn(Optional.of(member));
        when(postRepository.findById(any())).thenReturn(Optional.of(post));
        when(commentRepository.create(any())).thenReturn(comment);
        when(commentRepository.findById(any())).thenReturn(Optional.of(comment.getParent()));

        // when
        Comment savedComment = commentCreator.create(createCommentRequest);

        // then
        // 대댓글은 commentRepository.findById() 가 호출되어야 한다.
        verify(memberRepository, times(1)).findById(any());
        verify(postRepository, times(1)).findById(any());
        verify(commentRepository, times(1)).findById(any());
        verify(commentRepository, times(1)).create(any());

    }

    @DisplayName("ArgumentCaptor - 인자값 캡쳐")
    @Test
    void CommentCreatorMockTest_create_argumentCaptor() {
        // given
        Member member = MemberFixture.create();
        Post post = PostFixture.create();
        Comment comment = CommentFixture.create(member, post);
        CreateCommentRequest createCommentRequest = CreateCommentRequestFixture.create(member.getId(), post.getId());

        when(memberRepository.findById(any())).thenReturn(Optional.of(member));
        when(postRepository.findById(any())).thenReturn(Optional.of(post));
        when(commentRepository.create(any())).thenReturn(comment);

        // ArgumentCaptor
        ArgumentCaptor<Comment> CommentArgumentCaptor = ArgumentCaptor.forClass(Comment.class);

        // when
        Comment savedComment = commentCreator.create(createCommentRequest);

        // then
        verify(commentRepository, times(1)).create(CommentArgumentCaptor.capture());
        Comment capturedComment = CommentArgumentCaptor.getValue();
        // capturedComment 는 save() 전이니까 id가 없다!
        assertThat(capturedComment.getId()).isNull();
        assertThat(savedComment.getContent()).isEqualTo(capturedComment.getContent());
        assertThat(savedComment.getPost().getTitle()).isEqualTo(capturedComment.getPost().getTitle());
        assertThat(savedComment.getMember().getName()).isEqualTo(capturedComment.getMember().getName());

    }
}
