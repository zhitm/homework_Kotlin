package homeworks.hw6.tictactoe

var game: Game = Game()

fun startGame(): ViewModel.State {
    game.startGame()
    return getState()
}

fun getState(): ViewModel.State = ViewModel.State(game.board, game.nextPlayer, game.state)

fun makeMove(row: Int, column: Int): ViewModel.State {
    game.makeMove(row, column)
    return getState()
}
