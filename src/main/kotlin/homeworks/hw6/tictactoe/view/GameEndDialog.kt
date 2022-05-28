package homeworks.hw6.tictactoe.view

import androidx.compose.material.AlertDialog
import androidx.compose.material.Button
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import homeworks.hw6.tictactoe.ViewModel
import homeworks.hw6.tictactoe.enums.Screen
import kotlin.system.exitProcess

@Suppress("FunctionNaming")
@OptIn(ExperimentalMaterialApi::class)
@Composable
fun GameEndDialog(viewModel: ViewModel) {
    AlertDialog(
        onDismissRequest = {},
        title = { Text("Игра окончена.") },
        text = { Text("В каком режиме дальше?") },
        confirmButton = {
            Button(onClick = { viewModel.updateScreen(Screen.StartScreen) }) { Text("Начать новую игру") }
        },
        dismissButton = { Button(onClick = { exitProcess(0) }) { Text("Выйти из игры") } })
}
