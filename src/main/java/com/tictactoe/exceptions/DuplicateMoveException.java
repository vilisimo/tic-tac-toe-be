package com.tictactoe.exceptions;

public class DuplicateMoveException extends RuntimeException {
    public DuplicateMoveException(String message) {
        super(message);
    }
}
