package homeworks.hw6.tictactoe.view

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.layoutId
import androidx.compose.ui.unit.dp
import homeworks.hw6.tictactoe.ViewModel
import homeworks.hw6.tictactoe.startGame

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