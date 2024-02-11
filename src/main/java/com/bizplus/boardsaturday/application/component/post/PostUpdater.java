package com.bizplus.boardsaturday.application.component.post;

import com.bizplus.boardsaturday.domain.entity.Post;
import com.bizplus.boardsaturday.domain.repository.PostRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import javax.management.relation.RelationNotFoundException;
import javax.persistence.EntityNotFoundException;

@Component
@RequiredArgsConstructor
@Transactional
public class PostUpdater {
    private final PostRepository postRepository;

    public void changeStatusOn(Long id) {
        Post post = postRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("게시글을 찾을 수 없습니다."));

        post.changeStatusOn();
    }

    public void changeStatusOff(Long id) {
        Post post = postRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("게시글을 찾을 수 없습니다."));

        post.changeStatusOff();
    }
}
