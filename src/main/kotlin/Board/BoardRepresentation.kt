package Board

data class BoardRepresentation(
    val fields: Array<MutableList<Int>> = Array(7) { mutableListOf(0, 0, 0, 0, 0, 0)},
    var legalMoves: MutableList<Int> = mutableListOf(0, 1 , 2, 3, 4, 5, 6),
    var gameOver: Boolean = false,
    var winner: Player = Player.NONE,
    var playerToMove: Player = Player.YELLOW,
    var moveStack: MutableList<Int> = mutableListOf(),

    )
