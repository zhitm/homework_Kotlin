package homeworks.hw6.tictactoe.view

import androidx.compose.foundation.layout.Column
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import homeworks.hw6.tictactoe.ViewModel
import homeworks.hw6.tictactoe.enums.Screen
import homeworks.hw6.tictactoe.game

@Suppress("FunctionNaming", "MagicNumber")
@Composable
fun GameScreen(viewModel: ViewModel) {
    viewModel.state
    Column {
        if (!game.isAwaitingStart()) {
            Board(3, 3, viewModel)
        }
        Button(onClick = {
            viewModel.updateScreen(Screen.StartScreen)
        }) { Text("В меню") }
    }
    if (game.isOver()) {
        GameEndDialog(viewModel)
    }

}
