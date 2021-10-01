package Search

import Board.Board

class Search {

    fun miniMax(board: Board, depth: Int): Int{

        if (depth == 0 || board.boardRepresentation.gameOver){
            return 1
        }

        var numPositions = 0

        board.boardRepresentation.legalMoves.toList().forEach {
            board.PushMove(it)
            numPositions += miniMax(board, depth -1)
            board.UndoMove()
        }

        return numPositions
    }
}