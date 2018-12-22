package com.tictactoe.exceptions;

// TODO: handle to throw 404
public class GameDoesNotExist extends RuntimeException {
    public GameDoesNotExist(String message) {
        super(message);
    }
}
