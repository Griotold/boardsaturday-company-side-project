package com.bizplus.boardsaturday.application.component.post;

import com.bizplus.boardsaturday.application.response.post.PostResponse;
import com.bizplus.boardsaturday.domain.entity.Category;
import com.bizplus.boardsaturday.domain.entity.Post;
import com.bizplus.boardsaturday.domain.repository.CategoryRepository;
import com.bizplus.boardsaturday.domain.repository.PostRepository;
import com.bizplus.boardsaturday.domain.type.ActiveStatus;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class PostQuery {

    private final PostRepository postRepository;
    private final CategoryRepository categoryRepository;

    public List<PostResponse> findAll() {
        List<Post> all = postRepository.findAllWithCategoryAndTags();
        List<PostResponse> collect = all.stream()
                .map(PostResponse::new).collect(Collectors.toList());
        return collect;
    }

    public Page<PostResponse> searchWithPage(Long categoryId,
                                             ActiveStatus activeStatus,
                                             String title,
                                             String body,
                                             Pageable pageable) {
        Category category = null;

        if (categoryId != null) {
            category = categoryRepository.findById(categoryId)
                    .orElseThrow(() -> new EntityNotFoundException("카테고리를 찾을 수 없습니다."));
        }

        Page<Post> postPage = postRepository.searchByPage(category, activeStatus, title, body, pageable);
        return postPage
                .map(PostResponse::new);

    }
}
