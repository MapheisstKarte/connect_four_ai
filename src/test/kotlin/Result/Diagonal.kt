package Result

import Board.Board
import Board.Player
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test

class Diagonal {

    @Test
    fun DiagonalCheck(){
        var board = Board()

        board.PushMove(0)
        board.PushMove(1)
        board.PushMove(1)
        board.PushMove(2)
        board.PushMove(2)
        board.PushMove(6)
        board.PushMove(2)
        board.PushMove(3)
        board.PushMove(3)
        board.PushMove(3)
        board.PushMove(3)

        Assertions.assertTrue(board.boardRepresentation.winner == Player.YELLOW)
    }
}