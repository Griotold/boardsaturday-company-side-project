package com.bizplus.boardsaturday.global.error.ex;

import com.bizplus.boardsaturday.global.error.ErrorCode;

public class CategoryReferencedException extends BusinessException{
    public CategoryReferencedException(ErrorCode errorCode) {
        super(errorCode);
    }

    public CategoryReferencedException(ErrorCode errorCode, Throwable cause) {
        super(errorCode, cause);
    }

    public CategoryReferencedException(ErrorCode errorCode, String message) {
        super(errorCode, message);
    }
}
