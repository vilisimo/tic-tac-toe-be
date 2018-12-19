package com.tictactoe.domain;

import com.tictactoe.exceptions.InvalidMove;

import java.util.Objects;

public class Move {

    private final Player player;
    private final int square;

    /**
     * Since squares are counted from 0, only [0-8] squares are valid
     *
     * @throws InvalidMove on invalid move
     */
    public Move(Player player, int square) {
        if (square < 0 || square > 8) {
            throw new InvalidMove("Squares can only be inside [0-8] range");
        }

        this.player = player;
        this.square = square;
    }

    public Player getPlayer() {
        return player;
    }

    public int getSquare() {
        return square;
    }

    @Override
    public String toString() {
        return "Move{" +
                "player=" + player +
                ", square=" + square +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Move move = (Move) o;
        return square == move.square &&
                player == move.player;
    }

    @Override
    public int hashCode() {
        return Objects.hash(player, square);
    }
}
