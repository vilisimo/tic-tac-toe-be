package com.tictactoe.web

import org.junit.Test
import org.springframework.http.HttpStatus

class GameControllerTest {

    private def controller = new GameController()

    @Test
    void "returns 200"() {
        //when
        def response = controller.game

        //then
        response.statusCode == HttpStatus.OK
    }
}
