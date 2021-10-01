import Board.Board

fun main(args: Array<String>) {


    var board = Board()
    board.DrawBoard()
    while (!board.boardRepresentation.gameOver){
        val move = readLine()!!.toInt()
        board.PushMove(move)
        board.DrawBoard()
        println(board.boardRepresentation.winner.name)
    }




}