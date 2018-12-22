package com.tictactoe.exceptions;

public class GameDoesNotExist extends RuntimeException {
    public GameDoesNotExist(String message) {
        super(message);
    }
}
