package homeworks.hw4.task1.benchmark

import jetbrains.letsPlot.export.ggsave
import jetbrains.letsPlot.geom.geomSmooth
import jetbrains.letsPlot.ggsize
import jetbrains.letsPlot.letsPlot

@Suppress("MagicNumber")
fun main() {

    val graphicCreator = GraphicCreator()
    val data = mutableMapOf<String, Any>()
    val maxThreadsCount = 5
    for (i in 1..maxThreadsCount) {
        data += graphicCreator.createMapForGraphic(i)
    }
    val smooth =
        (1..maxThreadsCount).map { geomSmooth(method = "loess", se = false) { x = "x$it"; y = "y$it"; color = "$it" } }
    var fig = letsPlot(data) + ggsize(1920, 1080)
    smooth.forEach { fig += it }
    ggsave(fig, "plot_threads.png")
}
