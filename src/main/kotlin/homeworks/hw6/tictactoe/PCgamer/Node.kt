package homeworks.hw6.tictactoe.PCgamer

import homeworks.hw6.tictactoe.Game
import homeworks.hw6.tictactoe.enums.FigureType

class Node(private val game: Game, private val parent: Node? = null, val move: Move? = null) {
    init {
        addLeafs()
    }
    var children = mutableListOf<Node>()
    val isLeaf: Boolean
        get() = children.isEmpty()
    val coeffOfWin
        get() = if (subtreeLeafsCount != 0) {
            winCountAtSubtree / subtreeLeafsCount
        } else {
            1
        }
    private var winCountAtSubtree = 0
    private var subtreeLeafsCount = 0
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

    private fun addLeafs() {
        if (game.isOver()) {
            updateSizes { subtreeLeafsCount++ }
            if (game.winner == game.PCFigure) updateSizes { winCountAtSubtree++ }
        } else {
            val moves = getPossibleMoves()
            for (move in moves) {
                val newGame = game.copy()
                newGame.makeMove(move.row, move.column)
                val newChild = Node(newGame, this, move)
                children.add(newChild)
            }
        }
    }
}
