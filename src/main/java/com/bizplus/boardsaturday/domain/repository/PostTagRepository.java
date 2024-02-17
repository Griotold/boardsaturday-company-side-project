package com.bizplus.boardsaturday.domain.repository;

import com.bizplus.boardsaturday.domain.entity.Post;
import com.bizplus.boardsaturday.domain.entity.PostTag;

public interface PostTagRepository {

    PostTag create(PostTag postTag);

    void deleteAll(Post post);
}
