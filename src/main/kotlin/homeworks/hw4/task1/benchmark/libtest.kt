package homeworks.hw4.task1.benchmark

import jetbrains.letsPlot.export.ggsave
import jetbrains.letsPlot.geom.geomPoint
import jetbrains.letsPlot.letsPlot

fun main() {
    val graphicCreator = GraphicCreator()
    for (i in 1..1) {
        val data = graphicCreator.createMapForGraphic(i)
        val fig = letsPlot(data) + geomPoint(color = i * 500, size = 4.0) { x = "x"; y = "y" }

        ggsave(fig, "plot_${i}_threads.png")
    }
}
