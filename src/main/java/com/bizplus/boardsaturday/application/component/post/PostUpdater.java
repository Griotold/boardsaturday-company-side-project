package com.bizplus.boardsaturday.application.component.post;

import com.bizplus.boardsaturday.application.request.post.UpdatePostRequest;
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

import javax.management.relation.RelationNotFoundException;
import javax.persistence.EntityNotFoundException;

@Component
@RequiredArgsConstructor
@Transactional
public class PostUpdater {
    private final PostRepository postRepository;
    private final CategoryRepository categoryRepository;
    private final PostTagRepository postTagRepository;
    private final TagRepository tagRepository;


    public void update(UpdatePostRequest request, Long id) {
        Post post = postRepository.findByIdWithCategory(id)
                .orElseThrow(() -> new EntityNotFoundException("게시글을 찾을 수 없습니다."));

        Category category = categoryRepository.findById(request.getCategoryId())
                .orElseThrow(() -> new EntityNotFoundException("카테고리를 찾을 수 없습니다."));

        post.update(request, category);

        postTagRepository.deleteAll(post);

        post.getPostTags().clear();

        for (Long tagId : request.getTagIds()) {
            Tag tag = tagRepository
                    .findById(tagId)
                    .orElseThrow(() -> new EntityNotFoundException("태그를 찾을 수 없습니다."));

            PostTag postTag = new PostTag(post, tag);
            post.addPostTag(postTag);
            postTagRepository.create(postTag);
        }
    }

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
