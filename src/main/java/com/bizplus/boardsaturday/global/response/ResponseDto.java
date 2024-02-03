package com.bizplus.boardsaturday.global.response;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public class ResponseDto<T> {
    private final String code;
    private final String message;
    private final T data;
}
