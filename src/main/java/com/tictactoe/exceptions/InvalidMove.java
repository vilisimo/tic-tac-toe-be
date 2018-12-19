package com.tictactoe.exceptions;

public class InvalidMove extends RuntimeException {
    public InvalidMove(String reason) {
        super(reason);
    }
}
