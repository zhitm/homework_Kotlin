package homeworks.hw6.tictactoe.view

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.runtime.Composable
import homeworks.hw6.tictactoe.ViewModel

@Suppress("FunctionNaming")
@Composable
fun Board(rowCount: Int, columnCount: Int, viewModel: ViewModel) {
    Column {
        for (y in 0 until columnCount) {
            Row {
                for (x in 0 until rowCount) {
                    Cell(y, x, viewModel.state.board.field[y][x], viewModel::onCellClick)
                }
            }
        }
    }
}
