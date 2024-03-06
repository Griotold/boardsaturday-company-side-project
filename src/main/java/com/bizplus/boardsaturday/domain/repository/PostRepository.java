package com.bizplus.boardsaturday.domain.repository;

import com.bizplus.boardsaturday.domain.entity.Category;
import com.bizplus.boardsaturday.domain.entity.Post;
import com.bizplus.boardsaturday.domain.type.ActiveStatus;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

public interface PostRepository {

    Post create(Post post);

    List<Post> findAll();

    Optional<Post> findById(Long id);

    Optional<Post> findByIdWithFetch(Long id);

    Optional<Post> findByIdWithCategory(Long id);

    List<Post> searchBy(Category category, ActiveStatus activeStatus, String title, String body);

    Page<Post> searchByPage(Category category, ActiveStatus activeStatus, String title, String body, Pageable pageable);

    Long getCountByCategory(Category category);
}
