package homeworks.hw6.tictactoe.pcGamer

import homeworks.hw6.tictactoe.enums.FigureType
import homeworks.hw6.tictactoe.enums.GameType
import homeworks.hw6.tictactoe.game.Game

open class DumbBot(figureType: FigureType) {
    protected val gameCopy = Game(GameType.AGAINST_PC, figureType)

    init {
        gameCopy.startGame(GameType.AGAINST_YOURSELF, figureType)
    }

    open fun updateBotAndGetMove(lastMove: Move?): Move? {
        lastMove?.let { gameCopy.tryToMove(it.row, it.column) }
        val moves = getPossibleMoves(gameCopy).shuffled()
        if (moves.isEmpty()) return null
        val move = moves.first()
        move.let { gameCopy.tryToMove(move.row, move.column) }
        return move
    }

    protected fun getPossibleMoves(game: Game): MutableList<Move> {
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
}
