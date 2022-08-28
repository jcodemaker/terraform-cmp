package com.crcloud.cloudros.exception;

import com.google.common.base.Throwables;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

/**
 *
 * @author xingcl
 * @since 2022-04-18
 */
@Slf4j
@RestControllerAdvice
public class ControllerExceptionHandler {

    @ResponseBody
    @ExceptionHandler(Exception.class)
    public ResponseEntity exceptionHandler(Exception e) {

        // 不是所有异常都需要打印完整堆栈，后续可以定义内部的Exception，便于判断
        if (e instanceof IllegalArgumentException ) {
            log.warn("[ControllerException] http request failed, message is {}.", e.getMessage());
        } else if (e instanceof HttpMessageNotReadableException || e instanceof MethodArgumentTypeMismatchException) {
            log.warn("[ControllerException] invalid http request params, exception is {}.", e.getMessage());
        } else if (e instanceof HttpRequestMethodNotSupportedException) {
            log.warn("[ControllerException] invalid http request method, exception is {}.", e.getMessage());
        } else {
            log.error("[ControllerException] http request failed.", e);
        }
        return new ResponseEntity<>(Throwables.getStackTraceAsString(e), HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
