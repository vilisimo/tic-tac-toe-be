package com.tictactoe.domain;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OrderBy;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity
public class Game {

    @Id
    private String id;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "game_id")
    @OrderBy("id ASC")
    private List<Move> moves = new ArrayList<>();

    private Game() { /* For (de-)serialization */ }

    public Game(String id) {
        this.id = id;
    }

    public String getId() {
        return id;
    }

    public List<Move> getMoves() {
        return moves;
    }

    @Override
    public String toString() {
        return "Game{" +
                "id='" + id + '\'' +
                ", moves=" + moves +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Game game = (Game) o;
        return Objects.equals(id, game.id) &&
                Objects.equals(moves, game.moves);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, moves);
    }
}
