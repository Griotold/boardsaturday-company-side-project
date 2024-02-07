package com.bizplus.boardsaturday.domain.repository;

import com.bizplus.boardsaturday.domain.entity.Post;

import java.util.List;

public interface PostRepository {

    Post create(Post post);

    List<Post> findAll();

    List<Post> findAllSortByCreatedAt();
}
