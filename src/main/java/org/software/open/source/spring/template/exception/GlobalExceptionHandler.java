package org.software.open.source.spring.template.exception;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

import org.software.open.source.common.apis.models.responses.Response;
import org.software.open.source.spring.template.domain.models.responses.ErrorDetails;
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
        Response<ErrorDetails> response = new Response<>();
        response.setException(true, "ApplicationException");
        response.setData(new ErrorDetails(LocalDateTime.now(), ex.getMessage(), request.getDescription(false)));
        if (Objects.isNull(ex.getHttpStatus())) {
            log.error("Error occurred: {}", ex.getMessage());
            response.setStatusCode(HttpStatus.INTERNAL_SERVER_ERROR.value());
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        response.setStatusCode(HttpStatus.INTERNAL_SERVER_ERROR.value());
        log.error("Error occurred: {}", ex.getMessage());
        return new ResponseEntity<>(response, ex.getHttpStatus());
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    protected ResponseEntity<Response<Map<String, String>>> handleMethodArgumentNotValid(MethodArgumentNotValidException ex) {
        Response<Map<String, String>> response = new Response<>();
        response.setException(true, "MethodArgumentNotValidException");
        response.setStatusCode(HttpStatus.BAD_REQUEST.value());
        Map<String, String> errors = new HashMap<>();
        ex.getBindingResult().getAllErrors().forEach(error -> {
            String fieldName = ((FieldError) error).getField();
            String errorMessage = error.getDefaultMessage();
            errors.put(fieldName, errorMessage);
        });
        response.setData(errors);
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST); // 400
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<Response<ErrorDetails>> handleGlobalException(Exception ex, WebRequest request) {
        Response<ErrorDetails> response = new Response<>();
        response.setException(true, "Exception");
        response.setStatusCode(HttpStatus.INTERNAL_SERVER_ERROR.value());
        response.setData(new ErrorDetails(LocalDateTime.now(), ex.getMessage(), request.getDescription(false)));
        log.error("Unhandled exception occurred", ex);
        return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR); // 500
    }

    @ExceptionHandler(NoResourceFoundException.class)
    public ResponseEntity<Response<ErrorDetails>> handleNoResourceFoundException(NoResourceFoundException ex, WebRequest request) {
        Response<ErrorDetails> response = new Response<>();
        response.setException(true, "NoResourceFoundException");
        response.setStatusCode(HttpStatus.NOT_FOUND.value());
        response.setData(new ErrorDetails(LocalDateTime.now(), ex.getMessage(), request.getDescription(false)));
        log.error("No static resource", ex);
        return new ResponseEntity<>(response, HttpStatus.NOT_FOUND); // 500
    }

}
