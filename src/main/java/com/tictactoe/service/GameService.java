package com.tictactoe.service;

import com.tictactoe.domain.Game;
import com.tictactoe.domain.Move;
import com.tictactoe.exceptions.DuplicateMoveException;
import com.tictactoe.exceptions.FinishedGameException;
import com.tictactoe.exceptions.GameDoesNotExist;
import com.tictactoe.repository.GameRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;
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
        logger.debug("Retrieving a game[id={}] to save a move", id);
        Game game = find(id).orElseThrow(() -> new GameDoesNotExist("Game[id=" + id + "] could not be found"));
        List<Move> moves = game.getMoves();

        checkWon(game);
        checkMarked(move, moves);

        moves.add(move);
        save(game);
        logger.debug("Successfully saved a move[{}] to a game[id={}]", move, id);
    }

    private void checkWon(Game game) {
        if (game.isFinished() || game.getMoves().size() >= 9) {
            logger.error("An attempt to add a move to an ended game[id={}] was made", game);
            throw new FinishedGameException("Game[id=" + game.getId() + "] has already ended");
        }
    }

    private void checkMarked(Move move, List<Move> moves) {
        boolean duplicate = moves.stream()
                .anyMatch(m -> m.getSquare() == move.getSquare());

        if (duplicate) {
            logger.error("An attempt to mark an already marked square was made");
            throw new DuplicateMoveException("A move at square " + move.getSquare() + " already exists");
        }
    }
}
