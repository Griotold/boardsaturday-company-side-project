package com.bizplus.boardsaturday.application.component.tag;

import com.bizplus.boardsaturday.application.request.tag.CreateTagRequest;
import com.bizplus.boardsaturday.application.response.tag.TagResponse;
import com.bizplus.boardsaturday.domain.entity.Tag;
import com.bizplus.boardsaturday.domain.repository.TagRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Component
@RequiredArgsConstructor
@Transactional
public class TagCreator {
    private final TagRepository tagRepository;

    public TagResponse create(CreateTagRequest request) {

        Optional<Tag> findByNameTag = tagRepository.findByName(request.getName());

        Tag tag;

        if (findByNameTag.isPresent()) {
            tag = findByNameTag.get();
        } else {
            tag = request.toEntity();
            tag = tagRepository.create(tag);
        }

        return TagResponse.of(tag);
    }
}
