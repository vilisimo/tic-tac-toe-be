package com.tictactoe.domain

import org.junit.Test;

class PlayerTest {

    @Test
    void "creates a player from a string"() {
        //when
        def p1 = Player.from("X")
        def p2 = Player.from("O")

        //then
        assert p1 == Player.X
        assert p2 == Player.O
    }

    @Test(expected = IllegalArgumentException)
    void "does not accept invalid string"() {
        Player.from("A")
    }
}