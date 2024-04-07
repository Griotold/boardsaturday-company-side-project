package com.bizplus.boardsaturday.global.error.ex;

public class LoginFailException extends RuntimeException {

    public LoginFailException(String message, Throwable cause) {
        super(message, cause);
    }
}
