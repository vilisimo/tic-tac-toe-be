package com.tictactoe.web;

import com.tictactoe.domain.Game;
import com.tictactoe.domain.Move;
import com.tictactoe.domain.Player;
import com.tictactoe.service.GameService;
import com.tictactoe.web.request.GameRequest;
import com.tictactoe.web.request.MoveRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
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

    @PostMapping
    public ResponseEntity saveGame(@RequestBody @Valid GameRequest game) {
        logger.debug("Received a request to save a game[id={}]", game.getId());

        Game newGame = new Game(game.getId());
        games.save(newGame);

        return new ResponseEntity(HttpStatus.CREATED);
    }

    @GetMapping(produces = APPLICATION_JSON_VALUE, path = "{id}")
    public ResponseEntity<Game> getGame(@PathVariable String id) {
        logger.debug("Retrieving a game[id={}]", id);
        return games.find(id)
                .map(game -> new ResponseEntity<>(game, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PostMapping(path = "{id}/moves")
    public ResponseEntity makeMove(@PathVariable String id, @RequestBody @Valid MoveRequest move) {
        logger.debug("Received a move request for a game[id={}]", id);

        games.makeMove(id, new Move(move.getSquare(), move.getX(), move.getY(), Player.from(move.getPlayer())));

        return new ResponseEntity(HttpStatus.CREATED);
    }
}
