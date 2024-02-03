package com.bizplus.boardsaturday.application.request;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class CreateCategoryRequest {

    @NotBlank(message = "이름은 필수 입력값입니다.")
    @Size(max = 100, message = "100자 이내로 적어주세요.")
    private String name;

    @NotBlank(message = "설명은 필수 입력값입니다.")
    @Size(max = 255, message = "255자 이내로 적어주세요.")
    private String description;

    public CreateCategoryRequest(String name, String description) {
        this.name = name;
        this.description = description;
    }
}
