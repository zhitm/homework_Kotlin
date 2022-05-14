package homeworks.hw4.task1.benchmark

import jetbrains.letsPlot.export.ggsave
import jetbrains.letsPlot.geom.geomLine
import jetbrains.letsPlot.geom.geomPoint
import jetbrains.letsPlot.ggplot
import jetbrains.letsPlot.ggsize

@Suppress("MagicNumber")
fun main() {
    val graphicCreator = GraphicCreator()
    for (i in 1..15) {
        val data = graphicCreator.createMapForGraphic(i)
        val fig = ggplot(data) { x = "x"; y = "y"; color = "threads" } + geomPoint(size = 2.0) + geomLine() + ggsize(
            1920,
            1080
        )
        ggsave(fig, "plot_${i}_threads.png")
    }
}
