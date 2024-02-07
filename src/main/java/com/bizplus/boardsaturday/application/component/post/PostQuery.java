package com.bizplus.boardsaturday.application.component.post;

import com.bizplus.boardsaturday.application.response.post.PostResponse;
import com.bizplus.boardsaturday.domain.dto.PostWithCategoryDto;
import com.bizplus.boardsaturday.domain.repository.PostRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class PostQuery {

    private final PostRepository postRepository;

    public List<PostResponse> findAll() {
        List<PostWithCategoryDto> all = postRepository.findAllOrderByCreatedAt();
        List<PostResponse> collect = all.stream()
                .map(PostResponse::new).collect(Collectors.toList());
        return collect;
    }
}
