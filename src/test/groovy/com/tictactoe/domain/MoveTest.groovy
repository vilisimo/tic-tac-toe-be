package com.tictactoe.domain

import com.tictactoe.exceptions.InvalidMove
import org.junit.Test

import static com.tictactoe.domain.Player.PLAYER_ONE

class MoveTest {

    @Test
    void "allows to create a [0-8] square"() {
        //when
        def move = new Move(PLAYER_ONE, 0)

        //then
        assert move
    }

    @Test(expected = InvalidMove)
    void "does not allow to create a < 0 square"() {
        new Move(PLAYER_ONE, -1)
    }

    @Test(expected = InvalidMove)
    void "does not allow to create a > 8 square"() {
        new Move(PLAYER_ONE, 9)
    }
}
