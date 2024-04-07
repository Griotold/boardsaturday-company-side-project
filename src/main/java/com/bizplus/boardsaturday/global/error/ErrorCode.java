package com.bizplus.boardsaturday.global.error;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public enum ErrorCode {
    VALIDATION_FAIL(HttpStatus.BAD_REQUEST, "V-001", "유효성 체크에 실패했습니다."),

    NOT_SUPPORTED_METHOD(HttpStatus.METHOD_NOT_ALLOWED, "B-001", "지원하지 않는 HTTP 메소드 입니다."),
    TYPE_MISMATCH(HttpStatus.BAD_REQUEST, "B-002", "타입 미스매치"),
    ENTITY_NOT_FOUND(HttpStatus.BAD_REQUEST, "B-003", "엔티티를 찾을 수 없습니다."),
    CONFLICT(HttpStatus.CONFLICT, "B-004", "시스템과 충돌이 발생하여 처리할 수 없는 요청입니다."),
    DUPLICATED_EMAIL(HttpStatus.BAD_REQUEST, "B-005", "중복된 이메일 입니다."),
    PASSWORD_NOT_CONFIRMED(HttpStatus.BAD_REQUEST, "B-006", "비밀번호와 비밀번호 확인이 다릅니다."),
    BAD_CREDENTIAL(HttpStatus.BAD_REQUEST, "B-007", "아이디와 비밀번호를 확인해주세요.");


    ErrorCode(HttpStatus httpStatus, String errorCode, String message) {
        this.httpStatus = httpStatus;
        this.errorCode = errorCode;
        this.message = message;
    }

    private final HttpStatus httpStatus;
    private final String errorCode;
    private final String message;

}
