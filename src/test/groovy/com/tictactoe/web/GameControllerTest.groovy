package com.tictactoe.web

import com.tictactoe.domain.Game
import com.tictactoe.service.GameService
import com.tictactoe.web.request.GameRequest
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
    void "returns 201"() {
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
}

