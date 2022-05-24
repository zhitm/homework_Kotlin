package homeworks.hw6.tictactoe.view

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import homeworks.hw6.tictactoe.ViewModel
import homeworks.hw6.tictactoe.enums.FigureType
import homeworks.hw6.tictactoe.makeMove

@Composable
fun Cell(row: Int, column: Int, figure: FigureType, onCellClick: (Int, Int) -> Unit) {
    Box(
        Modifier.clickable(onClick = { onCellClick(row, column) }
        ).size(100.dp)
            .clip(shape = RoundedCornerShape(10.dp))
            .background(if (figure == FigureType.CROSS) Color.Red else if (figure == FigureType.CIRCLE) Color.Blue else Color.Gray)
    )
}