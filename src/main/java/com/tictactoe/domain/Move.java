package com.tictactoe.domain;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Id;
import java.util.Objects;

@Entity
public class Move {

    @Id
    private int square;
    private int x;
    private int y;

    @Enumerated(EnumType.STRING)
    private Player player;

    private Move() { /* For (de-)serialization */ }

    public Move(int square, int x, int y, Player player) {
        this.square = square;
        this.x = x;
        this.y = y;
        this.player = player;
    }

    public int getSquare() {
        return square;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public Player getPlayer() {
        return player;
    }

    @Override
    public String toString() {
        return "Move{" +
                "square=" + square +
                ", x=" + x +
                ", y=" + y +
                ", player=" + player +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Move move = (Move) o;
        return square == move.square &&
                x == move.x &&
                y == move.y &&
                player == move.player;
    }

    @Override
    public int hashCode() {
        return Objects.hash(square, x, y, player);
    }
}
