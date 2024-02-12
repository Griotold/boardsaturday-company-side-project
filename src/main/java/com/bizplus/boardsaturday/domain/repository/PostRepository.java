package com.bizplus.boardsaturday.domain.repository;

import com.bizplus.boardsaturday.domain.dto.PostWithCategoryDto;
import com.bizplus.boardsaturday.domain.entity.Post;

import java.util.List;
import java.util.Optional;

public interface PostRepository {

    Post create(Post post);

    List<Post> findAll();

    List<PostWithCategoryDto> findAllOrderByCreatedAt();

    Optional<Post> findById(Long id);

    Optional<Post> findByIdWithCategory(Long id);
}
