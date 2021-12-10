package com.origin.financial.config;

import com.origin.financial.config.exception.ApiError;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import static org.springframework.http.HttpStatus.BAD_REQUEST;

@ControllerAdvice
public class CustomResponseEntityExceptionHandler {

    @ExceptionHandler
    public ResponseEntity<ApiError> handle(MethodArgumentNotValidException exception) {
        ApiError apiError = new ApiError(exception.getFieldErrors().get(0).getDefaultMessage());
        return new ResponseEntity<ApiError>(apiError, null, BAD_REQUEST);
    }

    @ExceptionHandler
    public ResponseEntity<ApiError> handle(HttpMessageNotReadableException exception) {
        ApiError apiError = new ApiError("Invalid value");
        return new ResponseEntity<ApiError>(apiError, null, BAD_REQUEST);
    }
}