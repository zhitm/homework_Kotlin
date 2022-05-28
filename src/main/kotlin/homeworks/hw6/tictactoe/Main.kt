package homeworks.hw6.tictactoe

import androidx.compose.runtime.remember
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Window
import androidx.compose.ui.window.application
import androidx.compose.ui.window.rememberWindowState

fun main() = application {
    Window(
        onCloseRequest = ::exitApplication, title = "GAME",
        state = rememberWindowState(width = 800.dp, height = 800.dp)
    ) {
        val viewModel = remember { ViewModel() }
        view(viewModel)
    }
}
