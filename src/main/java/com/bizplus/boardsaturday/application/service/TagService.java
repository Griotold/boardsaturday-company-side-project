package com.bizplus.boardsaturday.application.service;

import com.bizplus.boardsaturday.application.component.tag.TagCreator;
import com.bizplus.boardsaturday.application.request.tag.CreateTagRequest;
import com.bizplus.boardsaturday.application.response.tag.TagResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class TagService {
    private final TagCreator tagCreator;

    public TagResponse create(CreateTagRequest request) {
        return tagCreator.create(request);
    }
}
