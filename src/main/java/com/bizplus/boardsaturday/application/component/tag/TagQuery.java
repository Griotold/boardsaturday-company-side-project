package com.bizplus.boardsaturday.application.component.tag;

import com.bizplus.boardsaturday.application.response.tag.TagResponse;
import com.bizplus.boardsaturday.domain.entity.Tag;
import com.bizplus.boardsaturday.domain.repository.TagRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;

@Component
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class TagQuery {

    private final TagRepository tagRepository;

    public TagResponse findByName(String name) {
        Tag tag = tagRepository.findByName(name)
                .orElseThrow(() -> new EntityNotFoundException("태그를 찾을 수 없습니다."));

        return TagResponse.of(tag);
    }
}
