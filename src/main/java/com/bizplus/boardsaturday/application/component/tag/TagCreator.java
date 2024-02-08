package com.bizplus.boardsaturday.application.component.tag;

import com.bizplus.boardsaturday.application.request.tag.CreateTagRequest;
import com.bizplus.boardsaturday.application.response.tag.TagResponse;
import com.bizplus.boardsaturday.domain.entity.Tag;
import com.bizplus.boardsaturday.domain.repository.TagRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
@RequiredArgsConstructor
@Transactional
public class TagCreator {
    private final TagRepository tagRepository;

    public TagResponse create(CreateTagRequest request) {
        Tag tag = request.toEntity();

        Tag tagPS = tagRepository.create(tag);

        return TagResponse.of(tagPS);
    }
}
