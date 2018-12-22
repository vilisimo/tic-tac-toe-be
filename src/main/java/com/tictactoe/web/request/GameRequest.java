package com.tictactoe.web.request;

import javax.validation.constraints.NotNull;

public class GameRequest {

    @NotNull
    private String id;

    public String getId() {
        return id;
    }
}
