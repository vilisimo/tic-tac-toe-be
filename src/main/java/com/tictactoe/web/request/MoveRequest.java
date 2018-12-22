package com.tictactoe.web.request;

import javax.validation.constraints.NotNull;
import java.util.Objects;

public class MoveRequest {

    @NotNull
    private int square;

    @NotNull
    private int x;

    @NotNull
    private int y;

    @NotNull
    private String player;

    public int getSquare() {
        return square;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public String getPlayer() {
        return player;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        MoveRequest that = (MoveRequest) o;
        return square == that.square &&
                x == that.x &&
                y == that.y &&
                Objects.equals(player, that.player);
    }

    @Override
    public int hashCode() {
        return Objects.hash(square, x, y, player);
    }
}
