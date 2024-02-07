package com.bizplus.boardsaturday.application.service;

import com.bizplus.boardsaturday.application.component.post.PostQuery;
import com.bizplus.boardsaturday.application.response.post.PostResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PostService {
    private final PostQuery postQuery;

    public List<PostResponse> findAll() {return postQuery.findAll();}
}
