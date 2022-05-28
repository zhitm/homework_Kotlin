package homeworks.hw4.task1

import homeworks.hw4.task1.benchmark.GraphicCreator
import jetbrains.letsPlot.intern.Plot
import java.awt.Dimension
import java.awt.GridLayout
import javax.swing.*
import javax.swing.WindowConstants.EXIT_ON_CLOSE

// по образу и подобию официального примера: https://github.com/alshan/lets-plot-mini-apps/tree/main/jvm-swing-javafx-app

class UserInterface {
    fun draw() {
        val window = JFrame("QSort")
        window.defaultCloseOperation = EXIT_ON_CLOSE
        window.contentPane.layout = BoxLayout(window.contentPane, BoxLayout.Y_AXIS)
        val stringPlotsMap = getMapOfGraphics()
        val selectedPlotKey = stringPlotsMap.keys.first()
        val controller = Controller(stringPlotsMap, selectedPlotKey)
        val controlsPanel = getControlPanels(stringPlotsMap, controller)
        window.contentPane.add(controlsPanel)

        val plotContainerPanel = JPanel(GridLayout())
        window.contentPane.add(plotContainerPanel)

        controller.plotContainerPanel = plotContainerPanel
        controller.rebuildPlotComponent()

        SwingUtilities.invokeLater {
            window.pack()
            window.size = Dimension(WIDTH, HEIGHT)
            window.setLocationRelativeTo(null)
            window.isVisible = true
        }
    }

    private fun getControlPanels(stringPlotMap: Map<String, Plot>, controller: Controller): Box =
        Box.createHorizontalBox().apply {
            val selectedPlotKey = stringPlotMap.keys.first()
            val plotButtonGroup = ButtonGroup()
            for (key in stringPlotMap.keys) {
                plotButtonGroup.add(
                    JRadioButton(key, key == selectedPlotKey).apply {
                        addActionListener {
                            controller.plotKey = this.text
                        }
                    }
                )
            }

            this.add(Box.createHorizontalBox().apply {
                border = BorderFactory.createTitledBorder("Plot")
                for (elem in plotButtonGroup.elements) {
                    add(elem)
                }
            })
            val aspectRadioButtonGroup = ButtonGroup()
            aspectRadioButtonGroup.add(JRadioButton("Original", false).apply {
                addActionListener {
                    controller.preserveAspectRadio = true
                }
            })
            aspectRadioButtonGroup.add(JRadioButton("Fit container", true).apply {
                addActionListener {
                    controller.preserveAspectRadio = false
                }
            })

            this.add(Box.createHorizontalBox().apply {
                border = BorderFactory.createTitledBorder("Aspect ratio")
                for (elem in aspectRadioButtonGroup.elements) {
                    add(elem)
                }
            })
        }

    private fun getMapOfGraphics(): Map<String, Plot> {
        val graphicCreator = GraphicCreator()
        val names = (1..MAX_THREADS_COUNT).map { "$it threads" }.toMutableList()
        names.add("coroutines")
        val plots = (1..MAX_THREADS_COUNT).map {
            graphicCreator.getPlot(it)
        }.toMutableList()
        plots.add(graphicCreator.getCoroutinePlot())
        return names.associateWith { plots[names.indexOf(it)] }
    }

    companion object {
        const val MAX_THREADS_COUNT = 10
        const val WIDTH = 1920
        const val HEIGHT = 1800
    }
}
