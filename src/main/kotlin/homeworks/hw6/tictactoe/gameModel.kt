package homeworks.hw6.tictactoe

import homeworks.hw6.tictactoe.enums.FigureType
import homeworks.hw6.tictactoe.enums.GameType

var game: Game = Game(GameType.AGAINST_YOURSELF)
fun startGame(gameType: GameType): ViewModel.State {
    game.startGame(gameType, FigureType.CIRCLE)
    return getState()
}

fun getState(): ViewModel.State = ViewModel.State(game.board, game.nextPlayer, game.state)

fun makeMove(row: Int, column: Int): ViewModel.State {
    game.makeMove(row, column)
    return getState()
}
