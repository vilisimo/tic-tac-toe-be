package com.tictactoe.service

import com.tictactoe.domain.Game
import com.tictactoe.domain.Move
import com.tictactoe.domain.Player
import com.tictactoe.exceptions.GameDoesNotExist
import com.tictactoe.repository.GameRepository
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.ArgumentCaptor
import org.mockito.Mock
import org.mockito.junit.MockitoJUnitRunner

import static org.mockito.Mockito.mock
import static org.mockito.Mockito.verify
import static org.mockito.Mockito.when

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

    @Test
    void "queries db for a game"() {
        //given
        def id = UUID.randomUUID().toString()

        //when
        service.find(id)

        //then
        verify(repository).findById(id)
    }

    @Test
    void "returns found game"() {
        //given
        def id = UUID.randomUUID().toString()
        when(repository.findById(id)).thenReturn(Optional.of(new Game(id)))

        //when
        def result = service.find(id)

        //then
        assert result
        assert result.get() == new Game(id)
    }

    @Test
    void "saves a move"() {
        //given
        def id = UUID.randomUUID().toString()
        def move = new Move(0, 1, 1, Player.X)

        when(repository.findById(id))
                .thenReturn(Optional.of(new Game(id)))

        //when
        service.makeMove(id, move)

        //then
        def captor = ArgumentCaptor.forClass(Game)
        verify(repository).save(captor.capture())

        def result = captor.getValue()
        assert result.moves.size() == 1
        assert result.moves.contains(move)
    }

    @Test(expected = GameDoesNotExist)
    void "informs that the game does not exist"() {
        //given
        def id = UUID.randomUUID().toString()

        when(repository.findById(id)).thenReturn(Optional.empty())

        //when
        service.makeMove(id, mock(Move))
    }
}