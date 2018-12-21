package com.tictactoe.web;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
@RequestMapping(path = "/games")
public class GameController {

    public GameController() {
    }

    @GetMapping(produces = APPLICATION_JSON_VALUE)
    public ResponseEntity getGame() {
        return ResponseEntity.ok(null);
    }
}
