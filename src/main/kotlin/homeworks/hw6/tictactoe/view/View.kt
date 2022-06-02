package homeworks.hw6.tictactoe.view

import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import homeworks.hw6.tictactoe.ViewModel
import homeworks.hw6.tictactoe.enums.Screen

@Suppress("FunctionNaming")
@Composable
fun View(viewModel: ViewModel) {
    val state = viewModel.state
    MaterialTheme {
        when (state.screen) {
            Screen.StartScreen -> {
                StartScreen(viewModel)
            }
            Screen.GameScreen -> {
                GameScreen(viewModel)
            }
        }
    }
}
