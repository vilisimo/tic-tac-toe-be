package com.tictactoe.exceptions;

public class FinishedGameException extends RuntimeException {
    public FinishedGameException(String message) {
        super(message);
    }
}
