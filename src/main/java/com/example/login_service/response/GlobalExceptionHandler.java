package com.example.login_service.response;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.bind.support.WebExchangeBindException;
import org.springframework.web.server.ResponseStatusException;
import reactor.core.publisher.Mono;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(Exception.class)
    public ResponseEntity<APIResponse<Object>> handleException(Exception e) {
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(new APIResponse<>(
                        "Something went wrong",
                        HttpStatus.INTERNAL_SERVER_ERROR.value(),
                        null
                ));
    }

    @ExceptionHandler(ResponseStatusException.class)
    public Mono<ResponseEntity<APIResponse<Object>>> handleResponseStatusException(ResponseStatusException e) {
        return Mono.just(
                ResponseEntity.status(e.getStatusCode())
                        .body(new APIResponse<>(
                                e.getReason(),
                                e.getStatusCode().value(),
                                null
                        ))
        );
    }

    @ExceptionHandler(WebExchangeBindException.class)
    public Mono<ResponseEntity<APIResponse<Object>>> handleValidationException(
            WebExchangeBindException ex) {

        String errorMessage = ex.getBindingResult()
                .getFieldErrors()
                .stream()
                .map(error -> error.getField() + ": " + error.getDefaultMessage())
                .findFirst()
                .orElse("Validation failed");

        return Mono.just(
                ResponseEntity.status(HttpStatus.BAD_REQUEST)
                        .body(new APIResponse<>(
                                errorMessage,
                                HttpStatus.BAD_REQUEST.value(),
                                null
                        ))
        );
    }

}
