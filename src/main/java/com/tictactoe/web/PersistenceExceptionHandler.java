package com.tictactoe.web;

import com.tictactoe.exceptions.GameDoesNotExist;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class PersistenceExceptionHandler {

    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(value = {GameDoesNotExist.class})
    public ErrorDetails handle(GameDoesNotExist exception) {
        return new ErrorDetails(exception.getMessage());
    }

    private static class ErrorDetails {
        private String message;

        private ErrorDetails(String message) {
            this.message = message;
        }

        public String getMessage() {
            return message;
        }
    }
}
