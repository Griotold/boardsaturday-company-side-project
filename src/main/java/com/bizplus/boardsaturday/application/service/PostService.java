package com.bizplus.boardsaturday.application.service;

import com.bizplus.boardsaturday.application.component.post.PostCreator;
import com.bizplus.boardsaturday.application.component.post.PostQuery;
import com.bizplus.boardsaturday.application.request.post.CreatePostRequest;
import com.bizplus.boardsaturday.application.response.post.PostResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PostService {
    private final PostQuery postQuery;
    private final PostCreator postCreator;

    public List<PostResponse> findAll() {return postQuery.findAll();}

    public void create(CreatePostRequest request) {
        postCreator.create(request);
    }
}
