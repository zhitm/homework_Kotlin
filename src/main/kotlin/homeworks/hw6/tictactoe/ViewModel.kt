package homeworks.hw6.tictactoe

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import homeworks.hw6.tictactoe.enums.FigureType
import homeworks.hw6.tictactoe.enums.GameState

class ViewModel {
    var state: State by mutableStateOf(initialState())
        private set

    private fun initialState(): State =
        State(Board(), FigureType.CROSS, GameState.AWAITING_START)

    data class State(val board: Board, val nextPlayer: FigureType, val gameState: GameState)

    fun updateState(newState: State) {
        state = newState
    }

    fun onCellClick(row: Int, column: Int) {
        updateState(makeMove(row, column))
        state.board.field.forEach {
            it.forEach { print(it)
            print(" ")
            }
            println()
        }
        println("____")
    }
}