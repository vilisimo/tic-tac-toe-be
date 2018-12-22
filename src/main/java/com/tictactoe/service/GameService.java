package com.tictactoe.service;

import com.tictactoe.domain.Game;
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
}
