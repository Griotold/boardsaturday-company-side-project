package com.bizplus.boardsaturday.global.error;

import com.bizplus.boardsaturday.global.error.ex.CustomValidationException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.Map;

@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {
    // todo 질문 1.
    /**
     * javax.validation.valid 또는 @Validated binding error가 발생할 경우
     * ResponseEntity<?>를 쓰고 싶다.
     */
    @ExceptionHandler(CustomValidationException.class)
    public ResponseEntity<ErrorResponse<Map<String, String>>> validationApiException(CustomValidationException e) {
        log.error("customValidationException", e);
        ErrorResponse<Map<String, String>> errorResponse
                = new ErrorResponse<>(ErrorCode.VALIDATION_FAIL.getErrorCode(), ErrorCode.VALIDATION_FAIL.getMessage(), e.getErrorMap());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(errorResponse);
    }

}
