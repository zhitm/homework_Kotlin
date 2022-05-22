package homeworks.hw4.task1.benchmark

import homeworks.hw4.task1.benchmark.graphicCreators.GraphicCreator
import homeworks.hw4.task1.qsort.QSort
import homeworks.hw4.task1.qsort.QSortCoroutines
import jetbrains.letsPlot.export.ggsave
import jetbrains.letsPlot.geom.geomSmooth
import jetbrains.letsPlot.ggsize
import jetbrains.letsPlot.letsPlot

@Suppress("MagicNumber")
fun main() {
    val coroutinesGraphicCreator = GraphicCreator(QSortCoroutines(), "coroutines", 1)
    val usualGraphicCreator = GraphicCreator(QSort<Int>(), "usual sort", 2)
    val data = coroutinesGraphicCreator.createMapForGraphic() + usualGraphicCreator.createMapForGraphic()
    val fig = letsPlot(data) + ggsize(1920, 1080) + geomSmooth(method = "loess", se = false) {
        x = "x1"; y = "y1"; color = "coroutines"
    } + geomSmooth(method = "loess", se = false) { x = "x2"; y = "y2"; color = "usual sort" }
    ggsave(fig, "plot_coroutines.png")
}
