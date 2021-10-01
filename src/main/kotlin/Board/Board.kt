package Board

class Board {

    var boardRepresentation: BoardRepresentation = BoardRepresentation()


    fun PushMove(column: Int){

        if (boardRepresentation.gameOver){
            throw Exception("Illegal Move: ${column} - The Game Is Over")
        }

        if (column !in boardRepresentation.legalMoves){
            throw Exception("Illegal Move: ${column} - The Column Is Full")
        }

        for (row in 0..5){

            if (boardRepresentation.fields[column][row] == 0){

                when (boardRepresentation.playerToMove) {
                    Player.YELLOW -> {
                        boardRepresentation.fields[column][row] = Field.YELLOW.fieldValue
                        break
                    }
                    Player.RED -> {
                        boardRepresentation.fields[column][row] = Field.RED.fieldValue
                        break
                    }

                    else -> throw Exception("Error - No Player To Move")
                }
            }
        }

                if (boardRepresentation.fields[column].all { it > 0 }){
                boardRepresentation.legalMoves.remove(column)
            }

        boardRepresentation.playerToMove = when (boardRepresentation.playerToMove){
            Player.YELLOW -> Player.RED
            Player.RED -> Player.YELLOW

            else -> throw Exception("Error - No Player To Move")
        }

        boardRepresentation.moveStack.add(column)
        CheckResult(column)

    }

    fun UndoMove(){

        boardRepresentation.gameOver = false
        boardRepresentation.winner = Player.NONE

        for (row in 0..5){
            if (boardRepresentation.fields[boardRepresentation.moveStack.last()][row] > 0){
                boardRepresentation.fields[boardRepresentation.moveStack.last()][row] = Field.NONE.fieldValue
                break
            }
        }

        if (boardRepresentation.moveStack.last() !in boardRepresentation.legalMoves){
            boardRepresentation.legalMoves.add(boardRepresentation.moveStack.last())
        }

        boardRepresentation.moveStack.remove(boardRepresentation.moveStack.last())

        boardRepresentation.playerToMove = when (boardRepresentation.playerToMove){
            Player.YELLOW -> Player.RED
            Player.RED -> Player.YELLOW

            else -> throw Exception("Error - No Player To Move")
        }
    }

    fun CheckResult(column: Int) {

        if (boardRepresentation.legalMoves.isEmpty()){
            boardRepresentation.gameOver = true
            boardRepresentation.winner = Player.NONE
            return
        }

        // vertical check

        var fieldsInARow = 0

        val relevantFieldType = when (boardRepresentation.playerToMove) {
            Player.RED -> Field.YELLOW.fieldValue
            Player.YELLOW -> Field.RED.fieldValue
            else -> Field.NONE.fieldValue
        }

        for (row in 0..5) {

            if (boardRepresentation.fields[column][row] == relevantFieldType) {

                fieldsInARow++

                if (fieldsInARow == 4) {
                    boardRepresentation.gameOver = true
                    boardRepresentation.winner = when (boardRepresentation.playerToMove) {
                        Player.YELLOW -> Player.RED
                        Player.RED -> Player.YELLOW
                        else -> Player.NONE
                    }
                    return
                }

            } else {

                fieldsInARow = 0
            }
        }

        if (fieldsInARow >= 4) {
            boardRepresentation.gameOver = true
            boardRepresentation.winner = when (boardRepresentation.playerToMove) {
                Player.YELLOW -> Player.RED
                Player.RED -> Player.YELLOW
                else -> Player.NONE
            }
            return
        }

        // horizontal check

        fieldsInARow = 0
        var targetRow = -1
        for (row in 0..5){
            if (boardRepresentation.fields[column][row] > 0) {
                targetRow = row
                break
            }
        }

                for (column in 0..6) {

                    if (boardRepresentation.fields[column][targetRow] == relevantFieldType) {

                        fieldsInARow++

                        if (fieldsInARow == 4) {
                            boardRepresentation.gameOver = true
                            boardRepresentation.winner = when (boardRepresentation.playerToMove) {
                                Player.YELLOW -> Player.RED
                                Player.RED -> Player.YELLOW
                                else -> Player.NONE
                            }
                            return
                        }

                    } else {

                        fieldsInARow = 0
                    }

                }

        fieldsInARow = 0

        // diagonal check

        val columnOffsets = arrayOf(arrayOf(1, -1), arrayOf(-1, 1))
        val rowOffsets = arrayOf(arrayOf(1, -1), arrayOf(1, -1))
        var targetColumn = column
        val tempTargetRow = targetRow

        for (diagonal in 0..1) {
            for (offset in 0..1) {

                while (targetColumn in 0..6 && targetRow in 0..5) {

                    if (boardRepresentation.fields[targetColumn][targetRow] == relevantFieldType) {

                        fieldsInARow++

                        if (fieldsInARow == 4) {
                            boardRepresentation.gameOver = true
                            boardRepresentation.winner = when (boardRepresentation.playerToMove) {
                                Player.YELLOW -> Player.RED
                                Player.RED -> Player.YELLOW
                                else -> Player.NONE
                            }
                            return
                        }

                    } else {

                        break
                    }

                    targetColumn += columnOffsets[diagonal][offset]
                    targetRow += rowOffsets[diagonal][offset]
                }
            }
            targetColumn = column
            targetRow = tempTargetRow
            fieldsInARow = 0
        }
    }


    fun DrawBoard() {

        for (row in 0..5) {
            for (column in 0..6) {
                when (boardRepresentation.fields[column][5- row]){
                    Field.NONE.fieldValue -> print(" ▪ ")
                    Field.YELLOW.fieldValue -> print(" ○ ")
                    Field.RED.fieldValue -> print(" ● ")
                }
            }
            println("\n")
        }

    }
}