package com.tictactoe.service

import com.tictactoe.domain.Game
import com.tictactoe.repository.GameRepository
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.junit.MockitoJUnitRunner

import static org.mockito.Mockito.verify

@RunWith(MockitoJUnitRunner)
class GameServiceTest {

    @Mock
    private GameRepository repository

    private GameService service

    @Before
    void setup() {
        service = new GameService(repository)
    }

    @Test
    void "saves a game"() {
        //given
        def game = new Game(UUID.randomUUID().toString())

        //when
        service.save(game)

        //then
        verify(repository).save(game)
    }
}