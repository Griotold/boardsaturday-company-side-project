package com.bizplus.boardsaturday.application.service;

import com.bizplus.boardsaturday.application.component.post.PostCreator;
import com.bizplus.boardsaturday.application.component.post.PostDeleter;
import com.bizplus.boardsaturday.application.component.post.PostQuery;
import com.bizplus.boardsaturday.application.component.post.PostUpdater;
import com.bizplus.boardsaturday.application.request.post.CreatePostRequest;
import com.bizplus.boardsaturday.application.request.post.UpdatePostRequest;
import com.bizplus.boardsaturday.application.response.post.PostResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PostService {
    private final PostQuery postQuery;
    private final PostCreator postCreator;
    private final PostUpdater postUpdater;
    private final PostDeleter postDeleter;

    public List<PostResponse> findAll() {return postQuery.findAll();}

    public void create(CreatePostRequest request) {
        postCreator.create(request);
    }

    public void changeStatusOn(Long id) {
        postUpdater.changeStatusOn(id);
    }

    public void changeStatusOff(Long id) {
        postUpdater.changeStatusOff(id);
    }

    public void delete(Long id) {
        postDeleter.delete(id);
    }

    public void update(UpdatePostRequest request, Long id) {
        postUpdater.update(request, id);
    }
}
