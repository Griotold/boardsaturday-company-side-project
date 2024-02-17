package com.bizplus.boardsaturday.global.error;

import com.bizplus.boardsaturday.global.error.ex.BusinessException;
import com.bizplus.boardsaturday.global.error.ex.CustomValidationException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;


import javax.persistence.EntityNotFoundException;
import java.util.Map;

@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {
    /**
     * javax.validation.valid 또는 @Validated binding error가 발생할 경우
     */
    @ExceptionHandler(CustomValidationException.class)
    public ResponseEntity<ErrorResponse<Map<String, String>>> validationApiException(CustomValidationException e) {
        log.error("customValidationException", e);
        ErrorResponse<Map<String, String>> errorResponse
                = new ErrorResponse<>(ErrorCode.VALIDATION_FAIL.getErrorCode(), ErrorCode.VALIDATION_FAIL.getMessage(), e.getErrorMap());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(errorResponse);
    }
    /**
     * 타입 미스매치
     */
    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
    protected ResponseEntity<ErrorResponse<?>> handleMethodArgumentTypeMismatchException(MethodArgumentTypeMismatchException e) {
        log.error("handleMethodArgumentTypeMismatchException", e);
        ErrorResponse<Object> errorResponse
                = new ErrorResponse<> (ErrorCode.TYPE_MISMATCH.getErrorCode(), ErrorCode.TYPE_MISMATCH.getMessage(), null);
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorResponse);
    }

    /**
     * 지원하지 않은 HTTP method 호출 할 경우 발생
     */
    @ExceptionHandler(HttpRequestMethodNotSupportedException.class)
    protected ResponseEntity<ErrorResponse<?>>  handleHttpRequestMethodNotSupportedException(HttpRequestMethodNotSupportedException e) {
        log.error("handleHttpRequestMethodNotSupportedException", e);
        ErrorResponse<Object> errorResponse
                = new ErrorResponse<>(ErrorCode.NOT_SUPPORTED_METHOD.getErrorCode(), ErrorCode.NOT_SUPPORTED_METHOD.getMessage(), null);
        return ResponseEntity.status(HttpStatus.METHOD_NOT_ALLOWED).body(errorResponse);
    }

    /**
     * 비즈니스 로직 실행 중 오류 발생
     */
    @ExceptionHandler(value = { BusinessException.class })
    protected ResponseEntity<ErrorResponse<?>> handleConflict(BusinessException e) {
        log.error("BusinessException", e);
        ErrorResponse<Object> errorResponse = new ErrorResponse<>(e.getErrorCode().getErrorCode(), e.getErrorCode().getMessage(), null);
        return ResponseEntity.status(e.getErrorCode().getHttpStatus())
                .body(errorResponse);
    }

    /**
     * EntityNotFoundException
     */
    @ExceptionHandler(EntityNotFoundException.class)
    protected ResponseEntity<ErrorResponse<?>> handleEntityNotFoundException(EntityNotFoundException e) {
        log.error("handleEntityNotFoundException", e);
        ErrorResponse<EntityNotFoundException> errorResponse
                = new ErrorResponse<>(ErrorCode.ENTITY_NOT_FOUND.getErrorCode(), e.getMessage(), null);
        return ResponseEntity.status(ErrorCode.ENTITY_NOT_FOUND.getHttpStatus())
                .body(errorResponse);
    }

    /**
     * 나머지 예외 발생
     */
    @ExceptionHandler(Exception.class)
    protected ResponseEntity<ErrorResponse<?>> handleException(Exception e) {
        log.error("Exception", e);
        ErrorResponse<Object> errorResponse = new ErrorResponse<>(HttpStatus.INTERNAL_SERVER_ERROR.toString(),
                e.getMessage(), null);
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorResponse);
    }

}
