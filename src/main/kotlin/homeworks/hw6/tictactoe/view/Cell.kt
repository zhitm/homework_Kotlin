package homeworks.hw6.tictactoe.view

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.graphics.painter.BitmapPainter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.loadImageBitmap
import androidx.compose.ui.unit.dp
import homeworks.hw6.tictactoe.enums.FigureType
import java.io.File


@Composable
fun Cell(row: Int, column: Int, figure: FigureType, onCellClick: (Int, Int) -> Unit) {
    Box(
        Modifier.clickable(onClick = { onCellClick(row, column) }
        ).size(100.dp)
            .clip(shape = RoundedCornerShape(10.dp))

    ) {
        val path =
            if (figure == FigureType.CROSS) "src/main/resources/images/cross.png"
            else if (figure == FigureType.CIRCLE) "src/main/resources/images/circle.png" else "src/main/resources/images/empty.jpg"

        val file = File(path)
        val imageBitmap: ImageBitmap = remember(file) {
            loadImageBitmap(file.inputStream())
        }
        Image(
            painter = BitmapPainter(image = imageBitmap),
            contentDescription = null,
            contentScale = ContentScale.Crop,
            modifier = Modifier.fillMaxSize()
        )
    }
}


