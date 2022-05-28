package homeworks.hw6.tictactoe

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import homeworks.hw6.tictactoe.enums.FigureType
import homeworks.hw6.tictactoe.enums.GameState
import homeworks.hw6.tictactoe.enums.GameType
import homeworks.hw6.tictactoe.enums.Screen
import homeworks.hw6.tictactoe.game.Board

class ViewModel {
    var state: State by mutableStateOf(initialState())
        private set

    private fun initialState(): State =
        State(
            Board(),
            0,
            FigureType.CIRCLE,
            FigureType.CROSS,
            GameState.AWAITING_START,
            gameType = GameType.AGAINST_YOURSELF,
            screen = Screen.StartScreen
        )

    data class State(
        val board: Board,
        val moveCount: Int,
        val pcFigure: FigureType,
        val nextPlayer: FigureType,
        val gameState: GameState,
        val gameType: GameType,
        val screen: Screen
    )

    fun updateState(newState: State) {
        state = newState
    }

    fun updateScreen(screen: Screen) = updateState { copy(screen = screen) }

    private inline fun updateState(update: State.() -> State) {
        state = state.update()
    }

    fun onSideSelect(figureType: FigureType) {
        game.pcFigureType = figureType
        updateState { copy(pcFigure = figureType) }
    }

    fun onGameTypeSelect(gameType: GameType) {
        game.gameType = gameType
        updateState { copy(gameType = gameType) }
    }

    fun onCellClick(row: Int, column: Int) {
        updateState(makeMove(row, column))
    }
}
