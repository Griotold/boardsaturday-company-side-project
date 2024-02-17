package com.bizplus.boardsaturday.application.component.post;

import com.bizplus.boardsaturday.domain.entity.Post;
import com.bizplus.boardsaturday.domain.repository.PostRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;

@Component
@RequiredArgsConstructor
@Transactional
public class PostDeleter {

    private final PostRepository postRepository;

    public void delete(Long id) {
        Post post = postRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("게시글을 찾을 수 없습니다."));

        post.delete();
    }
}
