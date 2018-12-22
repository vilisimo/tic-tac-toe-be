package com.tictactoe.domain;

import java.util.Arrays;

public enum Player {
    X, O;

    public static Player from(String string) {
        return Arrays.stream(Player.values())
                .filter(player -> player.toString().equals(string))
                .findFirst()
                // TODO: return 400 status code in case this is thrown
                .orElseThrow(() -> new IllegalArgumentException(string + " is not a valid player"));
    }
}
