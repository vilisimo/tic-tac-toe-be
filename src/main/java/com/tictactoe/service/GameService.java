package com.tictactoe.service;

import com.tictactoe.domain.Game;
import com.tictactoe.domain.Move;
import com.tictactoe.exceptions.GameDoesNotExist;
import com.tictactoe.repository.GameRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class GameService {

    private static final Logger logger = LoggerFactory.getLogger(GameService.class);

    private final GameRepository games;

    public GameService(GameRepository games) {
        this.games = games;
    }

    public void save(Game game) {
        logger.debug("Saving a game[id={}]", game.getId());
        games.save(game);
    }

    public Optional<Game> find(String id) {
        return games.findById(id);
    }

    public void makeMove(String id, Move move) {
        // TODO: verify if:
        //  1) a square hasn't been marked
        //  2) there aren't more than 9 moves already
        logger.debug("Retrieving a game[id={}] to save a move", id);
        Game game = find(id).orElseThrow(() -> new GameDoesNotExist("Game[id=" + id + "] could not be found"));
        game.getMoves().add(move);
        save(game);
        logger.debug("Successfully saved a move[{}] to a game[id={}]", move, id);
    }
}
