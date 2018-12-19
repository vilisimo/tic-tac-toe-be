package com.tictactoe.domain;

public enum Player {
    PLAYER_ONE("X"),
    PLAYER_TWO("O");

    private String representation;

    Player(String representation) {
        this.representation = representation;
    }


    @Override
    public String toString() {
        return representation;
    }
}
