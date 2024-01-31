package com.bizplus.boardsaturday.global.error.ex;

import lombok.Getter;

import java.util.Map;

@Getter
public class CustomValidationException extends RuntimeException{

    private final Map<String, String> errorMap;

    public CustomValidationException(String message, Throwable cause, Map<String, String> errorMap) {
        super(message, cause);
        this.errorMap = errorMap;
    }
}
