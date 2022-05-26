package homeworks.hw6.tictactoe

import homeworks.hw6.tictactoe.PCgamer.PCgamer
import homeworks.hw6.tictactoe.enums.FigureType
import homeworks.hw6.tictactoe.enums.GameType

var game: Game = Game(GameType.AGAINST_PC, FigureType.CIRCLE)
var pcGamer = PCgamer(FigureType.CIRCLE)
fun startGame(): ViewModel.State {
    game.startGame()
    return getState()
}

fun getState(): ViewModel.State = ViewModel.State(game.board, game.nextPlayer, game.state)

fun makeMove(row: Int, column: Int): ViewModel.State {
    game.makeMove(row, column)
    return getState()
}
