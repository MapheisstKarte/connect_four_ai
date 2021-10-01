package Result

import Board.Board
import Board.Player
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test

class Vertical {

    @Test
    fun VerticalCheck(){

        var board = Board()

        board.PushMove(0)
        board.PushMove(1)
        board.PushMove(0)
        board.PushMove(1)
        board.PushMove(0)
        board.PushMove(1)
        board.PushMove(0)

        Assertions.assertTrue(board.boardRepresentation.winner == Player.YELLOW)
    }
}