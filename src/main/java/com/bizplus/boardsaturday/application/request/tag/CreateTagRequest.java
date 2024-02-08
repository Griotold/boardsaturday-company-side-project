package com.bizplus.boardsaturday.application.request.tag;

import com.bizplus.boardsaturday.domain.entity.Tag;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class CreateTagRequest {

    @NotBlank
    private String name;

    public CreateTagRequest(String name) {
        this.name = name;
    }

    public Tag toEntity() {
        return new Tag(name);
    }
}
