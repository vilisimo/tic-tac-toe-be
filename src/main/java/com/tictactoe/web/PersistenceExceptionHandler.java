package com.tictactoe.web;

import com.tictactoe.exceptions.DuplicateMoveException;
import com.tictactoe.exceptions.FinishedGameException;
import com.tictactoe.exceptions.GameDoesNotExist;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class PersistenceExceptionHandler {

    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(GameDoesNotExist.class)
    public ErrorDetails handleMissingGame(GameDoesNotExist exception) {
        return new ErrorDetails(exception.getMessage());
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(DuplicateMoveException.class)
    public ErrorDetails handleDuplicateMove(DuplicateMoveException exception) {
        return new ErrorDetails(exception.getMessage());
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(FinishedGameException.class)
    public ErrorDetails handleFinishedGame(FinishedGameException exception) {
        return new ErrorDetails(exception.getMessage());
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(IllegalArgumentException.class)
    public ErrorDetails handleFinishedGame(IllegalArgumentException exception) {
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
