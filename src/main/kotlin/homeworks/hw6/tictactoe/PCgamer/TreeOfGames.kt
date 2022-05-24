package homeworks.hw6.tictactoe.PCgamer

class TreeOfGames(private var root: Node) {
    fun getBestMove(): Move {
        require(root.children.isNotEmpty()) { "Игра закончена, искать новые ходы нет смысла" }
        val child = root.children.maxByOrNull { it.coeffOfWin }
        child?.let { changeRoot(it) }
        return child?.move ?: throw Exception("нет детей и не выбрать лучший ход")
    }

    private fun changeRoot(child: Node) {
        root = child
    }
    fun changeRootByMove(move: Move){

        root = root.children.find { it.move == move } ?: throw Exception("ход противника не повторить")
    }
}
