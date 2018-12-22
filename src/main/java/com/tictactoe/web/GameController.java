package com.tictactoe.web;

import com.tictactoe.domain.Game;
import com.tictactoe.service.GameService;
import com.tictactoe.web.request.GameRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
@RequestMapping(path = "/games")
public class GameController {

    private static final Logger logger = LoggerFactory.getLogger(GameController.class);

    private GameService games;

    public GameController(GameService games) {
        this.games = games;
    }

    @GetMapping(produces = APPLICATION_JSON_VALUE)
    public ResponseEntity getGame() {
        return ResponseEntity.ok(null);
    }

    @PostMapping(produces = APPLICATION_JSON_VALUE)
    public ResponseEntity saveGame(@RequestBody @Valid GameRequest game) {
        // TODO: validate ID, etc
        logger.debug("Received a request to save a game[id={}]", game.getId());

        Game newGame = new Game(game.getId());
        games.save(newGame);

        return new ResponseEntity(HttpStatus.CREATED);
    }
}
