package org.software.open.source.spring.template.exception;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

import org.software.open.source.common.apis.models.responses.Response;
import org.software.open.source.spring.template.apis.models.responses.ErrorDetails;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.resource.NoResourceFoundException;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(ApplicationException.class)
    public ResponseEntity<Response<ErrorDetails>> handleApplicationException(ApplicationException ex, WebRequest request) {
        log.error("ApplicationException occurred: {}", ex.getMessage(), ex);
        HttpStatus status = ex.getHttpStatus() != null ? ex.getHttpStatus() : HttpStatus.INTERNAL_SERVER_ERROR;
        return buildErrorResponse(status, ex.getMessage(), request.getDescription(false));
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Response<Map<String, String>>> handleMethodArgumentNotValid(MethodArgumentNotValidException ex) {
        log.error("MethodArgumentNotValidException occurred: {}", ex.getMessage(), ex);

        Map<String, String> errors = new HashMap<>();
        ex.getBindingResult().getAllErrors().forEach(error -> {
            String fieldName = ((FieldError) error).getField();
            String errorMessage = error.getDefaultMessage();
            errors.put(fieldName, errorMessage);
        });

        Response<Map<String, String>> response = new Response<>();
        response.setException(true);
        response.setStatusCode(HttpStatus.BAD_REQUEST.value());
        response.setData(errors);

        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(NoResourceFoundException.class)
    public ResponseEntity<Response<ErrorDetails>> handleNoResourceFoundException(NoResourceFoundException ex, WebRequest request) {
        log.error("NoResourceFoundException occurred: {}", ex.getMessage(), ex);
        return buildErrorResponse(HttpStatus.NOT_FOUND, ex.getMessage(), request.getDescription(false));
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<Response<ErrorDetails>> handleGlobalException(Exception ex, WebRequest request) {
        log.error("Unhandled exception occurred", ex);
        return buildErrorResponse(HttpStatus.INTERNAL_SERVER_ERROR, ex.getMessage(), request.getDescription(false));
    }

    private ResponseEntity<Response<ErrorDetails>> buildErrorResponse(HttpStatus status, String message, String path) {
        Response<ErrorDetails> response = new Response<>();
        response.setException(true);
        response.setStatusCode(status.value());
        response.setData(new ErrorDetails(LocalDateTime.now(), message, path));
        return new ResponseEntity<>(response, status);
    }
}