package com.bizplus.boardsaturday.application.request;

import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotEmpty;
import java.util.ArrayList;
import java.util.List;

@Getter
@NoArgsConstructor
public class OrderChangeCategoryRequest {

    @NotEmpty
    private List<Long> ids;

}
