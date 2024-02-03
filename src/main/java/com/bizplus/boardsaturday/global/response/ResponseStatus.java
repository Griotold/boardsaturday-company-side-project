package com.bizplus.boardsaturday.global.response;

import lombok.Getter;

@Getter
public enum ResponseStatus {
    GOOD("R-001", "Good"), BAD("R-002", "Bad");

    private final String code;
    private final String message;

    ResponseStatus(String code, String message) {
        this.code = code;
        this.message = message;
    }
}
