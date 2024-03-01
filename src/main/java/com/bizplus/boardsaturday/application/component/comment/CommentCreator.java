package com.bizplus.boardsaturday.application.component.comment;

import com.bizplus.boardsaturday.application.request.comment.CreateCommentRequest;
import com.bizplus.boardsaturday.domain.entity.Comment;
import com.bizplus.boardsaturday.domain.entity.Member;
import com.bizplus.boardsaturday.domain.entity.Post;
import com.bizplus.boardsaturday.domain.repository.CommentRepository;
import com.bizplus.boardsaturday.domain.repository.MemberRepository;
import com.bizplus.boardsaturday.domain.repository.PostRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;

@Component
@RequiredArgsConstructor
@Transactional
public class CommentCreator {
    private final CommentRepository commentRepository;
    private final MemberRepository memberRepository;
    private final PostRepository postRepository;

    public Comment create(CreateCommentRequest request) {
        Member member = memberRepository.findById(request.getMemberId())
                .orElseThrow(() -> new EntityNotFoundException("회원을 찾을 수 없습니다."));

        Post post = postRepository.findById(request.getPostId())
                .orElseThrow(() -> new EntityNotFoundException("게시글을 찾을 수 없습니다."));

        Comment comment = request.toEntity(member, post);

        return commentRepository.create(comment);
    }
}
