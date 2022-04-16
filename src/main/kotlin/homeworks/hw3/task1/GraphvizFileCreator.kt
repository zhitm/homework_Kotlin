package homeworks.hw3.task1

import java.io.File
import java.io.PrintWriter

class GraphvizFileCreator<K, V> {
    private fun writeEdges(root: Node<K, V>, writer: PrintWriter) {
        if (root.leftNode != null) {
            writer.println("${root.key} -> ${root.leftNode!!.key}")
            writeEdges(root.leftNode!!, writer)
        }
        if (root.rightNode != null) {
            writer.println("${root.key} -> ${root.rightNode!!.key}")
            writeEdges(root.rightNode!!, writer)
        }
    }

    fun createDotFile(path: String, root: Node<K, V>?) {
        File(path).printWriter().use { out ->
            out.println("digraph G {")
            if (root?.isLeaf() == true) out.println(root.key)
            root?.let { writeEdges(it, out) }
            out.println("}")
        }
    }
}
