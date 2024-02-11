package com.bizplus.boardsaturday.global.error;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public enum ErrorCode {
    VALIDATION_FAIL(HttpStatus.BAD_REQUEST, "V-001", "유효성 체크에 실패했습니다."),
    NOT_SUPPORTED_METHOD(HttpStatus.BAD_REQUEST, "B-001", "지원하지 않는 HTTP 메소드 입니다.");


    ErrorCode(HttpStatus httpStatus, String errorCode, String message) {
        this.httpStatus = httpStatus;
        this.errorCode = errorCode;
        this.message = message;
    }

    private HttpStatus httpStatus;
    private String errorCode;
    private String message;

}
