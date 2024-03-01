package com.bizplus.boardsaturday.application.component.comment;

import com.bizplus.boardsaturday.domain.entity.Comment;
import com.bizplus.boardsaturday.domain.repository.CommentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;

@Component
@RequiredArgsConstructor
@Transactional
public class CommentDeleter {

    private final CommentRepository commentRepository;
    public void delete(Long id) {
        Comment comment = commentRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("댓글을 찾을 수 없습니다."));

        comment.delete();
    }
}
