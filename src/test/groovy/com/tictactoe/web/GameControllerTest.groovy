package com.tictactoe.web

import com.tictactoe.domain.Game
import com.tictactoe.domain.Move
import com.tictactoe.domain.Player
import com.tictactoe.service.GameService
import com.tictactoe.web.request.GameRequest
import com.tictactoe.web.request.MoveRequest
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.junit.MockitoJUnitRunner
import org.springframework.http.HttpStatus

import static org.mockito.ArgumentMatchers.any
import static org.mockito.Mockito.verify

@RunWith(MockitoJUnitRunner)
class GameControllerTest {

    @Mock
    private GameService games

    private GameController controller

    @Before
    void setup() {
        controller = new GameController(games)
    }

    @Test
    void "returns 201 upon saving a game"() {
        //when
        def response = controller.saveGame(new GameRequest(id: UUID.randomUUID().toString()))

        //then
        assert response
        assert response.statusCode == HttpStatus.CREATED
    }

    @Test
    void "delegates saving to downstream service"() {
        //when
        controller.saveGame(new GameRequest(id: UUID.randomUUID().toString()))

        //then
        verify(games).save(any(Game))
    }

    @Test
    void "delegates retrieving a game to downstream service"() {
        //given
        def id = UUID.randomUUID().toString()

        //when
        controller.getGame(id)

        //then
        verify(games).find(id)
    }

    @Test
    void "returns 201 upon saving a move"() {
        //given
        def moveRequest = new MoveRequest(square: 0, x: 1, y: 1, player: "X")

        //when
        def response = controller.makeMove(UUID.randomUUID().toString(), moveRequest)

        //then
        assert response
        assert response.statusCode == HttpStatus.CREATED
    }

    @Test
    void "saves a move"() {
        //given
        def id = UUID.randomUUID().toString()
        def moveRequest = new MoveRequest(square: 0, x: 1, y: 1, player: "O")

        //when
        controller.makeMove(id, moveRequest)

        //then
        verify(games).makeMove(id, new Move(0, 1, 1, Player.O))
    }
}

