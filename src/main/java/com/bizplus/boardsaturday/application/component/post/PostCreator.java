package com.bizplus.boardsaturday.application.component.post;

import com.bizplus.boardsaturday.application.request.post.CreatePostRequest;
import com.bizplus.boardsaturday.domain.entity.Category;
import com.bizplus.boardsaturday.domain.entity.Post;
import com.bizplus.boardsaturday.domain.entity.PostTag;
import com.bizplus.boardsaturday.domain.entity.Tag;
import com.bizplus.boardsaturday.domain.repository.CategoryRepository;
import com.bizplus.boardsaturday.domain.repository.PostRepository;
import com.bizplus.boardsaturday.domain.repository.PostTagRepository;
import com.bizplus.boardsaturday.domain.repository.TagRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;

@Component
@RequiredArgsConstructor
@Transactional
public class PostCreator {
    private final PostRepository postRepository;
    private final CategoryRepository categoryRepository;
    private final TagRepository tagRepository;
    private final PostTagRepository postTagRepository;


    public Post create(CreatePostRequest request) {
        Category category = categoryRepository.findById(request.getCategoryId())
                .orElseThrow(() -> new EntityNotFoundException("카테고리를 찾을 수 없습니다."));

        Post post = request.toEntity(category);

        Post postPS = postRepository.create(post);

        for (Long tagId : request.getTagIds()) {
            Tag tag = tagRepository
                    .findById(tagId)
                    .orElseThrow(() -> new EntityNotFoundException("태그를 찾을 수 없습니다."));

            PostTag postTag = new PostTag(postPS, tag);

            postTagRepository.create(postTag);
        }
        return postPS;
    }
}
