package homeworks.hw6.tictactoe.view

import androidx.compose.runtime.Composable
import homeworks.hw6.tictactoe.ViewModel
import homeworks.hw6.tictactoe.game

@Suppress("FunctionNaming", "MagicNumber")
@Composable
fun GameScreen(viewModel: ViewModel) {
    viewModel.state
    if (!game.isAwaitingStart()) {
        Board(3, 3, viewModel)
    }
    if (game.isOver()) {
        GameEndDialog(viewModel)
    }
}
