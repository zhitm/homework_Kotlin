package homeworks.hw6.tictactoe.PCgamer

import homeworks.hw6.tictactoe.Game
import homeworks.hw6.tictactoe.enums.FigureType

class Node(private val game: Game, private val parent: Node? = null, val move: Move? = null, val depth: Int = 0) {
    private var PCwinCount = 0
    private var gamerWinCount = 0
    var children = mutableListOf<Node>()
    init {
        addLeafs()
    }
    val isLeaf: Boolean
        get() = children.isEmpty()
    val coeffOfWin
        get() = PCwinCount - gamerWinCount

    private fun updateSizes(increment: (Node) -> Unit) {
        parent?.let {
            increment(it)
            it.updateSizes(increment)
        }
    }

    private fun getPossibleMoves(): MutableList<Move> {
        val moves = mutableListOf<Move>()
        for (row in 0..2) {
            for (column in 0..2) {
                if (game.board.field[row][column] == FigureType.EMPTY) {
                    moves.add(Move(row, column, game.nextPlayer))
                }
            }
        }
        return moves
    }

    fun printBoard(){
        println("${move?.row} ${move?.column}")
        game.board.field.forEach { row -> row.forEach { print(it) ; print(" ") }; println() }
        println("--------")
    }

    private fun addLeafs() {
        if (game.isOver() || depth>6) {
            if (game.winner == game.gamerFigure) updateSizes { gamerWinCount++ }
            if (game.winner == game.PCFigure) updateSizes { PCwinCount++ }
        } else {
            val moves = getPossibleMoves()
            for (move in moves) {
                val newGame = game.copy()
                newGame.makeMove(move.row, move.column)
                val newChild = Node(newGame, this, move, depth+1)
                children.add(newChild)
            }
        }
    }
}
