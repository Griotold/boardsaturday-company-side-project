package com.bizplus.boardsaturday.application.request;

import com.bizplus.boardsaturday.support.StringToIntegerConverter;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotEmpty;
import java.util.List;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class OrderChangeCategoryRequest {
    @NotEmpty
    private List<String> orders;

    public OrderChangeCategoryRequest(List<String> orders) {
        this.orders = orders;
    }

    public List<Integer> getOrdersAsIntegers() {
        return StringToIntegerConverter.convertStringListToIntList(this.orders);
    }
}
