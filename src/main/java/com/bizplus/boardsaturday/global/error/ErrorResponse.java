package com.bizplus.boardsaturday.global.error;

import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public class ErrorResponse<T> {

    private final String errorCode;
    private final String errorMessage;
    private final T data;

}
