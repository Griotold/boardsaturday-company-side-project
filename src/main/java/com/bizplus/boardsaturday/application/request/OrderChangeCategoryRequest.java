package com.bizplus.boardsaturday.application.request;

import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotEmpty;
import java.util.List;

@Getter
@NoArgsConstructor
public class OrderChangeCategoryRequest {
    @NotEmpty
    private List<String> orders;

    public OrderChangeCategoryRequest(List<String> orders) {
        this.orders = orders;
    }

}
