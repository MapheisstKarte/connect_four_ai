import Board.Board
import Search.Search

fun main(args: Array<String>) {


    var board = Board()
    var search = Search()

    board.DrawBoard()
    while (!board.boardRepresentation.gameOver){

        // val time = System.currentTimeMillis()
        // val positions = search.miniMax(board, 9)
        // val searchTime = System.currentTimeMillis() - time
        // println("Positions found: ${positions}")
        // println("Search time: ${searchTime}")
        // println("nodes per second: ${(positions / searchTime) * 1000}")
        val move = readLine()!!.toInt()
        board.PushMove(move)
        board.DrawBoard() 
        println(board.boardRepresentation.winner.name)
    }




}